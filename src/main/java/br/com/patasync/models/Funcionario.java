package br.com.patasync.models;

import java.time.LocalDate;

public abstract class Funcionario extends Pessoa {

    private double salario;

    public Funcionario(String nome,
                       String cpf,
                       String telefone,
                       String email,
                       String logradouro,
                       String numero,
                       String complemento,
                       String cep,
                       String cidade,
                       String estado,
                       String profissao,
                       LocalDate dataNascimento,
                       String sexo,
                       String estadoCivil,
                       double salario) {

        super(nome,
              cpf,
              telefone,
              email,
              logradouro,
              numero,
              complemento,
              cep,
              cidade,
              estado,
              profissao,
              dataNascimento,
              sexo,
              estadoCivil);

        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}