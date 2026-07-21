package br.com.patasync.tests;

import br.com.patasync.models.*;
import br.com.patasync.services.AtendimentoService;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class AppTesteAtendimento {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // === Cadastro de Tutor ===
        System.out.println("=== Cadastro de Tutor ===");
        System.out.print("Nome do tutor: ");
        String nomeTutor = scanner.nextLine();

        System.out.print("CPF do tutor (formato 000.000.000-00): ");
        String cpfTutorEntrada = scanner.nextLine();
        String cpfTutor = cpfTutorEntrada.replaceAll("[^0-9]", "");
        if (cpfTutor.length() != 11) {
            System.out.println("CPF inválido. Informe 11 dígitos.");
            scanner.close();
            return;
        }

        System.out.print("Telefone do tutor: ");
        String telefoneTutor = scanner.nextLine();

        System.out.print("Email do tutor: ");
        String emailTutor = scanner.nextLine();

        System.out.print("Logradouro (rua/avenida): ");
        String logradouroTutor = scanner.nextLine();

        System.out.print("Número: ");
        String numeroEnderecoTutor = scanner.nextLine();

        System.out.print("Complemento (opcional, pode deixar vazio): ");
        String complementoTutor = scanner.nextLine();

        System.out.print("CEP: ");
        String cepTutor = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidadeTutor = scanner.nextLine();

        System.out.print("Estado (UF): ");
        String estadoTutor = scanner.nextLine();

        System.out.print("Profissão do tutor: ");
        String profissaoTutor = scanner.nextLine();

        System.out.print("Data de nascimento do tutor (YYYY-MM-DD): ");
        String dataNascTexto = scanner.nextLine();
        LocalDate dataNascimentoTutor = LocalDate.parse(dataNascTexto);

        System.out.print("Sexo do tutor: ");
        String sexoTutor = scanner.nextLine();

        System.out.print("Estado civil do tutor: ");
        String estadoCivilTutor = scanner.nextLine();

        // Validação de maioridade
        LocalDate hoje = LocalDate.now();
        int idadeTutor = Period.between(dataNascimentoTutor, hoje).getYears();
        if (idadeTutor < 18) {
            System.out.println("Tutor menor de idade (" + idadeTutor + " anos). Não é permitido cadastrar como responsável.");
            scanner.close();
            return;
        }

        Tutor tutor = new Tutor(
            nomeTutor,
            cpfTutor,
            telefoneTutor,
            emailTutor,
            logradouroTutor,
            numeroEnderecoTutor,
            complementoTutor,
            cepTutor,
            cidadeTutor,
            estadoTutor,
            profissaoTutor,
            dataNascimentoTutor,
            sexoTutor,
            estadoCivilTutor
        );

        // === Cadastro de Animal ===
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

        Animal animal = new Animal(
            nomeAnimal,
            especieAnimal,
            racaAnimal,
            idadeAnimal,
            pesoAnimal,
            tutor
        );
        tutor.adicionarAnimal(animal);

        // Funcionários (por enquanto fixos)
        Atendente atendente = new Atendente(
            "Maria Atendente",
            "11122233344",
            "34 98888-0000",
            "atendente@clinicavet.com",
            "Av. Central",
            "10",
            "",
            "38400-000",
            "Uberlândia",
            "MG",
            "Atendente",
            LocalDate.of(1990, 1, 1),
            "Feminino",
            "Solteira",
            2000.0
        );

        AssistenteVeterinario assistente = new AssistenteVeterinario(
            "Carlos Assistente",
            "55566677788",
            "34 97777-0000",
            "assistente@clinicavet.com",
            "Rua B",
            "45",
            "",
            "38400-000",
            "Uberlândia",
            "MG",
            "Assistente Veterinário",
            LocalDate.of(1992, 5, 10),
            "Masculino",
            "Solteiro",
            2500.0
        );

        Veterinario veterinario = new Veterinario(
            "Ana Veterinária",
            "99988877766",
            "34 96666-0000",
            "veterinaria@clinicavet.com",
            "Rua C",
            "80",
            "",
            "38400-000",
            "Uberlândia",
            "MG",
            "Médica Veterinária",
            LocalDate.of(1985, 3, 20),
            "Feminino",
            "Casada",
            5000.0
        );

        AtendimentoService atendimentoService = new AtendimentoService();

        // Abrir atendimento
        Atendimento atendimento = atendimentoService.abrirAtendimento(
            atendente,
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