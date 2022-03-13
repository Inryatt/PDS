package TextReader;

public class Main {
    public static void main(String[] args) {
        TextReaderInterface reader = new TextReader("lab07\\Input\\input.txt");
        System.out.println(reader.next());
        reader = new NormalizationFilter(new TextReader("lab07\\Input\\input.txt"));
        System.out.println(reader.next());
        reader = new VowelFilter(new TermFilter(new TextReader("lab07\\Input\\input.txt")));
        System.out.println(reader.next());
    }
}
