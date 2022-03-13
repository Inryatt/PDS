package Ex2;

import java.util.ArrayList;
import java.util.List;

public class Insurance {
    private List<Person> resgistered = new ArrayList<>();
    private String name;

    public Insurance(String name) {
        this.name = name;
    }

    public void regist(Person p) {
        this.resgistered.add(p);
    }
}
