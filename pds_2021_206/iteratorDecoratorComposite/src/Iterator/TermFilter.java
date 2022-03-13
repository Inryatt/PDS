package TextReader;

import java.util.LinkedList;
import java.util.Queue;

public class TermFilter extends TextReaderDecorator {
    private Queue<String> queue = new LinkedList<>();

    public TermFilter(TextReaderInterface obj) {
        super(obj);
        while(obj.hasNext()) {
            String[] line = obj.next().split(" ");
            for(String str : line) {
                queue.add(str);
            }
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
