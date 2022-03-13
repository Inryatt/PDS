package ex3;

public class Livro {
    private String titulo;
    private int ISBN;
    private int ano;
    private String autor;
    private LivroState state;

    public Livro(String titulo, int ISBN, int ano, String autor) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.ano = ano;
        this.autor = autor;
        this.state = new LivroInv();
        state.setLivro(this);
    }

    public void changeState(LivroState state) {
        this.state = state;
        state.setLivro(this);
    }

    public int getISBN() {
        return this.ISBN;
    }

    public String getAutor() {
        return this.autor;
    }

    public LivroState getState() {
        return this.state;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void resgitar() {
        state.resgitar();
    }

    public void reservar() {
        state.reservar();
    }

    public void cancelar() {
        state.cancelar();
    }

    public void requisitar() {
        state.requisitar();
    }

    public void devolver() {
        state.devolver();
    }
}
