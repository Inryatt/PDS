package TextReader;

public class TextReaderDecorator implements TextReaderInterface {
    TextReaderInterface wrappee;

    public TextReaderDecorator(TextReaderInterface obj) {
        wrappee = obj;
    }

    @Override
    public boolean hasNext() {
        return wrappee.hasNext();
    }

    @Override
    public String next() {
        return wrappee.next();
    }
}
