package br.com.patasync.models;

import java.time.LocalDateTime;

public class AcaoAtendimento {

    private Funcionario responsavel;
    private TipoAcaoAtendimento tipo;
    private String descricao;
    private LocalDateTime dataHora;
    private Procedimento procedimento; // opcional
    private Medicacao medicacao;       // opcional

    public AcaoAtendimento(Funcionario responsavel,
                           TipoAcaoAtendimento tipo,
                           String descricao,
                           LocalDateTime dataHora) {
        this.responsavel = responsavel;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public TipoAcaoAtendimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcaoAtendimento tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public Medicacao getMedicacao() {
        return medicacao;
    }

    public void setMedicacao(Medicacao medicacao) {
        this.medicacao = medicacao;
    }
}