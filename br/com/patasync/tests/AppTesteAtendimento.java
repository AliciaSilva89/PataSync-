package br.com.patasync.tests;

import br.com.patasync.models.*;
import br.com.patasync.services.AtendimentoService;

import java.util.Scanner;

public class AppTesteAtendimento {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. Dados do Tutor
        System.out.println("=== Cadastro de Tutor ===");
        System.out.print("Nome do tutor: ");
        String nomeTutor = scanner.nextLine();

        System.out.print("CPF do tutor: ");
        String cpfTutor = scanner.nextLine();

        System.out.print("Telefone do tutor: ");
        String telefoneTutor = scanner.nextLine();

        System.out.print("Endereço do tutor: ");
        String enderecoTutor = scanner.nextLine();

        System.out.print("Profissão do tutor: ");
        String profissaoTutor = scanner.nextLine();

        Tutor tutor = new Tutor(
            nomeTutor,
            cpfTutor,
            telefoneTutor,
            enderecoTutor,
            profissaoTutor
        );

        // 2. Dados do Animal
        System.out.println("\n=== Cadastro de Animal ===");
        System.out.print("Nome do animal: ");
        String nomeAnimal = scanner.nextLine();

        System.out.print("Espécie do animal: ");
        String especieAnimal = scanner.nextLine();

        System.out.print("Raça do animal: ");
        String racaAnimal = scanner.nextLine();

        System.out.print("Idade do animal (anos): ");
        int idadeAnimal = Integer.parseInt(scanner.nextLine());

        System.out.print("Peso do animal (kg): ");
        double pesoAnimal = Double.parseDouble(scanner.nextLine());

        Animal animal = new Animal(
            nomeAnimal,
            especieAnimal,
            racaAnimal,
            idadeAnimal,
            pesoAnimal,
            tutor
        );
        tutor.adicionarAnimal(animal);

        // 3. Funcionários (para teste podemos fixar ou também pedir via console)
        Atendente atendente = new Atendente(
            "Maria Atendente",
            "111.222.333-44",
            "34 98888-0000",
            "Av. Central, 10",
            "Atendente",
            2000.0
        );

        AssistenteVeterinario assistente = new AssistenteVeterinario(
            "Carlos Assistente",
            "555.666.777-88",
            "34 97777-0000",
            "Rua B, 45",
            "Assistente Veterinário",
            2500.0
        );

        Veterinario veterinario = new Veterinario(
            "Ana Veterinária",
            "999.888.777-66",
            "34 96666-0000",
            "Rua C, 80",
            "Médica Veterinária",
            5000.0
        );

        AtendimentoService atendimentoService = new AtendimentoService();

        // 4. Abrir atendimento
        Atendimento atendimento = atendimentoService.abrirAtendimento(
            atendente,
            animal,
            veterinario
        );

        // 5. Primeiro atendimento (triagem)
        atendimentoService.registrarPrimeiroAtendimento(
            assistente,
            atendimento
        );

        // 6. Entrada de dados de medicação
        System.out.println("\n=== Registro de Medicação ===");
        System.out.print("Nome da medicação: ");
        String nomeMedicacao = scanner.nextLine();

        System.out.print("Dosagem (ex: 20 mg): ");
        String dosagem = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Valor unitário (R$): ");
        double valorUnitario = Double.parseDouble(scanner.nextLine());

        Medicacao medicacao = new Medicacao(
            nomeMedicacao,
            dosagem,
            quantidade,
            valorUnitario
        );

        atendimentoService.aplicarMedicacao(
            assistente,
            atendimento,
            medicacao
        );

        // 7. Diagnóstico
        System.out.println("\n=== Registro de Diagnóstico ===");
        System.out.print("Descrição do diagnóstico: ");
        String descricaoDiagnostico = scanner.nextLine();

        atendimentoService.registrarDiagnostico(
            veterinario,
            atendimento,
            descricaoDiagnostico
        );

        // 8. Procedimento
        System.out.println("\n=== Registro de Procedimento ===");
        System.out.print("Descrição do procedimento: ");
        String descricaoProcedimento = scanner.nextLine();

        Procedimento procedimento = new Procedimento(
            descricaoProcedimento,
            TipoProcedimento.CONSULTA, // por enquanto fixo; depois você pode pedir tipo via console
            120.0                       // valor de exemplo; você pode pedir via console também
        );

        atendimentoService.registrarProcedimento(
            veterinario,
            atendimento,
            procedimento
        );

        // 9. Encerrar atendimento
        atendimentoService.encerrarAtendimento(
            atendente,
            atendimento
        );

        // 10. Mostrar resumo
        System.out.println("\n=== Resumo do Atendimento ===");
        System.out.println("Animal: " + atendimento.getAnimal().getNome());
        System.out.println("Tutor: " + atendimento.getAnimal().getTutor().getNome());
        System.out.println("Duração (min): " + atendimento.calcularDuracaoEmMinutos());
        System.out.println("Total (R$): " + atendimento.calcularTotal());

        System.out.println("\nHistórico de ações:");
        for (AcaoAtendimento acao : atendimento.getHistoricoAcoes()) {
            System.out.println(
                acao.getDataHora() + " - " +
                acao.getTipo() + " - " +
                acao.getResponsavel().getNome() + " - " +
                acao.getDescricao()
            );
        }

        scanner.close();
    }
}