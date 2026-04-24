package leituraArquivoRetorno;

import entidades.Boleto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeituraArquivoRetornoBradesco implements LeituraArquivoRetorno {
    @Override
    public List<Boleto> lerArquivo(String nomeArquivo) {

        final List<String> linhasArquivo = lerLinhasArquivo(nomeArquivo);
        List<Boleto> boletos = new ArrayList<>(linhasArquivo.size());

        for (String linha : linhasArquivo) {
            var boleto = criarBoleto(linha);

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

    @Override
    public Boleto criarBoleto(String linha) {
        var boleto = new Boleto();
        String[] vetor = linha.split(",");
        var indice = 0;

        boleto.setId(Integer.parseInt(vetor[indice++]));
        boleto.setCodBanco(vetor[indice++]);
        boleto.setAgencia(vetor[indice++]);
        boleto.setContaBancaria(vetor[indice++]);
        boleto.setDataVencimento(LocalDate.parse(vetor[indice++], FORMATO_DATA));
        boleto.setDataPagamento(
                LocalDateTime.parse(vetor[indice++], FORMATO_DATA_HORA));
        boleto.setCpfCliente(vetor[indice++]);
        boleto.setValor(Double.parseDouble(vetor[indice++]));
        boleto.setMulta(Double.parseDouble(vetor[indice++]));
        boleto.setJuros(Double.parseDouble(vetor[indice]));

        return boleto;
    }
}
