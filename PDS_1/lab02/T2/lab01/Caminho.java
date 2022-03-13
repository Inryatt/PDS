import java.util.ArrayList;

public class Caminho {
    private ArrayList<Ponto> posicoes;
    private Direction direcao;

    public Caminho(){
        
    }
    
    public Caminho(ArrayList<Ponto> posicoes, Direction direcao) {
        this.posicoes = posicoes;
        this.direcao = direcao;
    }

    public Caminho(ArrayList<Ponto> posicoes) {
        this.posicoes = posicoes;
    }

    public ArrayList<Ponto> getPosicoes() {
        return posicoes;
    }

    public Direction getDirecao() {
        return direcao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((direcao == null) ? 0 : direcao.hashCode());
        result = prime * result + ((posicoes == null) ? 0 : posicoes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        Caminho other = (Caminho) obj;
        if (posicoes == null) {
            if (other.posicoes != null)
                return false;
        } else if (!posicoes.equals(other.posicoes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Caminho [direcao=" + direcao + ", posicoes=" + posicoes + "]";
    }

}
