package Ex2;

public class Main {
    public static void main(String[] args){
        CakeMaster cakeMaster = new CakeMaster();

        CakeBuilder chocolate = new ChocolateCakeBuilder();
        cakeMaster.setCakeBuilder(chocolate);
        cakeMaster.createCake("Congratulations"); // 1 cake layer
        Cake cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: "+cake);
        System.out.print("\n");

        CakeBuilder sponge = new SpongeCakeBuilder();
        cakeMaster.setCakeBuilder(sponge);
        cakeMaster.createCake(Shape.Square, 2, "Well done"); //squared, 2 layers
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: "+ cake);
        System.out.print("\n");

        CakeBuilder yogurt = new YogurtCakeBuilder();
        cakeMaster.setCakeBuilder(yogurt);
        cakeMaster.createCake(3, "The best");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: "+cake);
        System.out.print("\n");

        CakeBuilder portal = new IconicPortalCakeBuilder();
        cakeMaster.setCakeBuilder(portal);
        cakeMaster.createCake(Shape.CIRCLE , 20, "The cake is a lie");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: "+cake);
        System.out.print("\n");

        CakeBuilder love = new LoveCakeBuilder();
        cakeMaster.setCakeBuilder(love);
        cakeMaster.createCake(Shape.HEART,1, "1 <3 u");
        cake = cakeMaster.getCake();
        System.out.println("Your cake is ready: "+cake);
        System.out.print("\n");

    }
}
