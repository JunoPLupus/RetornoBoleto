package processador;

import entidades.Boleto;
import leituraArquivoRetorno.LeituraArquivoRetorno;
import leituraArquivoRetorno.LeituraArquivoRetornoBancoBrasil;
import leituraArquivoRetorno.LeituraArquivoRetornoBradesco;

import java.util.List;
import java.util.Objects;

public class ProcessadorBoletos {
    private LeituraArquivoRetorno leituraArquivoRetorno;

    public ProcessadorBoletos() {}

    public ProcessadorBoletos(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = Objects.requireNonNull(
                leituraArquivoRetorno,
                "LeituraArquivoRetorno não pode ser nulo"
        );
    }

    public void setLeituraArquivoRetorno(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = leituraArquivoRetorno;
    }

    private void imprimirBoletosNaTela(List<Boleto> boletos) {
        System.out.println("-----------------------------------------------");
        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    public void processarBoleto(String nomeArquivo) {
        List<Boleto> boletos = leituraArquivoRetorno.lerArquivo(nomeArquivo);
        imprimirBoletosNaTela(boletos);
    }
}
