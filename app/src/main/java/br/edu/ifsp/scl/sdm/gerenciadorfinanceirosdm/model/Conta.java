package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Conta {

    private String nome;
    private BigDecimal saldo;

    private List<Transacao> transacoes;

    public Conta() {
        transacoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getSaldoFormatado() {
        return getSaldoString(saldo);
    }

    public static String getSaldoString(BigDecimal valor) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        String formatado = nf.format(valor);
        if(formatado.contains(" ")) {
            return formatado;
        } else {
            String symbol = nf.getCurrency().getSymbol(locale);
            return formatado.replace(symbol, symbol.concat(" "));
        }
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public boolean isSaldoPositivo() {
        return saldo.compareTo(BigDecimal.ZERO) == 0;
    }

}