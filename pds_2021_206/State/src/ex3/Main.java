package ex3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Livro l1 = new Livro("Java Anti-Stress",1,1997,"Omodionah");
        Livro l2 = new Livro("A Guerra dos Padrões",2,1984,"Jorge Omel");
        Livro l3 = new Livro("A Procura da Luz",3,2001,"Khumatkli");
        ArrayList<Livro> biblioteca = new ArrayList<>();
        biblioteca.add(l1); biblioteca.add(l2); biblioteca.add(l3);
        boolean looping = true;
        while(looping) {
            printBiblioteca(biblioteca);
            String input = sc.nextLine();
            String[] paramsStr = input.split(",");
            int[] params = {Integer.parseInt(paramsStr[0]),Integer.parseInt(paramsStr[1])};
            Livro oLivro = null;
            for(Livro l : biblioteca) {
                if(l.getISBN() == params[0]) oLivro = l;
            }
            if(oLivro == null) {
                System.out.println("Livro não encontrado!");
                break;
            }
            switch(params[1]) {
                case 1:
                    oLivro.resgitar();
                    break;
                case 2:
                    oLivro.requisitar();
                    break;
                case 3:
                    oLivro.devolver();
                    break;
                case 4:
                    oLivro.reservar();
                    break;
                case 5:
                    oLivro.cancelar();
                    break;
                default:
                    looping = false;
                    break;
            }

        }
        sc.close();

    }

    public static void printBiblioteca(ArrayList<Livro> biblioteca) {
        System.out.println("*** Biblioteca ***");
        for(Livro l : biblioteca) {
            System.out.println(l.getISBN()+"\t"+l.getTitulo()+"\t"+l.getAutor()+"\t["+l.getState().getState()+"]");
        }
        System.out.println(">> <livroISBN>,<operação>: (1)resgistar; (2)requisitar; (3)devolver; (4)reservar; (5)cancelar;");
    }
}
