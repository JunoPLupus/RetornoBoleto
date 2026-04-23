package leituraArquivoRetorno;

import entidades.Boleto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static processador.ProcessadorBoletos.FORMATO_DATA;
import static processador.ProcessadorBoletos.FORMATO_DATA_HORA;

public class LeituraArquivoRetornoBradesco {
    public static Boleto criarBoleto(String linha) {
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
