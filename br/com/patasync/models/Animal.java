package br.com.patasync.models;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private double peso;
    private Tutor tutor;
    private List<Atendimento> historicoAtendimentos;

    public Animal(String nome,
                  String especie,
                  String raca,
                  int idade,
                  double peso,
                  Tutor tutor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
        this.tutor = tutor;
        this.historicoAtendimentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Atendimento> getHistoricoAtendimentos() {
        return historicoAtendimentos;
    }

    public void adicionarAtendimento(Atendimento atendimento) {
        if (atendimento != null && !historicoAtendimentos.contains(atendimento)) {
            historicoAtendimentos.add(atendimento);
        }
    }
}