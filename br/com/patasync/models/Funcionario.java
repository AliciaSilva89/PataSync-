package br.com.patasync.models;

public abstract class Funcionario extends Pessoa {

    private double salario;

    public Funcionario(String nome,
                       String cpf,
                       String telefone,
                       String endereco,
                       String profissao,
                       double salario) {
        super(nome, cpf, telefone, endereco, profissao);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public abstract void registrarAtendimento(Animal animal, Atendimento atendimento);
}
