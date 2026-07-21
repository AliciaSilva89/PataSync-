package br.com.patasync.models;

public class AssistenteVeterinario extends Funcionario {

    public AssistenteVeterinario(String nome,
                                 String cpf,
                                 String telefone,
                                 String endereco,
                                 String profissao,
                                 double salario) {
        super(nome, cpf, telefone, endereco, profissao, salario);
    }

    @Override
    public void registrarAtendimento(Animal animal, Atendimento atendimento) {
        // Implementar a lógica de primeiro atendimento / triagem.
        // Exemplo: criar uma AcaoAtendimento do tipo PRIMEIRO_ATENDIMENTO.
    }

    public void aplicarMedicacao(Atendimento atendimento, Medicacao medicacao) {
        // Implementar a lógica de aplicação de medicação,
        // associando a Medicacao ao Atendimento e registrando uma AcaoAtendimento do tipo MEDICACAO.
    }
}