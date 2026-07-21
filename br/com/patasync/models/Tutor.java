package br.com.patasync.models;

import java.util.ArrayList;
import java.util.List;

public class Tutor extends Pessoa {

    private List<Animal> animais;

    public Tutor(String nome,
                 String cpf,
                 String telefone,
                 String endereco,
                 String profissao) {
        super(nome, cpf, telefone, endereco, profissao);
        this.animais = new ArrayList<>();
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void adicionarAnimal(Animal animal) {
        if (animal != null && !animais.contains(animal)) {
            animais.add(animal);
        }
    }

    public void removerAnimal(Animal animal) {
        animais.remove(animal);
    }
}