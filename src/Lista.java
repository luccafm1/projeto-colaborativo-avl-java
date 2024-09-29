public class Lista {
    /**
     * ================================
     * = Lista simplesmente encadeada =
     * ================================
     *
     * boolean vazia(); // Verifica se a lista L está vazia
     * void insere_primeiro(int info); // Insere o elemento info como primeiro na lista L
     * void insere_depois(Node * NoLE, int info); // Insere o elemento info depois do nó NoLE
     * void insere_ultimo(int info); // Insere o elemento info como último na lista L
     * void insere_ordenado(int info); // Insere o elemento info de maneira ordenada na lista
     * void mostra_lista(); // Mostra em Tela os Elementos da Lista L
     * int retira_primeiro(); // Retira o primeiro elemento da Lista e retorna o valor da info do NoLE
     * int retira_ultimo(); // Retira o último elemento da Lista e retorna a informação do Nó
     * int retira_depois(Node NoLE); // Retira o elemento posterior ao nó NoLE e retorna a sua
     * informação
     * int ultimo_elemento(); // Retorna a informação do último elemento da Lista
     * */

    public class NoLE {
        private NoLE prox;
        private Termo dado;

        public NoLE(Termo dado, NoLE prox){
            this.prox = prox;
            this.dado = dado;
        }

        public NoLE getProx() {
            return prox;
        }

        public void setProx(NoLE prox) {
            this.prox = prox;
        }

        public Termo getDado() {
            return dado;
        }

        public void setDado(Termo dado) {
            this.dado = dado;
        }
    }

    private NoLE head;
    private NoLE tail;

    public Lista(){
        this.head = this.tail = null;
    }

    public NoLE getHead() {
        return head;
    }

    public NoLE getTail() {
        return tail;
    }

    public NoLE getNo(int i){
        NoLE atual = this.head;
        int iterator = 0;
        while (atual != null){
            if (iterator == i){
                break;
            }
            atual = atual.getProx();
            iterator++;
        }
        return atual;
    }

    public Termo retiraDepois(NoLE p){
        NoLE removido = p.getProx();
        p.setProx(p.getProx().getProx());
        return removido.getDado();
    }

    public Termo retiraPrimeiro(){
        NoLE removido = this.head;
        this.head = this.head.getProx();
        return removido.getDado();
    }

    public Termo retiraUltimo(){
        NoLE removido = this.tail;
        NoLE atual = this.head;
        while (atual.getProx() != this.tail) {
            atual = atual.getProx();
        }
        this.tail = atual;
        this.tail.setProx(null);
        return removido.getDado();
    }

    public Termo ultimoElemento(){
        return this.tail.getDado();
    }

    public boolean ordenado(){
        NoLE atual = this.head;
        if (!this.vazia()) {
            while (atual.getProx() != null) {
                if (atual.getDado().getR() > atual.getProx().getDado().getR()) {
                    return false;
                }
                atual = atual.getProx();
            }
        }
        return true;
    }

    public boolean vazia(){
        return this.tail == null;
    }

    public void insereDepois(NoLE p, Termo dado) {
        NoLE novo = new NoLE(dado, p.getProx());
        if (this.vazia()){
            this.head = novo;
            this.tail = novo;
        } else {
            p.setProx(novo);
            if (p == this.tail) {
                this.tail = novo;
            }
        }
    }

    public void insereOrdenado(Termo dado) {
        NoLE novo = new NoLE(dado, null);
        if (this.vazia()) {
            this.head = novo;
            this.tail = novo;
        } else if (dado.getR() <= this.head.getDado().getR()) {
            novo.setProx(this.head);
            this.head = novo;
        } else if (dado.getR() >= this.tail.getDado().getR()) {
            this.tail.setProx(novo);
            this.tail = novo;
        } else {
            NoLE atual = this.head;
            NoLE prev = null;

            while (atual != null && dado.getR() > atual.getDado().getR()) {
                prev = atual;
                atual = atual.getProx();
            }

            novo.setProx(atual);
            prev.setProx(novo);
        }
    }


    public void inserePrimeiro(Termo dado){
        NoLE novo = new NoLE(dado, this.head);
        this.head = novo;
        if (this.vazia()) {
            this.tail = novo;
        }
    }

    public void insereUltimo(Termo dado){
        NoLE novo = new NoLE(dado, null);
        if (this.vazia()){
            this.head = novo;
            this.tail = novo;
        } else {
            this.tail.setProx(novo);
            this.tail = novo;
        }
    }

    public void mostraLista() {
        NoLE atual = this.head;

        System.out.print("\n");
        while (atual != null) {
            NoLE prox = atual.getProx();
            System.out.printf("(%s : %d) -> ", atual.getDado().getArquivo(), atual.getDado().getR());
            if (prox == null) {
                System.out.print("null");
            }
            atual = prox;
        }
        System.out.print("\n");
    }
    
}
