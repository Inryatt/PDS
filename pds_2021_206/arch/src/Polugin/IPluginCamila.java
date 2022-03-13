package ex1;

import javax.crypto.spec.RC2ParameterSpec;
import java.util.Random;

public class IPluginCamila implements IPlugin{
    @Override
    public void fazQualQuerCoisa(){
        System.out.println("Which DnD class and race should you pick next? :thinking_emoji:");
        String[] Races = new String[]{"Human","Elf","Dwarf","Tiefling","Gnome","Halfling","Dragonborn","Half-elf","Half-orc","Genasi","Aasimar","Drow","Wood Elf","Aaraockra","Kenku","Tortle","Goblin"};
        String[] Classes = new String[]{"Artificer","Sorcerer","Wizard","Rogue","Fighter","Bard","Ranger","Cleric"};

        Random r = new Random();
        int i = r.nextInt(Classes.length);
        int j = r.nextInt(Races.length);
        System.out.println("You get a: "+Classes[i]+" "+Races[j]);
    }
}
