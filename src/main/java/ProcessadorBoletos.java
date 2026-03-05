import Entidades.Boleto;
import LeituraArquivoRetornoStrategy.LeituraArquivoRetorno;

import java.util.List;

public class ProcessadorBoletos {
    private LeituraArquivoRetorno leituraArquivoRetorno;

    public void setLeituraArquivoRetorno(LeituraArquivoRetorno leituraArquivoRetorno) {
        this.leituraArquivoRetorno = leituraArquivoRetorno;
    }

    public List<Boleto> processarBoleto(String nomeArquivo) {
        return leituraArquivoRetorno.lerArquivo(nomeArquivo);
    }
}
