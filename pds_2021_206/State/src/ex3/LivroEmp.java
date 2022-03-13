package ex3;

public class LivroEmp implements LivroState {
    private Livro livro;

    @Override
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getState() {
        return "Emprestado";
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
        System.out.println("Operação não disponível.");
    }

    @Override
    public void requisitar() {
        System.out.println("Operação não disponível.");
    }

    @Override
    public void devolver() {
        livro.changeState(new LivroDisp());
    }
}
