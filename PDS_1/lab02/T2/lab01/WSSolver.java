import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WSSolver {
    static int size_quadrado;
    static List<String> puzzle = new ArrayList<String>();
    static ArrayList<String> words = new ArrayList<String>();

    public static void main(String[] args) {

        // Número de argumentos invalido
        if (args.length != 1) {
            System.out.println("Passe como argumento apenas o ficheiro de leitura!");
            System.exit(-1);
        }

        try (Scanner input = new Scanner(new FileReader(args[0]))) {

            // Ler a primeira linha e fazer as verificacoes dessa linha
            // A primeira linha foi lida separadamente para se poder ver o tamanho dessa
            // linha, pois sabe-se que o tamanho do puzzle tera de ter o tamanho dessa linha
            String line_one = input.nextLine();
            if (line_one.isEmpty()) {
                System.out.println("O puzzle contem linhas vazias");
                System.exit(-1);
            }
            if (!line_one.matches("[A-Z]+")) {
                System.out.println("O puzzle n contem só letras maiusculas");
                System.exit(-1);
            }

            if (line_one.length() > 40) {
                System.out.println("O tamanho do puzzle é superior a 40!");
                System.exit(-1);
            }

            puzzle.add(line_one);
            size_quadrado = line_one.length();
            // Verificar
            for (int i = 0; i < (size_quadrado - 1); i++) {
                // Ler cada linha do ficheiro passado e fazer as verificacoes
                // Verificar se o puzzle apenas contem letras maiusculas
                // Verificar se o tamanho do puzzle passa o máximo
                if (input.hasNextLine()) {
                    String line = input.nextLine();
                    if (line.isEmpty()) {
                        System.out.println("O puzzle contem linhas vazias");
                        System.exit(-1);
                    } else if (line.length() != size_quadrado) {
                        System.out.println("O puzzle nao é quadraddo");
                        System.exit(-1);
                    } else if (!line.matches("[A-Z]+")) {
                        System.out.println("O puzzle n contem só letras maiusculas");
                        System.exit(-1);
                    }
                    // Adicinar a linha ao array puzzle, para se ficar com o array apenas com as
                    // linhas do puzzle
                    puzzle.add(line);
                } else { // Verificar se o puzzle tem as dimensões certas
                    System.out.println("O puzzle nao é quadraddo");
                    System.exit(-1);
                }
            }

            // Verificar as palavras-chaves que se querem encontrar na sopa de letras e se
            // verificam as condiçoes necessarias
            while (input.hasNextLine()) {
                String[] linha = input.nextLine().split("[, ;]+");
                for (String j : linha) {
                    if (j.isEmpty()) {
                        System.out.println("A lista de palavras contem linhas vazias");
                        System.exit(-1);
                    } else if (!j.matches("[a-zA-Z]+")) {
                        System.out.println("As palavras não são compostas só por caracteres alfabéticos");
                        System.exit(-1);
                    }
                    words.add(j);
                }
            }

            input.close();

        } catch (FileNotFoundException e) {
            System.err.printf("ERRO: %s\n", e.getMessage());
        }

        char[][] sopa = new char[size_quadrado][size_quadrado];
        char[][] sopa_solucao = new char[size_quadrado][size_quadrado];
        int count = 0;
        for (String string : puzzle) {
            for (int i = 0; i < string.length(); i++) {
                sopa[count][i] = string.charAt(i); // preencher sopa com os caracteres nas posiçoes corretas no puzzle
                sopa_solucao[count][i] = '.'; // preencher sopa_solucao com '.' para posterior utilizacao
            }
            count++;
        }

        // ordenar words por ordem: maior comprimento ate menor, de modo a corrigir o
        // requesito 8
        ArrayList<String> words_ordemCorreta = (ArrayList<String>) words.clone();
        words.sort((x, y) -> {
            if (x.length() > y.length())
                return -1;
            else if (x.length() < y.length())
                return 1;
            else
                return 0;
        });

        Map<Ponto, Integer> posicoes = new HashMap<>(); // 0->livre; 1->ocupado
        Set<Ponto> inicio = new HashSet<>();
        Set<Caminho> allWays_possibles = new HashSet<>();

        for (int i = 1; i < size_quadrado + 1; i++) {
            for (int k = 1; k < size_quadrado + 1; k++) {
                Ponto ponto_aux = new Ponto(i, k);
                inicio.add(ponto_aux);
                posicoes.put(new Ponto(i, k), 0);
            }
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_down = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_down.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_down.clone(), Direction.DOWN));
            } while (++line_aux <= size_quadrado);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_up = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_up.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_up.clone(), Direction.UP));
            } while (--line_aux > 0);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_right = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_right.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_right.clone(), Direction.RIGHT));
            } while (++col_aux <= size_quadrado);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_left = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_left.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_left.clone(), Direction.LEFT));
            } while (--col_aux > 0);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_upLeft = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_upLeft.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_upLeft.clone(), Direction.UPLEFT));
            } while (--line_aux > 0 && --col_aux > 0);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_downRight = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_downRight.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_downRight.clone(), Direction.DOWNRIGHT));
            } while (++line_aux <= size_quadrado && ++col_aux <= size_quadrado);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_downLeft = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_downLeft.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_downLeft.clone(), Direction.DOWNLEFT));
            } while (++line_aux <= size_quadrado && --col_aux > 0);
        }

        for (Ponto p : inicio) {
            ArrayList<Ponto> values_upRight = new ArrayList<>();
            int line_aux = p.getX();
            int col_aux = p.getY();
            do {
                values_upRight.add(new Ponto(line_aux, col_aux));
                allWays_possibles.add(new Caminho((ArrayList<Ponto>) values_upRight.clone(), Direction.UPRIGHT));
            } while ((--line_aux > 0) && (++col_aux <= size_quadrado));
        }

        Map<String, ArrayList<Caminho>> combinacoes = new HashMap<>();
        // Ir a todas as possibilidades e ir buscar um caminho
        for (Caminho way : allWays_possibles) {
            String palavra = "";
            // percorrer as posicoes do caminho selecionado e gerar a palavra que essas
            // posicoes contêm
            for (Ponto ponto : way.getPosicoes()) {
                palavra += sopa[ponto.getX() - 1][ponto.getY() - 1];
            }

            // Verificar se a palavra ja se encontra no mapa combinacoes adicionando ao
            // array valor o caminho caso a palavra esteja no mapa
            ArrayList<Caminho> valor;
            if (combinacoes.containsKey(palavra)) {
                valor = combinacoes.get(palavra);
                valor.add(way);
            } else {
                valor = new ArrayList<>();
                valor.add(way);
            }
            //gera mapa com chave palavra e valor o array valor q contem o caminho
            combinacoes.put(palavra, valor);
        }

        Map<String, Caminho> solucao = new HashMap<>();
        //percorrer as palavras da lista
        for (String is : words) {
            ArrayList<Caminho> caminhos = combinacoes.get(is.toUpperCase());
            ArrayList<Caminho> caminhos_remover = new ArrayList<>();
            //Verificar se a palavra foi bem inserida no puzzle
            if (caminhos == null) {
                System.out.printf("A palavra %s nao está no puzzle\n", is);
                System.exit(-1);
            }
            //Percorrer todos os caminhos e verificar se alguma posicao esta ocupada
            for (Caminho caminho : caminhos) {
                for (Ponto ponto : caminho.getPosicoes()) {
                    if (posicoes.get(ponto) == 1) {
                        //adicionar o caminho que encontrou posicoes ocupadas e adiciona-lo ao array caminhos_remover
                        caminhos_remover.add(caminho);
                    }
                }
            }
            //remover os caminhos que encontraram posicoes ocupadas
            caminhos.removeAll(caminhos_remover);

            //Verificar se a palavra esta mais do q uma vez no puzzle
            if (caminhos.size() > 1) {
                System.out.printf("A palavra %s está mais que uma vez no puzzle!\n", is);
                System.exit(-1);
            //Verificar se a palavra esta sobre uma palavra maior
            } else if (caminhos.size() == 0) {
                System.out.printf("A palavra %s está sobre uma palavra maior\n", is);
                System.exit(-1);
            }

            // atualizar dicionario ocupado
            Caminho caminho = caminhos.get(0);
            for (Ponto ponto : caminho.getPosicoes()) {
                posicoes.put(ponto, 1);
            }

            solucao.put(is, caminho);
        }
        //Fazer os prints no ficheiro e no terminal
        try (PrintWriter out = new PrintWriter(new File("out3.txt"))) {

            // ordenar solucao atraves da chave: lista de palavras do puzzle retornadas pelo
            // WSSolver tem de estar na mesma ordem das palavras passadas na lista.
            for (String palavra : words_ordemCorreta) {
                Caminho caminho = solucao.get(palavra);

                for (Ponto ponto : caminho.getPosicoes()) {
                    int line = ponto.getX() - 1;
                    int coluna = ponto.getY() - 1;
                    sopa_solucao[line][coluna] = sopa[line][coluna];
                }

                int lineInicio = caminho.getPosicoes().get(0).getX();
                int colunaInicio = caminho.getPosicoes().get(0).getY();
                System.out.printf("%-20s %-5d %-7s %-10s \n", palavra, palavra.length(),
                        lineInicio + "," + colunaInicio, caminho.getDirecao());
                out.printf("%-20s %-5d %-7s %10s \n", palavra, palavra.length(), lineInicio + "," + colunaInicio,
                        caminho.getDirecao());
            }

            System.out.println();
            out.println();

            for (int i = 0; i < sopa_solucao.length; i++) {
                for (int j = 0; j < sopa_solucao.length; j++) {
                    System.out.printf("%s ", sopa_solucao[i][j]);
                    out.printf("%s ", sopa_solucao[i][j]);
                }
                System.out.println();
                out.println();
            }

            out.close();
        } catch (FileNotFoundException e) {
            System.err.printf("ERRO: %s\n", e.getMessage());
        }

    }
}