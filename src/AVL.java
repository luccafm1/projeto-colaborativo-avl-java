import java.io.File;

public class AVL<T extends Comparable<T>> {
    private No<T> raiz;

    public AVL() {
        this.raiz = null;
    }

    public AVL(No<T> raiz) {
        this.raiz = raiz;
    }

    public No<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(No<T> raiz) {
        this.raiz = raiz;
    }

    public No<T> busca(T elemento){
        if (existe(raiz, elemento)){
            return busca(raiz, elemento);
        }
        return null;
    }

    public void inserir(T elemento, File f) {
        if (existe(raiz, elemento)) {
            busca(raiz, elemento).atualizar(f);
        } else {
            raiz = inserirRecursivo(raiz, elemento, f);
        }
    }

    private No<T> inserirRecursivo(No<T> no, T elemento, File f) {
        if (no == null) {
            No<T> novo_no =  new No<>(elemento);
            novo_no.atualizar(f);
            return novo_no;
        }

        if (elemento.compareTo(no.getElemento()) < 0) {
            no.setEsquerda(inserirRecursivo(no.getEsquerda(), elemento, f));
        } else if (elemento.compareTo(no.getElemento()) > 0) {
            no.setDireita(inserirRecursivo(no.getDireita(), elemento, f));
        } else {
            no.atualizar(f);
            return no;
        }
        return balancear(no);
    }

    public boolean existe(No<T> no, T elemento) {
        if (no == null) {
            return false;
        }

        if (elemento.compareTo(no.getElemento()) < 0) {
            return existe(no.getEsquerda(), elemento);
        } else if (elemento.compareTo(no.getElemento()) > 0) {
            return existe(no.getDireita(), elemento);
        } else {
            return true;
        }
    }

    public No<T> busca(No<T> no, T elemento) {
        if (no == null) {
            return null;
        }

        if (elemento.compareTo(no.getElemento()) < 0) {
            return busca(no.getEsquerda(), elemento);
        } else if (elemento.compareTo(no.getElemento()) > 0) {
            return busca(no.getDireita(), elemento);
        } else {
            return no;
        }
    }

    public void remover(T elemento) {
        raiz = removerRecursivo(raiz, elemento);
    }

    private No<T> removerRecursivo(No<T> no, T elemento) {
        if (no == null) {
            return no;
        }

        if (elemento.compareTo(no.getElemento()) < 0) {
            no.setEsquerda(removerRecursivo(no.getEsquerda(), elemento));
        } else if (elemento.compareTo(no.getElemento()) > 0) {
            no.setDireita(removerRecursivo(no.getDireita(), elemento));
        } else {
            if (no.getEsquerda() == null || no.getDireita() == null) {
                No<T> temp = (no.getEsquerda() != null) ? no.getEsquerda() : no.getDireita();

                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else {
                No<T> temp = noMinimo(no.getDireita());
                no.setElemento(temp.getElemento());
                no.setDireita(removerRecursivo(no.getDireita(), temp.getElemento()));
            }
        }

        if (no == null) {
            return no;
        }

        return balancear(no);
    }

    private No<T> noMinimo(No<T> no) {
        No<T> atual = no;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    private No<T> balancear(No<T> no) {
        int fatorBalanceamento = fatorBalanceamento(no);

        // Rotação à direita
        if (fatorBalanceamento > 1 && fatorBalanceamento(no.getEsquerda()) >= 0) {
            return rotacaoDireita(no);
        }

        // Rotação à esquerda
        if (fatorBalanceamento < -1 && fatorBalanceamento(no.getDireita()) <= 0) {
            return rotacaoEsquerda(no);
        }

        // Rotação dupla esquerda-direita
        if (fatorBalanceamento > 1 && fatorBalanceamento(no.getEsquerda()) < 0) {
            no.setEsquerda(rotacaoEsquerda(no.getEsquerda()));
            return rotacaoDireita(no);
        }

        // Rotação dupla direita-esquerda
        if (fatorBalanceamento < -1 && fatorBalanceamento(no.getDireita()) > 0) {
            no.setDireita(rotacaoDireita(no.getDireita()));
            return rotacaoEsquerda(no);
        }

        return no;
    }

    private No<T> rotacaoDireita(No<T> y) {
        No<T> x = y.getEsquerda();
        No<T> x2 = x.getDireita();

        x.setDireita(y);
        y.setEsquerda(x2);

        return x;
    }

    private No<T> rotacaoEsquerda(No<T> x) {
        No<T> y = x.getDireita();
        No<T> y2 = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(y2);

        return y;
    }

    private int fatorBalanceamento(No<T> no) {
        if (no == null) {
            return 0;
        }
        return altura(no.getEsquerda()) - altura(no.getDireita());
    }

    public void printTree(No<T> no, int level) {
        if (no == null) {
            return;
        }

        System.out.println("|    ".repeat(level) + no.getElemento());
        no.getTermos().mostraLista();
        if (no.getEsquerda() != null || no.getDireita() != null) {
            printTree(no.getEsquerda(), level + 1);
            printTree(no.getDireita(), level + 1);
        }
    }

    public void imprimeArvore() {
        printTree(raiz, 0);
    }

    public int altura(No<T> no){
        if (no == null){
            return -1;
        }

        int esquerda = altura(no.getEsquerda());
        int direita = altura(no.getDireita());

        return Math.max(esquerda, direita);

    }
}
