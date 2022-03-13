package ex1;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    static Random r = new Random();
    public static void main(String[] args) {
        Produto prod1 = new Produto("The Bestest Product!",1000.0);
        Produto prod2 = new Produto("A great adition to your collection!",300.0);
        Produto prod3 = new Produto("It's only missing a few screws! Buy now!", 250.0);
        Produto prod4 = new Produto("The fastest, smartest and prettiest product you'll ever find!",1110.0);
        Produto prod5 = new Produto("Great product! Trust us!", 550.0);
        ArrayList<Produto> allProducts = new ArrayList<>();
        allProducts.add(prod1); allProducts.add(prod2); allProducts.add(prod3); allProducts.add(prod4); allProducts.add(prod5); 
        Gestor gest1 = new Gestor(allProducts, "Gestemido");
        Cliente cli1 = new Cliente(gest1,"Ana Estácia");
        Cliente cli2 = new Cliente(gest1,"Ricardo Reis");
        Cliente cli3 = new Cliente(gest1,"Bartolomeu Bartolominha");
        ArrayList<Cliente> allClients = new ArrayList<>();
        allClients.add(cli1); allClients.add(cli2); allClients.add(cli3);

        for(int i = 0; i < 40; i++) {
            for(Produto prod : allProducts) {
                boolean changeState = r.nextInt(100) < 30;
                if(changeState) prod.updateState();
            }
            for(Cliente cli : allClients) {
                boolean bid = r.nextInt(100) < 30;
                if(bid) cli.bid();
            }
            if(gest1.getSold().size() == allProducts.size()) {
                System.out.println("All products sold!");
                break;
            }
        }
    }
}
