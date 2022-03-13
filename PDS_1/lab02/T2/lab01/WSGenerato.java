import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class WSGenerato {
    static Map<Ponto, Integer> positions = new HashMap<>(); //0: livre;  1:ocupado
    static Map<String, Caminho> solucao = new HashMap<>();
    static ArrayList<String> words_ordemCorreta;
    public static void main(String[] args) {

        if (args.length == 6) {
            if (!(args[0].equals("-i") && args[1].getClass().getSimpleName().equals("String") && args[2].equals("-s")
                    && args[3].matches("[0-9]+") && args[4].equals("-o")
                    && args[5].getClass().getSimpleName().equals("String"))) {
                System.out.println("Argumentos mal passados, devem ser do tipo '-i {String} -s {Integer} -o {String}'");
                System.exit(1);
            }
        } else if (args.length != 4) {
            System.out.println(
                    "Argumentos mal passados, devem ser do tipo '-i {String} -s {Integer}' ou '-i {String} -s {Integer} -o {String}'");
            System.exit(1);
        } else {
            if (!(args[0].equals("-i") && args[1].getClass().getSimpleName().equals("String") && args[2].equals("-s")
                    && args[3].matches("[0-9]+"))) {
                System.out.println("Argumentos mal passados, devem ser do tipo '-i {String} -s {Integer}'");
                System.exit(1);
            }
        }
        
        words_ordemCorreta = leituraWords(args[1]);

        // ordenar words por ordem: maior comprimento ate menor
        ArrayList<String> words = (ArrayList<String>) words_ordemCorreta.clone();
        words.sort((x, y) -> {
            if (x.length() > y.length())
                return -1;
            else if (x.length() < y.length())
                return 1;
            else
                return 0;
        });



        int size_puzzle = 0;
        try {
            size_puzzle = Integer.parseInt(args[3]);
            if (size_puzzle<2 || size_puzzle>40) {
                System.out.println("O tamanho do puzzle não pode ser superior a 40, nem inferior a 2");
                System.exit(-1);
            }
        } catch (NumberFormatException num) {
            System.out.println("O arguemnto passado para o comprimento do puzzle não é um int!");
            System.exit(-1);
        }

        int contadorChar = 0;
        for (String wordzinhas : words) {
            if (wordzinhas.length() > size_puzzle) {
                System.out
                        .println("Foi encontrada uma palavra com tamanho superior à sopa de letras que se irá gerar.");
                System.exit(-1);
            }
            contadorChar = contadorChar + wordzinhas.length();
        }

        if (contadorChar > size_puzzle * size_puzzle) {
            System.out.println("O número de palavras que se quer inserir na sopa de letras ultrapassa o número de caracteres que esta irá ter");
            System.exit(-1);
        }

        char[][] sopaLetras = new char[size_puzzle][size_puzzle];

        for (int i = 0; i < size_puzzle; i++) {
            for (int j = 0; j < size_puzzle; j++) {
                sopaLetras[i][j] = generateASCII();
                positions.put(new Ponto(i,j), 0);
            }
        }

        for (String palavra : words) {
            sopaLetras = checkSopa( palavra, sopaLetras).clone();
        }

        if(args.length == 6){
            printinFile(sopaLetras , args[5], words);
        }else{
            for (int i = 0; i < size_puzzle; i++) {
                for (int j = 0; j < size_puzzle; j++) {
                    System.out.print(sopaLetras[i][j]);
                }
                System.out.println();
            }
            for (String palavra : words) {
                System.out.printf("%s ", palavra);
            }
        }
        
    }

    public static void printinFile(char[][] sopaLetras , String fileName, ArrayList<String> words){
        try (PrintWriter out = new PrintWriter(new File(fileName))) {
            for (int i = 0; i < sopaLetras.length; i++) {
                for (int j = 0; j < sopaLetras[i].length; j++) {
                    out.printf("%c", sopaLetras[i][j]);
                }
                out.println();
            }
            for (String palavra : words) {
                out.printf("%s ", palavra);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static ArrayList<String> leituraWords(String file){
        ArrayList<String> words = new ArrayList<String>(); 
        try  (Scanner input = new Scanner(new FileReader(file)))  {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.isEmpty()) {
                    System.out.println("A lista de palavras contem linhas vazias");
                    System.exit(-1);
                }
                String[] linha = line.split("[, ;]+");// cortar linhas com , ; e espaço
                for (String j : linha) {
                    if (!j.matches("[a-zA-Z]+")) {
                        System.out.println("As palavras não são compostas só por caracteres alfabéticos");
                        System.exit(-1);
                    }
                    words.add(j);
                }
            }
        }catch (FileNotFoundException e) {
            //TODO: handle exception
            System.err.printf("ERRO: %s\n", e.getMessage());
        }
        return words;
    }

    public static char generateASCII(){
        Random r = new Random();
        int low = 65;
        int high = 90;
        int result = r.nextInt(high-low) + low; 
        return (char)result;
    }

    public static char[][] checkSopa(String checkWord,char[][] sopaLetras){

        ArrayList<ArrayList<String>> ways = caminhos(checkWord,sopaLetras);
        ArrayList<Caminho> caminhos = new ArrayList<>();
        boolean flag = true;

        for (ArrayList<String> arrayList : ways) {
            ArrayList<Ponto> pontos= new ArrayList<>();
            for (String posis : arrayList) {
                String[] posicoes = posis.split(",");
                Ponto aux_ponto = new Ponto(Integer.parseInt(posicoes[0])-1, Integer.parseInt(posicoes[1])-1);
                if (positions.get(aux_ponto)==1  ) {
                    flag = false;
                    
                }
                pontos.add(aux_ponto);
            }

           if (flag) {
               caminhos.add(new Caminho(pontos));

           }
           flag = true;
        }

        if (caminhos.size()==0) {
            Caminho caminho = caminhoAleatorio(checkWord.length(), sopaLetras.length);
           
            ArrayList<Ponto> pontos = caminho.getPosicoes();
            int i = 0;
            for (Ponto ponto : pontos) {
                sopaLetras[ponto.getX()][ponto.getY()] = Character.toUpperCase(checkWord.charAt(i));
                positions.put(ponto, 1);
                i++;
            }
            solucao.put(checkWord, caminho);

        } else if (caminhos.size()!=1){
            ArrayList<Integer> shuf = new ArrayList<>();
            for (int i = 0; i < caminhos.size(); i++) {
                shuf.add(i);
            }
            java.util.Collections.shuffle(shuf);

            for (int i = 0; i < caminhos.size()-1; i++) {
                Caminho way = caminhos.get(shuf.get(i));
                String palavra = alterarCaminho(checkWord);

                int j = 0;
                ArrayList<Ponto> posicoes = way.getPosicoes();
                for (Ponto ponto : posicoes) {
                    sopaLetras[ponto.getX()][ponto.getY()]=Character.toUpperCase(palavra.charAt(j));
                    j++;
                }

            }

            Caminho queFica = caminhos.get(shuf.get(caminhos.size()-1));
            ArrayList<Ponto> pontos = queFica.getPosicoes();
            int i = 0;
            for (Ponto ponto : pontos) {
                sopaLetras[ponto.getX()][ponto.getY()]=Character.toUpperCase(checkWord.charAt(i));
                positions.put(ponto, 1);
                i++;
            }
            solucao.put(checkWord, queFica);
        }

        return sopaLetras;
    }

    public static String alterarCaminho(String word){//gerar word aleatoria que n esteja na lista de palavras e que seja diferente de word
        String palavra;
        do {
            palavra = "";
            for (int i = 0; i < word.length(); i++) {
                palavra+=generateASCII();
            }
        } while (words_ordemCorreta.contains(palavra));

        return palavra;
    }

    public static Caminho caminhoAleatorio(int size_word, int size_sopa){
        Random r = new Random();
        int linhaIncremnto;
        int colunaIncremento;

        ArrayList<Ponto> atpInicio = new ArrayList<>();
        boolean flag = true;
        ArrayList<Ponto> pontos = new ArrayList<>();
        Direction direcao = Direction.DOWN;//so para inicializar

        do {
            Ponto inicio;
            do {
                inicio = new Ponto(r.nextInt(size_sopa-1),r.nextInt(size_sopa-1));
            } while (positions.get(inicio)==1 || atpInicio.contains(inicio));
            

            ArrayList<Integer> shuf = new ArrayList<>();
            shuf.add(1);shuf.add(2);shuf.add(3);shuf.add(4);shuf.add(5);shuf.add(6);shuf.add(7);shuf.add(8);
            java.util.Collections.shuffle(shuf);

            for (int i = 0; i < shuf.size(); i++) {
                switch (shuf.get(i)) {
                    case(1):
                    {
                        colunaIncremento = 0;
                        linhaIncremnto = -1;
                        break;
                    }
                    case(2):
                    {
                        linhaIncremnto = 1;
                        colunaIncremento = 0;
                        break;
                    }
                    case(3):
                    {
                        linhaIncremnto = 0;
                        colunaIncremento = -1;
                        break;
                    }
                    case(4):
                    {
                        linhaIncremnto = 0;
                        colunaIncremento = 1;
                        break;
                    }
                    case(5):
                    {
                        linhaIncremnto = -1;
                        colunaIncremento = -1;
                        break;
                    }
                    case(6):
                    {
                        linhaIncremnto = 1;
                        colunaIncremento = -1;               
                        break;
                    }
                    case(7):
                    {

                        linhaIncremnto = -1;
                        colunaIncremento = 1;    
                        break;
                    }
                    default:
                    {
                        linhaIncremnto = 1;
                        colunaIncremento = 1;    
                        break;
                    }
                }

                pontos = checkDirecao(size_sopa, inicio, linhaIncremnto, colunaIncremento, size_word);

                if (pontos.size()==size_word) {
                    flag=false;
                    direcao = Direction.getEnum(shuf.get(i));
                    i=9;
                }
            }
    
            atpInicio.add(inicio);
        } while (flag);


        Caminho caminho = new Caminho(pontos,direcao);
      
        return caminho;
    }

   
    public static ArrayList<Ponto> checkDirecao(int size_sopa, Ponto inicio, int linhaIncremnto,
            int colunaIncremento, int size_word) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        int linha = inicio.getX();
        int coluna = inicio.getY();
        for (int i = 0; i < size_word; i++) {
            Ponto p = new Ponto(linha, coluna);
            if (linha < 0 || coluna < 0 || linha >= size_sopa || coluna >= size_sopa || positions.get(p)==1) {
                return pontos;
            }
            linha += linhaIncremnto;
            coluna += colunaIncremento;
            pontos.add(p);
        }
        return pontos;
    }


    public static ArrayList<ArrayList<String>> caminhos(String checkWord,char[][] sopaLetras){
        Map<String,Integer> posicoes = new HashMap<>(); //0->livre; 1->ocupado
        Set<int[]> inicio_upLeft = new HashSet<int[]>();
        Set<int[]> inicio_downRight = new HashSet<int[]>();
        Set<ArrayList<String>> combinacoes_indices = new HashSet<>();
        Set<int[]> inicio_downLeft = new HashSet<int[]>();
        Set<int[]> inicio_upRight = new HashSet<int[]>();

        int size_quadrado = sopaLetras.length;
       
        for (int i = 1; i < size_quadrado+1; i++) {
            for (int k = 1; k < size_quadrado+1; k++) {
                ArrayList<String> values_right = new ArrayList<>();
                ArrayList<String> values_down = new ArrayList<>();
                for (int j = k; j < size_quadrado+1; j++) {
                    values_right.add(Integer.toString(i) + "," + Integer.toString(j)); //right
                    values_down.add(Integer.toString(j) + "," + Integer.toString(i));  //Down
                    combinacoes_indices.add((ArrayList<String>) values_right.clone());
                    combinacoes_indices.add((ArrayList<String>) values_down.clone());

                    //Preencher posicoes
                    posicoes.put(Integer.toString(i) + "," + Integer.toString(j), 0);
                }   
                
                ArrayList<String> values_left = new ArrayList<>();
                ArrayList<String> values_up = new ArrayList<>();
                for (int j = k; j > 0 ; j--) {
                    values_left.add(Integer.toString(i) + "," + Integer.toString(j)); //left
                    values_up.add(Integer.toString(j) + "," + Integer.toString(i)); //up
                    combinacoes_indices.add((ArrayList<String>) values_left.clone());
                    combinacoes_indices.add((ArrayList<String>) values_up.clone());
                }   

                int[] aux = {i,k};
                inicio_downLeft.add(aux);
                inicio_upLeft.add(aux);
                inicio_downRight.add(aux);
                inicio_upRight.add(aux);

            }

        }

        for (int[] i : inicio_upLeft) {
            ArrayList<String> values_upLeft = new ArrayList<>();
            int aux_i1 = i[0];
            int aux_i2 = i[1];
            do {
                values_upLeft.add(Integer.toString(aux_i1) + "," + Integer.toString(aux_i2));
                combinacoes_indices.add((ArrayList<String>) values_upLeft.clone());
            } while (--aux_i1 > 0 && --aux_i2 > 0);
        }

        for (int[] i : inicio_downRight) {
            ArrayList<String> values_downRight = new ArrayList<>();
            int aux_i1 = i[0];
            int aux_i2 = i[1];
            do {
                values_downRight.add(Integer.toString(aux_i1) + "," + Integer.toString(aux_i2));
                combinacoes_indices.add((ArrayList<String>) values_downRight.clone());
            } while (++aux_i1 <=size_quadrado && ++aux_i2 <=size_quadrado);
        }

        for (int[] i : inicio_downLeft) {
            ArrayList<String> values_downLeft = new ArrayList<>();
            int aux_i1 = i[0];
            int aux_i2 = i[1];
            do {
                values_downLeft.add(Integer.toString(aux_i1) + "," + Integer.toString(aux_i2));
                combinacoes_indices.add((ArrayList<String>) values_downLeft.clone());
            } while (++aux_i1 <=size_quadrado && --aux_i2 > 0);
        }

        for (int[] i : inicio_upRight) {
            ArrayList<String> values_upRight = new ArrayList<>();
            int aux_i1 = i[0];
            int aux_i2 = i[1];
            do {
                values_upRight.add(Integer.toString(aux_i1) + "," + Integer.toString(aux_i2));
                combinacoes_indices.add((ArrayList<String>) values_upRight.clone());
            } while ( (--aux_i1 > 0) && (++aux_i2 <=size_quadrado));
        }

        ArrayList<ArrayList<String>> caminhos = new ArrayList<>();
        for (ArrayList<String> comb : combinacoes_indices) {
            String palavra = "";

            for (String is : comb) {
                String[] ind = is.split(",");
                palavra += sopaLetras[Integer.parseInt(ind[0]) - 1][Integer.parseInt(ind[1]) - 1];
        
                
            }

            if (palavra.equals(checkWord.toUpperCase())) {
                caminhos.add(comb);
            }

        }   

        return caminhos;
        
    } 
        
}
