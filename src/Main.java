import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Primeiro, vamos ler os arquivos . . .
        String[] filedirs = new String[10];

        // a. definir os caminhos
        String absolute_path = "C:\\Users\\Lucca\\Desktop\\puc\\java\\projetoColaborativo\\src\\";
        filedirs[0] = absolute_path+"arquivo1.txt";
        filedirs[1] = absolute_path+"arquivo2.txt";
        filedirs[2] = absolute_path+"arquivo3.txt";

        // b. extrair todos os arquivos
        AVL<String> contaPalavras = new AVL<>();

        for (String filedir : filedirs) {
            if (filedir != null) {
                File f = new File(filedir);
                System.out.print("> Carregando arquivo " + f.getName());
                try {
                    Scanner sc = new Scanner(f);
                    int linhas = 0;
                    while (sc.hasNextLine()) {
                        linhas++;
                        String[] palavras = sc.nextLine().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
                        for (String palavra : palavras) {
                            contaPalavras.inserir(palavra, f);
                        }
                    }
                    System.out.printf(" . . . arquivo carregado com sucesso! (%d linhas lidas)\n", linhas);
                } catch (FileNotFoundException e) {
                    System.out.println("> Arquivo " + filedir + " não encontrado.");
                }
            }
        }

        // c. entrada usuário
        System.out.print("\nTermo a ser procurado\n>>> ");
        Scanner sc = new Scanner(System.in);
        String termo = sc.nextLine();

        // d. gerar relatório
        String output = "Termo '"+termo+"'\n\n";
        if (contaPalavras.existe(contaPalavras.getRaiz(), termo)) {
            No<String> noTermo = contaPalavras.busca(termo);
            noTermo.getTermos().mostraLista();
            Lista.NoLE itr = noTermo.getTermos().getHead();
            while (itr != null) {
                output += "> " + itr.getDado().getArquivo() + ": " + itr.getDado().getR() + "\n";
                itr = itr.getProx();
            }
        } else {
            output += " não encontrado.";
        }

        try {
            File relatorio = new File(absolute_path + "relatorio.txt");
            if (relatorio.exists()) {
                relatorio.delete();
            }

            relatorio.createNewFile();
            System.out.println("Arquivo relatório criado.");
            FileWriter fw = new FileWriter(absolute_path + "relatorio.txt");
            fw.write(output);
            fw.close();
            System.out.println("Arquivo relatório preenchido com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}