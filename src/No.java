import java.io.File;

public class No<T extends Comparable<T>> {
    private T elemento;
    private Lista termos; // Lista de Termo
    private No<T> esquerda;
    private No<T> direita;

    public No(T elemento) {
        this.elemento = elemento;
        this.termos = new Lista();
        this.esquerda = null;
        this.direita = null;
    }

    public No(T elemento, No<T> esquerda, No<T> direita) {
        this.elemento = elemento;
        this.termos = new Lista();
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public Lista getTermos(){
        return termos;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public No<T> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No<T> esquerda) {
        this.esquerda = esquerda;
    }

    public No<T> getDireita() {
        return direita;
    }

    public void atualizar(File f){
        Lista.NoLE itr = termos.getHead();
        boolean found = false;
        while (itr != null){
            if (itr.getDado().getArquivo().equals(f.getName())){
                itr.getDado().atualizarR(1);
                found = true;
                break;

            }
            itr = itr.getProx();
        }

        if (!found) {
            termos.insereOrdenado(new Termo(f.getName()));
        }
    }

    public void setDireita(No<T> direita) {
        this.direita = direita;
    }
}
