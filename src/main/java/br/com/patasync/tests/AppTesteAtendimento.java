package br.com.patasync.tests;

import br.com.patasync.db.dao.AnimalDAO;
import br.com.patasync.db.dao.FuncionarioDAO;
import br.com.patasync.db.dao.MedicacaoDAO;
import br.com.patasync.db.dao.PessoaDAO;
import br.com.patasync.db.dao.TutorDAO;
import br.com.patasync.models.*;
import br.com.patasync.services.AtendimentoService;

import java.util.ArrayList;
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
        MedicacaoDAO medicacaoDAO = new MedicacaoDAO();
        PessoaDAO pessoaDAO = new PessoaDAO();

        System.out.print("Digite o CPF do atendente (ou deixe vazio para cadastrar novo): ");
        String cpfAtendente = scanner.nextLine().replaceAll("[^0-9]", "");
        
        Atendente atendente;
        
        if (cpfAtendente.isEmpty()) {
            // Cadastro de novo atendente
            atendente = cadastrarNovoAtendente(scanner, pessoaDAO, funcionarioDAO);
            if (atendente == null) {
                System.out.println("Falha no cadastro do atendente.");
                scanner.close();
                return;
            }
        } else {
            // Buscar atendente por CPF
            atendente = funcionarioDAO.buscarAtendentePorCPF(cpfAtendente);
            if (atendente == null) {
                System.out.println("Atendente não encontrado com o CPF: " + cpfAtendente);
                System.out.println("Deseja cadastrar um novo atendente? (S/N)");
                String opcao = scanner.nextLine().toUpperCase();
                
                if (opcao.equals("S")) {
                    atendente = cadastrarNovoAtendente(scanner, pessoaDAO, funcionarioDAO);
                    if (atendente == null) {
                        System.out.println("Falha no cadastro do atendente.");
                        scanner.close();
                        return;
                    }
                } else {
                    System.out.println("Operação cancelada.");
                    scanner.close();
                    return;
                }
            }
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
        System.out.println("\n=== Registro de Medicações ===");
        List<Medicacao> medicamentosDisponiveis = medicacaoDAO.buscarTodas();
        List<Medicacao> medicamentosSelecionados = new ArrayList<>();
        
        if (medicamentosDisponiveis.isEmpty()) {
            System.out.println("Nenhuma medicação cadastrada no sistema.");
            System.out.println("Deseja cadastrar uma nova medicação? (S/N)");
            String opcao = scanner.nextLine().toUpperCase();
            
            if (opcao.equals("S")) {
                cadastrarNovaMedicacao(scanner, medicacaoDAO, medicamentosSelecionados);
            }
        } else {
            System.out.println("Medicações disponíveis:");
            for (int i = 0; i < medicamentosDisponiveis.size(); i++) {
                Medicacao m = medicamentosDisponiveis.get(i);
                System.out.println((i + 1) + ". " + m.getNome() + " - " + m.getClasseIndicacao() + " - R$ " + m.getValorUnitario());
                System.out.println("   Princípio ativo: " + m.getPrincipioAtivo());
                System.out.println("   Dosagem média: " + m.getDosagem());
                if (m.getObservacoes() != null && !m.getObservacoes().isEmpty()) {
                    System.out.println("   Observações: " + m.getObservacoes());
                }
                System.out.println();
            }
            
            System.out.println("Selecione as medicações (separadas por vírgula) ou digite 0 para cadastrar nova:");
            System.out.println("Exemplo: 1,3,5 para selecionar medicações 1, 3 e 5");
            String entrada = scanner.nextLine();
            
            if (entrada.equals("0")) {
                cadastrarNovaMedicacao(scanner, medicacaoDAO, medicamentosSelecionados);
            } else {
                String[] selecoes = entrada.split(",");
                for (String s : selecoes) {
                    try {
                        int indice = Integer.parseInt(s.trim()) - 1;
                        if (indice >= 0 && indice < medicamentosDisponiveis.size()) {
                            Medicacao medicacaoBase = medicamentosDisponiveis.get(indice);
                            
                            System.out.print("Quantidade para " + medicacaoBase.getNome() + ": ");
                            int quantidade = Integer.parseInt(scanner.nextLine());
                            
                            Medicacao medicacaoSelecionada = new Medicacao(
                                medicacaoBase.getNome(),
                                medicacaoBase.getDosagem(),
                                quantidade,
                                medicacaoBase.getValorUnitario()
                            );
                            medicacaoSelecionada.setPrincipioAtivo(medicacaoBase.getPrincipioAtivo());
                            medicacaoSelecionada.setClasseIndicacao(medicacaoBase.getClasseIndicacao());
                            medicacaoSelecionada.setCategoriaClinica(medicacaoBase.getCategoriaClinica());
                            medicacaoSelecionada.setObservacoes(medicacaoBase.getObservacoes());
                            
                            medicamentosSelecionados.add(medicacaoSelecionada);
                            System.out.println("Medicação adicionada: " + medicacaoBase.getNome() + " (Qtd: " + quantidade + ")");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida: " + s);
                    }
                }
            }
        }
        
        // Aplicar medicações selecionadas
        for (Medicacao medicacao : medicamentosSelecionados) {
            atendimentoService.aplicarMedicacao(
                assistente,
                atendimento,
                medicacao
            );
        }
        
        if (!medicamentosSelecionados.isEmpty()) {
            System.out.println("\nTotal de " + medicamentosSelecionados.size() + " medicação(ões) registrada(s).");
        }

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

    private static void cadastrarNovaMedicacao(Scanner scanner, MedicacaoDAO medicacaoDAO, List<Medicacao> medicamentosSelecionados) {
        System.out.println("\n=== Cadastro de Nova Medicação ===");
        System.out.print("Nome da medicação: ");
        String nome = scanner.nextLine();
        
        System.out.print("Princípio ativo: ");
        String principioAtivo = scanner.nextLine();
        
        System.out.print("Classe de indicação: ");
        String classeIndicacao = scanner.nextLine();
        
        System.out.print("Dosagem média: ");
        String dosagem = scanner.nextLine();
        
        System.out.print("Categoria clínica: ");
        String categoriaClinica = scanner.nextLine();
        
        System.out.print("Valor unitário (R$): ");
        String valorUnitarioTexto = scanner.nextLine();
        valorUnitarioTexto = valorUnitarioTexto.replaceAll("[^0-9.,]", "");
        valorUnitarioTexto = valorUnitarioTexto.replace(",", ".");
        double valorUnitario = Double.parseDouble(valorUnitarioTexto);
        
        System.out.print("Observações (opcional, pode deixar vazio): ");
        String observacoes = scanner.nextLine();
        
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(scanner.nextLine());
        
        Medicacao novaMedicacao = new Medicacao(nome, dosagem, quantidade, valorUnitario);
        novaMedicacao.setPrincipioAtivo(principioAtivo);
        novaMedicacao.setClasseIndicacao(classeIndicacao);
        novaMedicacao.setCategoriaClinica(categoriaClinica);
        novaMedicacao.setObservacoes(observacoes);
        
        int medicamentoId = medicacaoDAO.inserirMedicacao(novaMedicacao);
        if (medicamentoId > 0) {
            System.out.println("Medicação cadastrada com sucesso!");
            medicamentosSelecionados.add(novaMedicacao);
            
            System.out.println("Deseja adicionar mais medicações? (S/N)");
            String opcao = scanner.nextLine().toUpperCase();
            if (opcao.equals("S")) {
                // Recarregar lista de medicamentos disponíveis
                List<Medicacao> medicamentosDisponiveis = medicacaoDAO.buscarTodas();
                System.out.println("\nMedicações disponíveis:");
                for (int i = 0; i < medicamentosDisponiveis.size(); i++) {
                    Medicacao m = medicamentosDisponiveis.get(i);
                    System.out.println((i + 1) + ". " + m.getNome() + " - " + m.getClasseIndicacao() + " - R$ " + m.getValorUnitario());
                }
                
                System.out.println("Selecione as medicações (separadas por vírgula):");
                String entrada = scanner.nextLine();
                
                String[] selecoes = entrada.split(",");
                for (String s : selecoes) {
                    try {
                        int indice = Integer.parseInt(s.trim()) - 1;
                        if (indice >= 0 && indice < medicamentosDisponiveis.size()) {
                            Medicacao medicacaoBase = medicamentosDisponiveis.get(indice);
                            
                            System.out.print("Quantidade para " + medicacaoBase.getNome() + ": ");
                            int qtd = Integer.parseInt(scanner.nextLine());
                            
                            Medicacao medicacaoSelecionada = new Medicacao(
                                medicacaoBase.getNome(),
                                medicacaoBase.getDosagem(),
                                qtd,
                                medicacaoBase.getValorUnitario()
                            );
                            medicacaoSelecionada.setPrincipioAtivo(medicacaoBase.getPrincipioAtivo());
                            medicacaoSelecionada.setClasseIndicacao(medicacaoBase.getClasseIndicacao());
                            medicacaoSelecionada.setCategoriaClinica(medicacaoBase.getCategoriaClinica());
                            medicacaoSelecionada.setObservacoes(medicacaoBase.getObservacoes());
                            
                            medicamentosSelecionados.add(medicacaoSelecionada);
                            System.out.println("Medicação adicionada: " + medicacaoBase.getNome() + " (Qtd: " + qtd + ")");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida: " + s);
                    }
                }
            }
        } else {
            System.out.println("Erro ao cadastrar medicação.");
        }
    }

    private static Atendente cadastrarNovoAtendente(Scanner scanner, PessoaDAO pessoaDAO, FuncionarioDAO funcionarioDAO) {
        System.out.println("\n=== Cadastro de Novo Atendente ===");
        
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF (apenas números): ");
        String cpf = scanner.nextLine().replaceAll("[^0-9]", "");
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Logradouro (rua/avenida): ");
        String logradouro = scanner.nextLine();
        
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        
        System.out.print("Complemento (opcional, pode deixar vazio): ");
        String complemento = scanner.nextLine();
        
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        
        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine();
        
        System.out.print("Profissão: ");
        String profissao = scanner.nextLine();
        
        System.out.print("Data de nascimento (YYYY-MM-DD): ");
        String dataNascimento = scanner.nextLine();
        
        System.out.print("Sexo (M/F): ");
        String sexo = scanner.nextLine().toUpperCase();
        
        System.out.print("Estado civil: ");
        String estadoCivil = scanner.nextLine();
        
        System.out.print("Salário: ");
        String salarioTexto = scanner.nextLine();
        salarioTexto = salarioTexto.replaceAll("[^0-9.,]", "");
        salarioTexto = salarioTexto.replace(",", ".");
        double salario = Double.parseDouble(salarioTexto);
        
        // Inserir pessoa
        int pessoaId = pessoaDAO.inserirPessoa(nome, cpf, telefone, email, logradouro, numero, 
                                               complemento, cep, cidade, estado, profissao, 
                                               dataNascimento, sexo, estadoCivil);
        
        if (pessoaId == -1) {
            System.out.println("Erro ao cadastrar pessoa no banco de dados.");
            return null;
        }
        
        // Inserir funcionário como atendente
        int funcionarioId = funcionarioDAO.inserirFuncionario(pessoaId, salario, "ATENDENTE");
        
        if (funcionarioId == -1) {
            System.out.println("Erro ao cadastrar funcionário no banco de dados.");
            return null;
        }
        
        // Criar objeto Atendente
        Atendente novoAtendente = new Atendente(nome, cpf, telefone, email, logradouro, numero,
                                               complemento, cep, cidade, estado, profissao,
                                               java.time.LocalDate.parse(dataNascimento), sexo, estadoCivil, salario);
        
        System.out.println("Atendente cadastrado com sucesso!");
        return novoAtendente;
    }
}