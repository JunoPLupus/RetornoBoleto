package processador;

import entidades.Boleto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.time.format.DateTimeFormatter.ofPattern;

public class ProcessadorBoletos {
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter FORMATO_DATA_HORA = ofPattern("dd/MM/yyyy HH:mm:ss");
    public Function<String, Boleto> criarBoleto;

    public ProcessadorBoletos(Function<String, Boleto>  criarBoleto) {
        this.criarBoleto = criarBoleto;
    }

    public void setCriarBoleto(Function<String, Boleto> criarBoleto) {
        this.criarBoleto = criarBoleto;
    }

    public final void processar(String arquivo){
        List<Boleto> boletos = lerArquivo(arquivo);
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    private List<Boleto> lerArquivo(String nomeArquivo) {

        final List<String> linhasArquivo = lerLinhasArquivo(nomeArquivo);
        List<Boleto> boletos = new ArrayList<>(linhasArquivo.size());

        for (String linha : linhasArquivo) {
            var boleto = criarBoleto.apply(linha);

            boletos.add(boleto);
        }
        return boletos;
    }

    private List<String> lerLinhasArquivo(String nomeArquivo) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nomeArquivo);

        if(inputStream == null){
            throw new RuntimeException("Arquivo não encontrado!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<String> linhas = new ArrayList<>();
            String linha;

            while ((linha = reader.readLine()) != null) {
                if(!linha.isBlank()) linhas.add(linha);
            }
            return linhas;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
