package TextReader;

import java.text.Normalizer;
import java.util.LinkedList;
import java.util.Queue;

public class NormalizationFilter extends TextReaderDecorator {
    private Queue<String> queue = new LinkedList<>();

    public NormalizationFilter(TextReaderInterface obj) {
        super(obj);
        while(obj.hasNext()) {
            String str = obj.next();
            str = str.replaceAll("\\p{Punct}","");
            String newStr = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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
