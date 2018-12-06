package br.edu.ifsp.scl.sdm.gerenciadorfinanceirosdm.model;

public class Transacao {

    private String nome;
    private NaturezaTransacao naturezaTransacao;
    private String tipo;
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

    public boolean isPeriodica() {
        return periodica;
    }

    public void setPeriodica(boolean periodica) {
        this.periodica = periodica;
    }

}