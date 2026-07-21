package br.com.patasync.services;

import br.com.patasync.models.*;

import java.time.LocalDateTime;

public class AtendimentoService {

    public Atendimento abrirAtendimento(Atendente atendente,
                                        Animal animal,
                                        Veterinario responsavelPrincipal) {
        // Cria um novo atendimento
        Atendimento atendimento = new Atendimento(
            animal,
            LocalDateTime.now(),
            responsavelPrincipal
        );

        // Vincula atendimento ao animal
        animal.adicionarAtendimento(atendimento);

        // Registra ação de abertura
        AcaoAtendimento acao = new AcaoAtendimento(
            atendente,
            TipoAcaoAtendimento.ABERTURA,
            "Atendimento aberto pelo atendente.",
            LocalDateTime.now()
        );

        atendimento.adicionarAcao(acao);

        return atendimento;
    }

    public void registrarPrimeiroAtendimento(AssistenteVeterinario assistente,
                                             Atendimento atendimento) {
        AcaoAtendimento acao = new AcaoAtendimento(
            assistente,
            TipoAcaoAtendimento.PRIMEIRO_ATENDIMENTO,
            "Primeiro atendimento / triagem realizado pelo assistente.",
            LocalDateTime.now()
        );

        atendimento.adicionarAcao(acao);
    }

    public void aplicarMedicacao(AssistenteVeterinario assistente,
                                 Atendimento atendimento,
                                 Medicacao medicacao) {
        // Adiciona medicação ao atendimento
        atendimento.adicionarMedicacao(medicacao);

        // Registra ação de medicação
        AcaoAtendimento acao = new AcaoAtendimento(
            assistente,
            TipoAcaoAtendimento.MEDICACAO,
            "Medicação aplicada pelo assistente.",
            LocalDateTime.now()
        );

        acao.setMedicacao(medicacao);
        atendimento.adicionarAcao(acao);
    }

    public void registrarDiagnostico(Veterinario veterinario,
                                     Atendimento atendimento,
                                     String descricao) {
        AcaoAtendimento acao = new AcaoAtendimento(
            veterinario,
            TipoAcaoAtendimento.DIAGNOSTICO,
            descricao,
            LocalDateTime.now()
        );

        atendimento.adicionarAcao(acao);
    }

    public void registrarProcedimento(Veterinario veterinario,
                                      Atendimento atendimento,
                                      Procedimento procedimento) {
        // Adiciona procedimento ao atendimento
        atendimento.adicionarProcedimento(procedimento);

        // Registra ação de procedimento
        AcaoAtendimento acao = new AcaoAtendimento(
            veterinario,
            TipoAcaoAtendimento.PROCEDIMENTO,
            "Procedimento realizado pelo veterinário.",
            LocalDateTime.now()
        );

        acao.setProcedimento(procedimento);
        atendimento.adicionarAcao(acao);
    }

    public void encerrarAtendimento(Atendente atendente,
                                    Atendimento atendimento) {
        atendimento.setDataHoraFim(LocalDateTime.now());

        AcaoAtendimento acao = new AcaoAtendimento(
            atendente,
            TipoAcaoAtendimento.ENCERRAMENTO,
            "Atendimento encerrado pelo atendente.",
            LocalDateTime.now()
        );

        atendimento.adicionarAcao(acao);
    }
}