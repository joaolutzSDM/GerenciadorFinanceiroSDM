package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Conta implements Serializable {

    private Integer id;
    private String nome;
    private BigDecimal saldo;

    private static int increment = 1;

    private List<Transacao> transacoes;

    public Conta()
    {
        id = increment++;
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
        return getCurrencyBigDecimal(saldo);
    }

    public static String getCurrencyBigDecimal(BigDecimal valor) {
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
        return saldo.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}