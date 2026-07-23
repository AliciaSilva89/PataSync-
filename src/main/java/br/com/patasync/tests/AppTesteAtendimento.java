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

        // === Carregar dados do banco de dados ===
        TutorDAO tutorDAO = new TutorDAO();
        AnimalDAO animalDAO = new AnimalDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

        // Buscar primeiro tutor do banco
        Tutor tutor = tutorDAO.buscarPrimeiro();
        if (tutor == null) {
            System.out.println("Nenhum tutor encontrado no banco de dados.");
            scanner.close();
            return;
        }
        System.out.println("Tutor carregado: " + tutor.getNome());

        // Buscar primeiro animal do tutor
        Animal animal = animalDAO.buscarPrimeiroPorTutor(1, tutor);
        if (animal == null) {
            System.out.println("Nenhum animal encontrado para o tutor no banco de dados.");
            scanner.close();
            return;
        }
        System.out.println("Animal carregado: " + animal.getNome());
        tutor.adicionarAnimal(animal);

        // Buscar funcionários do banco
        Atendente atendente = funcionarioDAO.buscarPrimeiroAtendente();
        if (atendente == null) {
            System.out.println("Nenhum atendente encontrado no banco de dados.");
            scanner.close();
            return;
        }
        System.out.println("Atendente carregado: " + atendente.getNome());

        AssistenteVeterinario assistente = funcionarioDAO.buscarPrimeiroAssistente();
        if (assistente == null) {
            System.out.println("Nenhum assistente encontrado no banco de dados.");
            scanner.close();
            return;
        }
        System.out.println("Assistente carregado: " + assistente.getNome());

        Veterinario veterinario = funcionarioDAO.buscarPrimeiroVeterinario();
        if (veterinario == null) {
            System.out.println("Nenhum veterinário encontrado no banco de dados.");
            scanner.close();
            return;
        }
        System.out.println("Veterinário carregado: " + veterinario.getNome());

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