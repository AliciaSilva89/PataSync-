package br.com.patasync.models;

public class Medicacao {

    private int medicamentoId;
    private String nome;
    private String principioAtivo;
    private String classeIndicacao;
    private String dosagem;
    private String categoriaClinica;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private String observacoes;

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

    public int getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(int medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getClasseIndicacao() {
        return classeIndicacao;
    }

    public void setClasseIndicacao(String classeIndicacao) {
        this.classeIndicacao = classeIndicacao;
    }

    public String getCategoriaClinica() {
        return categoriaClinica;
    }

    public void setCategoriaClinica(String categoriaClinica) {
        this.categoriaClinica = categoriaClinica;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}