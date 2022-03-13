package Ex2;

import java.util.ArrayList;
import java.util.List;

public class SocialSecurity {
    private List<Person> resgistered = new ArrayList<>();

    public void regist(Person p) {
        this.resgistered.add(p);
    }
}
