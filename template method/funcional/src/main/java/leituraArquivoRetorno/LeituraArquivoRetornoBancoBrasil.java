package leituraArquivoRetorno;

import entidades.Boleto;
import processador.ProcessadorBoletos;

import java.time.LocalDate;

public class LeituraArquivoRetornoBancoBrasil {
    public static Boleto criarBoleto(String linha) {
        var boleto = new Boleto();
        String[] vetor = linha.split(",");
        var indice = 0;

        boleto.setId(Integer.parseInt(vetor[indice++]));
        boleto.setCodBanco(vetor[indice++]);
        boleto.setDataVencimento(LocalDate.parse(vetor[indice++], ProcessadorBoletos.FORMATO_DATA));
        boleto.setDataPagamento(LocalDate.parse(vetor[indice], ProcessadorBoletos.FORMATO_DATA).atStartOfDay());
        return boleto;
    }
}
