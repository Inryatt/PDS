package TextReader;

import java.util.LinkedList;
import java.util.Queue;

public class VowelFilter extends TextReaderDecorator {
    private Queue<String> queue = new LinkedList<>();

    public VowelFilter(TextReaderInterface obj) {
        super(obj);
        while(obj.hasNext()) {
            String str = obj.next();
            String newStr = str.replaceAll("[aeiou]","");
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
