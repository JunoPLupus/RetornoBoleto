package LeituraArquivoRetornoStrategy;

import Entidades.Boleto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

abstract class LeituraArquivoRetornoTemplate implements LeituraArquivoRetorno {

        @Override
        public List<Boleto> lerArquivo(String nomeArquivo) {
            System.out.println("\nLeitura do arquivo: " + nomeArquivo);
            System.out.println("-----------------------------------------------");

            final List<String> linhasArquivo = lerLinhasArquivo(nomeArquivo);
            List<Boleto> boletos = new ArrayList<>(linhasArquivo.size());

            for (String linha : linhasArquivo) {
                var boleto = criarBoleto(linha);

                boletos.add(boleto);
                System.out.println(boleto);
            }
            System.out.println("-----------------------------------------------");
            return boletos;
        }

        protected abstract Boleto criarBoleto(String linha);

        public List<String> lerLinhasArquivo(String nomeArquivo) {
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


}
