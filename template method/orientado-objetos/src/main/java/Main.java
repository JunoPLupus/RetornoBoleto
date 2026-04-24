import leituraArquivoRetorno.LeituraArquivoRetornoBancoBrasil;
import leituraArquivoRetorno.LeituraArquivoRetornoBradesco;
import processador.ProcessadorBoletos;
/*
    @author Juno Piazza Lopes
 */
public class Main {
    public static void main(String[] args) {
        ProcessadorBoletos processador = new ProcessadorBoletos(new LeituraArquivoRetornoBancoBrasil());
        processador.processarBoleto("banco-brasil.csv");

        processador.setLeituraArquivoRetorno(new LeituraArquivoRetornoBradesco());
        processador.processarBoleto("bradesco.csv");
    }
}
