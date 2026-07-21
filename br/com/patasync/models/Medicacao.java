package br.com.patasync.models;

public class Medicacao {

    private String nome;
    private String dosagem;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    public Medicacao(String nome,
                     String dosagem,
                     int quantidade,
                     double valorUnitario) {
        this.nome = nome;
        this.dosagem = dosagem;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = quantidade * valorUnitario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = this.quantidade * this.valorUnitario;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
        this.valorTotal = this.quantidade * this.valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}