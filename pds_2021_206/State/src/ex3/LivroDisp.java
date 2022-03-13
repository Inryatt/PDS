package ex3;

public class LivroDisp implements LivroState {
    private Livro livro;

    @Override
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getState() {
        return "Disponível";
    }

    @Override
    public void resgitar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void reservar() {
        livro.changeState(new LivroRes());
    }

    @Override
    public void cancelar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void requisitar() {
        livro.changeState(new LivroEmp());
    }

    @Override
    public void devolver() {
        System.out.println("Operação não disponível.");
    }
}
