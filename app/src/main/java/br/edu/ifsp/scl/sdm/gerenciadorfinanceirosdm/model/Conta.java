package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

}