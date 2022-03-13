package ex1;

import java.util.Scanner;

public class IPluginCalculator implements IPlugin {

    @Override
    public void fazQualQuerCoisa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insere dois números em duas linhas distintas que eu somo-os :).");
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("A soma desses dois números é "+a+b+".");
    }
}
