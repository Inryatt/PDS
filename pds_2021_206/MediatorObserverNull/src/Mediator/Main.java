package ex3;

public class Main {
    public static void main(String[] args) {
        Script script = new Script(); // Is our mediator
        Jogo quake = new Jogo("Quake",script);
        VPN southVPN = new VPN("southVPN",script);
        Mundo best_save = new Mundo("best_save",script);

        script.assignContent(quake,best_save,southVPN);

        System.out.println("Is VPN on? " + southVPN.isRunning() );
        System.out.println("Is Game on? "+quake.isRunning());
        System.out.println("->>> Starting the game!");
        script.startGame();
        System.out.println("Is Game on now? "+quake.isRunning());
        System.out.println("What about the VPN? "+southVPN.isRunning());
        System.out.println("And the world? "+best_save.isRunning());
        System.out.println("->>> Loading up the save...");
        script.load();
        System.out.println("Do we have world? "+ best_save.isRunning());
        System.out.println("And VPN? "+southVPN.isRunning());
        System.out.println("->>> Closing game now.");
        script.unload();
        System.out.println("Game? "+quake.isRunning()+" World? "+ best_save.isRunning()+" VPN? "+southVPN.isRunning());
        System.out.println("->>> What if we try to load the world with the game on? :thinking:");
        script.load();
        System.out.println("Game? "+quake.isRunning()+" World?"+ best_save.isRunning()+" VPN? "+southVPN.isRunning());
        System.out.println("->>> Woa, the game is opened automatically! :000");


    }
}
