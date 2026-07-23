package br.com.patasync.models;

import java.time.LocalDate;

public class Atendente extends Funcionario {

    public Atendente(String nome,
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
              estadoCivil,
              salario);
    }
}