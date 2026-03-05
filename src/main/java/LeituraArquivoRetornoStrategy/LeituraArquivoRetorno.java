package LeituraArquivoRetornoStrategy;

import java.time.format.DateTimeFormatter;
import java.util.List;
import Entidades.Boleto;

import static java.time.format.DateTimeFormatter.ofPattern;

public interface LeituraArquivoRetorno {
    DateTimeFormatter FORMATO_DATA = ofPattern("dd/MM/yyyy");
    DateTimeFormatter FORMATO_DATA_HORA = ofPattern("dd/MM/yyyy HH:mm:ss");

    List<Boleto> lerArquivo(String nomeArquivo);
}
