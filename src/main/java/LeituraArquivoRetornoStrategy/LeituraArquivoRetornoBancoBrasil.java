package LeituraArquivoRetornoStrategy;

import java.time.LocalDate;
import Entidades.Boleto;

public class LeituraArquivoRetornoBancoBrasil extends LeituraArquivoRetornoTemplate {

    @Override
    protected Boleto criarBoleto(String linha) {
        var boleto = new Boleto();
        String[] vetor = linha.split(",");
        var indice = 0;

        boleto.setId(Integer.parseInt(vetor[indice++]));
        boleto.setCodBanco(vetor[indice++]);
        boleto.setDataVencimento(LocalDate.parse(vetor[indice++], FORMATO_DATA));
        boleto.setDataPagamento(
                LocalDate.parse(vetor[indice++], FORMATO_DATA).atStartOfDay());
        boleto.setCpfCliente(vetor[indice++]);
        boleto.setValor(Double.parseDouble(vetor[indice++]));
        boleto.setMulta(Double.parseDouble(vetor[indice++]));
        boleto.setJuros(Double.parseDouble(vetor[indice]));

        return boleto;
    }
}
