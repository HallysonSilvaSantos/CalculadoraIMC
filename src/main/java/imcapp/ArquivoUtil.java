package imcapp;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    private static final String NOME_ARQUIVO = "dados_pessoas.txt";

    public static void salvar(Pessoa pessoa) {
        try (FileWriter fw = new FileWriter(NOME_ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(pessoa.getNome() + "," + pessoa.getAltura() + "," + pessoa.getPeso());
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pessoa> carregar() {
        List<Pessoa> lista = new ArrayList<>();

        try (FileReader fr = new FileReader(NOME_ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    String nome = partes[0];
                    double altura = Double.parseDouble(partes[1]);
                    double peso = Double.parseDouble(partes[2]);
                    lista.add(new Pessoa(nome, altura, peso));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
