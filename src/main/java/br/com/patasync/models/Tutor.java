package br.com.patasync.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends Pessoa {

    private List<Animal> animais;

    public Tutor(String nome,
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
                 String estadoCivil) {

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