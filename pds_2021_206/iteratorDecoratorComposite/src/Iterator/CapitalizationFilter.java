package TextReader;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class CapitalizationFilter extends TextReaderDecorator {
    private Queue<String> queue = new LinkedList<>();

    public CapitalizationFilter(TextReaderInterface obj) {
        super(obj);
        while(obj.hasNext()) {
            String str = obj.next();
            str = str.toLowerCase();
            String newStr = str.substring(0,1).toUpperCase() + str.substring(1,str.length()-1) + Character.toUpperCase(str.charAt(str.length()-1));
            queue.add(newStr);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public String next() {
        return queue.remove();
    }
}
