public class Termo {
    private String arquivo;
    private int r;
    
    public Termo(String arquivo) {
        this.arquivo = arquivo;
        this.r = 1;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public int getR() {
        return r;

    }

    public void setR(int r) {
        this.r = r;
    }

    public void atualizarR(int x) {
        this.r = this.r + x;
    }

}
