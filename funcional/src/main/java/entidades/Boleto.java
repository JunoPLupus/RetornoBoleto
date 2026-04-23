package entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Boleto {
    private int id;
    private String codBanco;
    private LocalDate dataVencimento;
    private LocalDateTime dataPagamento;
    private String cpfCliente;
    private double valor;
    private double multa;
    private double juros;
    private String agencia;
    private String contaBancaria;

    public Boleto() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    @Override
    public String toString() {
        String informacoesBoleto = "Boleto "+ id +
                " | Banco:'" + codBanco + '\'' +
                " | Data de Vencimento:" + dataVencimento +
                " | Data de Pagamento:" + dataPagamento +
                " | Cpf do Cliente:'" + cpfCliente + '\'' +
                " | Valor: R$" + valor;

        if(multa > 0) informacoesBoleto += " | Multa: R$" + multa;
        if(juros > 0) informacoesBoleto += " | Juros: R$" + juros;
        if(agencia != null) informacoesBoleto += " | Agência:'" + agencia + '\'';
        if(contaBancaria != null) informacoesBoleto += " | Conta Bancária:'" + contaBancaria + '\'';

        return informacoesBoleto;
    }
}

