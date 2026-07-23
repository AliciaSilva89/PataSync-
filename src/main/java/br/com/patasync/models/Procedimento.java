package br.com.patasync.models;

public class Procedimento {

    private String descricao;
    private TipoProcedimento tipo;
    private double valorBase;

    public Procedimento(String descricao,
                        TipoProcedimento tipo,
                        double valorBase) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.valorBase = valorBase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoProcedimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoProcedimento tipo) {
        this.tipo = tipo;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }
}