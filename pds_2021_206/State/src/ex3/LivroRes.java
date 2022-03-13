package ex3;

public class LivroRes implements LivroState {
    private Livro livro;

    @Override
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getState() {
        return "Reservado";
    }

    @Override
    public void resgitar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void reservar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void cancelar() {
        livro.changeState(new LivroDisp());
    }

    @Override
    public void requisitar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void devolver() {
        System.out.println("Operação não disponível.");
    }
}
