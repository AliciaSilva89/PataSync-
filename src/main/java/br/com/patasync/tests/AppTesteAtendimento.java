package br.com.patasync.tests;

import br.com.patasync.db.dao.AnimalDAO;
import br.com.patasync.db.dao.FuncionarioDAO;
import br.com.patasync.db.dao.TutorDAO;
import br.com.patasync.models.*;
import br.com.patasync.services.AtendimentoService;

import java.util.List;
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
        System.out.println("\n=== Animais do Tutor ===");
        List<Animal> animais = animalDAO.buscarTodosPorTutor(tutor.getTutorId(), tutor);
        
        Animal animalSelecionado = null;
        
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado para este tutor.");
            System.out.println("Deseja cadastrar um novo animal? (S/N)");
            String opcao = scanner.nextLine().toUpperCase();
            
            if (opcao.equals("S")) {
                System.out.println("\n=== Cadastro de Animal ===");
                System.out.print("Nome do animal: ");
                String nomeAnimal = scanner.nextLine();
                
                System.out.print("Espécie do animal: ");
                String especieAnimal = scanner.nextLine();
                
                System.out.print("Raça do animal: ");
                String racaAnimal = scanner.nextLine();
                
                System.out.print("Idade do animal (anos): ");
                int idadeAnimal = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Peso do animal (ex: 7 ou 7kg): ");
                String pesoTexto = scanner.nextLine();
                pesoTexto = pesoTexto.replaceAll("[^0-9.,]", "");
                pesoTexto = pesoTexto.replace(",", ".");
                double pesoAnimal = Double.parseDouble(pesoTexto);
                
                Animal novoAnimal = new Animal(
                    nomeAnimal,
                    especieAnimal,
                    racaAnimal,
                    idadeAnimal,
                    pesoAnimal,
                    tutor
                );
                
                int animalId = animalDAO.inserirAnimal(novoAnimal, tutor.getTutorId());
                if (animalId > 0) {
                    System.out.println("Animal cadastrado com sucesso!");
                    tutor.adicionarAnimal(novoAnimal);
                    animalSelecionado = novoAnimal;
                } else {
                    System.out.println("Erro ao cadastrar animal.");
                    scanner.close();
                    return;
                }
            } else {
                System.out.println("Operação cancelada.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Animais encontrados:");
            for (int i = 0; i < animais.size(); i++) {
                Animal a = animais.get(i);
                System.out.println((i + 1) + ". " + a.getNome() + " - " + a.getEspecie() + " (" + a.getRaca() + ") - " + a.getIdade() + " anos");
            }
            
            System.out.println("\nSelecione o animal (número) ou digite 0 para cadastrar novo:");
            int escolha = Integer.parseInt(scanner.nextLine());
            
            if (escolha == 0) {
                System.out.println("\n=== Cadastro de Animal ===");
                System.out.print("Nome do animal: ");
                String nomeAnimal = scanner.nextLine();
                
                System.out.print("Espécie do animal: ");
                String especieAnimal = scanner.nextLine();
                
                System.out.print("Raça do animal: ");
                String racaAnimal = scanner.nextLine();
                
                System.out.print("Idade do animal (anos): ");
                int idadeAnimal = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Peso do animal (ex: 7 ou 7kg): ");
                String pesoTexto = scanner.nextLine();
                pesoTexto = pesoTexto.replaceAll("[^0-9.,]", "");
                pesoTexto = pesoTexto.replace(",", ".");
                double pesoAnimal = Double.parseDouble(pesoTexto);
                
                Animal novoAnimal = new Animal(
                    nomeAnimal,
                    especieAnimal,
                    racaAnimal,
                    idadeAnimal,
                    pesoAnimal,
                    tutor
                );
                
                int animalId = animalDAO.inserirAnimal(novoAnimal, tutor.getTutorId());
                if (animalId > 0) {
                    System.out.println("Animal cadastrado com sucesso!");
                    tutor.adicionarAnimal(novoAnimal);
                    animalSelecionado = novoAnimal;
                } else {
                    System.out.println("Erro ao cadastrar animal.");
                    scanner.close();
                    return;
                }
            } else if (escolha > 0 && escolha <= animais.size()) {
                animalSelecionado = animais.get(escolha - 1);
                System.out.println("Animal selecionado: " + animalSelecionado.getNome());
            } else {
                System.out.println("Opção inválida.");
                scanner.close();
                return;
            }
        }

        AtendimentoService atendimentoService = new AtendimentoService();

        // Abrir atendimento
        Atendimento atendimento = atendimentoService.abrirAtendimento(
            atendente,
            tutor,
            animalSelecionado,
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