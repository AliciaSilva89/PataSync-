package br.com.patasync.models;

public class Veterinario extends Funcionario {

    public Veterinario(String nome,
                       String cpf,
                       String telefone,
                       String endereco,
                       String profissao,
                       double salario) {
        super(nome, cpf, telefone, endereco, profissao, salario);
    }

    @Override
    public void registrarAtendimento(Animal animal, Atendimento atendimento) {
        // Aqui você pode implementar a lógica específica do veterinário,
        // por exemplo registrar diagnóstico, procedimentos, etc.
        // Por enquanto, deixe a lógica simples e evolua depois junto com Atendimento.
    }

    public void registrarDiagnostico(Atendimento atendimento, String descricao) {
        // Implementar lógica para registrar diagnóstico no atendimento
        // (por exemplo, criar uma AcaoAtendimento do tipo DIAGNOSTICO).
    }
}