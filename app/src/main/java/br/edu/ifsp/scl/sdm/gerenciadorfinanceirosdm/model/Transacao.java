package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transacao implements Serializable {

    private String nome;
    private NaturezaTransacao naturezaTransacao;
    private String tipo;
    private BigDecimal valor;
    private boolean periodica;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public NaturezaTransacao getNaturezaTransacao() {
        return naturezaTransacao;
    }

    public void setNaturezaTransacao(NaturezaTransacao naturezaTransacao) {
        this.naturezaTransacao = naturezaTransacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isPeriodica() {
        return periodica;
    }

    public void setPeriodica(boolean periodica) {
        this.periodica = periodica;
    }

}