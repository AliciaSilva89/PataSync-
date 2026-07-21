package br.com.patasync.models;

public class Atendente extends Funcionario {

    public Atendente(String nome,
                     String cpf,
                     String telefone,
                     String endereco,
                     String profissao,
                     double salario) {
        super(nome, cpf, telefone, endereco, profissao, salario);
    }

    @Override
    public void registrarAtendimento(Animal animal, Atendimento atendimento) {
        // Implementar lógica de abrir/encerrar atendimento,
        // por exemplo criar AcaoAtendimento do tipo ABERTURA ou ENCERRAMENTO.
    }
}
