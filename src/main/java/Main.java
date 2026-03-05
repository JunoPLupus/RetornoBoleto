import LeituraArquivoRetornoStrategy.LeituraArquivoRetornoBancoBrasil;
import LeituraArquivoRetornoStrategy.LeituraArquivoRetornoBradesco;

/*
    * Aluno: Juno Piazza Lopes
    * Padrões utilizados: Strategy e Template Method
 */
public class Main {
    public static void main(String[] args) {
        ProcessadorBoletos processador = new ProcessadorBoletos();

        processador.setLeituraArquivoRetorno(new LeituraArquivoRetornoBancoBrasil());
        processador.processarBoleto("banco-brasil.csv");

        processador.setLeituraArquivoRetorno(new LeituraArquivoRetornoBradesco());
        processador.processarBoleto("bradesco.csv");
    }
}
