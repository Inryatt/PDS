package ex3;

public interface LivroState {
    public void resgitar();
    public void reservar();
    public void cancelar();
    public void requisitar();
    public void devolver();
    public void setLivro(Livro livro);
    public String getState();
}
