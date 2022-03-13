package ex3;

public class LivroInv implements LivroState {
    private Livro livro;

    @Override
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getState() {
        return "Inventário";
    }

    @Override
    public void resgitar() {
        livro.changeState(new LivroDisp());
    }

    @Override
    public void reservar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void cancelar() {
        System.out.println("Operação não disponível.");
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
