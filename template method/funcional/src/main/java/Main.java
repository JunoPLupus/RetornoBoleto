import leituraArquivoRetorno.LeituraArquivoRetornoBancoBrasil;
import leituraArquivoRetorno.LeituraArquivoRetornoBradesco;
import processador.ProcessadorBoletos;
/*
    @author Juno Piazza Lopes
 */
static void main() {
    final var processador = new ProcessadorBoletos(
            LeituraArquivoRetornoBancoBrasil::criarBoleto);
    processador.processar("banco-brasil.csv");

    processador.setCriarBoleto(
            LeituraArquivoRetornoBradesco::criarBoleto);
    processador.processar("bradesco.csv");
}