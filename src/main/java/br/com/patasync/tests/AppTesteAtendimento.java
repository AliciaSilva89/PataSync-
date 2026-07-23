package br.com.patasync.tests;

import br.com.patasync.db.dao.AnimalDAO;
import br.com.patasync.db.dao.FuncionarioDAO;
import br.com.patasync.db.dao.TutorDAO;
import br.com.patasync.models.*;
import br.com.patasync.services.AtendimentoService;

import java.util.Scanner;

public class AppTesteAtendimento {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // === Login do Atendente ===
        System.out.println("=== Sistema PataSync - Atendimento Veterinário ===");
        System.out.println("=== Login do Atendente ===");
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        TutorDAO tutorDAO = new TutorDAO();
        AnimalDAO animalDAO = new AnimalDAO();

        // Buscar atendente disponível
        Atendente atendente = funcionarioDAO.buscarPrimeiroAtendente();
        if (atendente == null) {
            System.out.println("Nenhum atendente cadastrado no sistema.");
            scanner.close();
            return;
        }
        System.out.println("Atendente logado: " + atendente.getNome());

        // Buscar veterinário disponível
        Veterinario veterinario = funcionarioDAO.buscarPrimeiroVeterinario();
        if (veterinario == null) {
            System.out.println("Nenhum veterinário cadastrado no sistema.");
            scanner.close();
            return;
        }
        System.out.println("Veterinário disponível: " + veterinario.getNome());

        // Buscar assistente disponível
        AssistenteVeterinario assistente = funcionarioDAO.buscarPrimeiroAssistente();
        if (assistente == null) {
            System.out.println("Nenhum assistente cadastrado no sistema.");
            scanner.close();
            return;
        }
        System.out.println("Assistente disponível: " + assistente.getNome());

        // === Buscar Tutor ===
        System.out.println("\n=== Buscar Tutor ===");
        System.out.print("Digite o CPF do tutor: ");
        String cpfTutor = scanner.nextLine().replaceAll("[^0-9]", "");
        
        Tutor tutor = tutorDAO.buscarPorCPF(cpfTutor);
        if (tutor == null) {
            System.out.println("Tutor não encontrado com o CPF: " + cpfTutor);
            scanner.close();
            return;
        }
        System.out.println("Tutor encontrado: " + tutor.getNome());

        // === Buscar Animal ===
        System.out.println("\n=== Buscar Animal ===");
        System.out.print("Digite o nome do animal: ");
        String nomeAnimal = scanner.nextLine();
        
        Animal animal = animalDAO.buscarPorNomeETutor(nomeAnimal, tutor);
        if (animal == null) {
            System.out.println("Animal não encontrado para este tutor.");
            scanner.close();
            return;
        }
        System.out.println("Animal encontrado: " + animal.getNome() + " (" + animal.getEspecie() + ")");
        tutor.adicionarAnimal(animal);

        AtendimentoService atendimentoService = new AtendimentoService();

        // Abrir atendimento
        Atendimento atendimento = atendimentoService.abrirAtendimento(
            atendente,
            tutor,
            animal,
            veterinario
        );

        // Primeiro atendimento (triagem)
        atendimentoService.registrarPrimeiroAtendimento(
            assistente,
            atendimento
        );

        // Registro de Medicação
        System.out.println("\n=== Registro de Medicação ===");
        System.out.print("Nome da medicação: ");
        String nomeMedicacao = scanner.nextLine();

        System.out.print("Dosagem (ex: 20 mg): ");
        String dosagem = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        System.out.print("Valor unitário (R$): ");
        String valorUnitarioTexto = scanner.nextLine();
        valorUnitarioTexto = valorUnitarioTexto.replaceAll("[^0-9.,]", "");
        valorUnitarioTexto = valorUnitarioTexto.replace(",", ".");
        double valorUnitario = Double.parseDouble(valorUnitarioTexto);

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

        // Diagnóstico
        System.out.println("\n=== Registro de Diagnóstico ===");
        System.out.print("Descrição do diagnóstico: ");
        String descricaoDiagnostico = scanner.nextLine();

        atendimentoService.registrarDiagnostico(
            veterinario,
            atendimento,
            descricaoDiagnostico
        );

        // Procedimento
        System.out.println("\n=== Registro de Procedimento ===");
        System.out.print("Descrição do procedimento: ");
        String descricaoProcedimento = scanner.nextLine();

        Procedimento procedimento = new Procedimento(
            descricaoProcedimento,
            TipoProcedimento.CONSULTA,
            120.0
        );

        atendimentoService.registrarProcedimento(
            veterinario,
            atendimento,
            procedimento
        );

        // Encerrar atendimento
        atendimentoService.encerrarAtendimento(
            atendente,
            atendimento
        );

        // Resumo
        System.out.println("\n=== Resumo do Atendimento ===");
        System.out.println("Animal: " + atendimento.getAnimal().getNome());
        System.out.println("Tutor: " + atendimento.getTutor().getNome());
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