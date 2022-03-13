package ex2;

public class Main {
    public static void main(String[] args) {
        Handler sushi = new SushiChef();
        Handler pasta = new PastaChef();
        Handler burger = new BurgerChef();
        Handler pizza = new PizzaChef();
        Handler dessert = new DessertChef();

    sushi.setNext(pasta);
    pasta.setNext(burger);
    burger.setNext(pizza);
    pizza.setNext(dessert);

    String req1 = "veggie burger";
    String req2 = "Pasta Carbonara";
    String req3 = "PLAIN pizza, no toppings!";
    String req4 = "sushi nigiri and sashimi";
    String req5 = "salad with tuna";
    String req6 = "strawberry ice cream and waffles dessert";
    String[] requests = {req1,req2,req3,req4,req5,req6};

    for(String request : requests) {
        System.out.println("Can I please get a "+request+"?");
        sushi.handle(request);
        System.out.print("\n\n\n");
    }

    }
}
