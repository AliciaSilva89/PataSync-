package br.com.patasync.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Atendimento {

    private Animal animal;
    private Tutor tutor;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Veterinario responsavelPrincipal;
    private List<Procedimento> procedimentos;
    private List<Medicacao> medicacoes;
    private List<AcaoAtendimento> historicoAcoes;

    public Atendimento(Animal animal,
                       Tutor tutor,
                       LocalDateTime dataHoraInicio,
                       Veterinario responsavelPrincipal) {
        this.animal = animal;
        this.tutor = tutor;
        this.dataHoraInicio = dataHoraInicio;
        this.responsavelPrincipal = responsavelPrincipal;
        this.procedimentos = new ArrayList<>();
        this.medicacoes = new ArrayList<>();
        this.historicoAcoes = new ArrayList<>();
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public Veterinario getResponsavelPrincipal() {
        return responsavelPrincipal;
    }

    public void setResponsavelPrincipal(Veterinario responsavelPrincipal) {
        this.responsavelPrincipal = responsavelPrincipal;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void adicionarProcedimento(Procedimento procedimento) {
        if (procedimento != null) {
            procedimentos.add(procedimento);
        }
    }

    public List<Medicacao> getMedicacoes() {
        return medicacoes;
    }

    public void adicionarMedicacao(Medicacao medicacao) {
        if (medicacao != null) {
            medicacoes.add(medicacao);
        }
    }

    public List<AcaoAtendimento> getHistoricoAcoes() {
        return historicoAcoes;
    }

    public void adicionarAcao(AcaoAtendimento acao) {
        if (acao != null) {
            historicoAcoes.add(acao);
        }
    }

    public long calcularDuracaoEmMinutos() {
        if (dataHoraInicio != null && dataHoraFim != null) {
            return Duration.between(dataHoraInicio, dataHoraFim).toMinutes();
        }
        return 0;
    }

    public double calcularTotal() {
        double total = 0.0;

        for (Procedimento p : procedimentos) {
            total += p.getValorBase();
        }

        for (Medicacao m : medicacoes) {
            total += m.getValorTotal();
        }

        return total;
    }
}