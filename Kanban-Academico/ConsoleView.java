import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner = new Scanner(System.in);
    private SistemaAcademico sistema = new SistemaAcademico();

    public void iniciar() {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA ACADEMICO =====");
            System.out.println("1 - Cadastrar Professor");
            System.out.println("2 - Cadastrar Aluno");
            System.out.println("3 - Listar Professores");
            System.out.println("4 - Listar Alunos");
            System.out.println("5 - Listar Disciplinas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarProfessor();
                case 2 -> cadastrarAluno();
                case 3 -> sistema.listarProfessores();
                case 4 -> sistema.listarAlunos();
                case 5 -> sistema.listarDisciplinas();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);
    }

    public void cadastrarProfessor() {
        System.out.println("\n=== Cadastro de Professor ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Disciplina: ");
        String nomeDisciplina = scanner.nextLine();

        sistema.cadastrarProfessor(nome, matricula, email, nomeDisciplina);
    }

    public void cadastrarAluno() {
        System.out.println("\n=== Cadastro de Aluno ===");

        if (!sistema.temDisciplinas()) {
            System.out.println("Nao existe disciplina cadastrada.");
            System.out.println("Cadastre um professor primeiro.");
            return;
        }

        sistema.listarDisciplinas();

        System.out.print("Escolha o numero da disciplina: ");
        int indice = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();

        sistema.cadastrarAluno(nome, matricula, indice);
    }
}
