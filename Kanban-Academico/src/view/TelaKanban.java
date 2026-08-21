package view;

import controller.SistemaAcademicoController;
import model.Disciplina;

import javax.swing.*;
import java.awt.*;

public class TelaKanban extends JFrame {

    private SistemaAcademicoController controller;

    private JPanel painelProfessores;
    private JPanel painelAlunos;
    private JPanel painelDisciplinas;

    public TelaKanban() {
        this(new SistemaAcademicoController());
    }

    public TelaKanban(SistemaAcademicoController controller) {
        this.controller = controller;

        setTitle("Sistema Acadêmico Kanban");
        setSize(1200,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,3,10,0));

        painelProfessores = criarColuna("PROFESSORES","professor");
        painelAlunos = criarColuna("ALUNOS","aluno");
        painelDisciplinas = criarColuna("DISCIPLINAS","disciplina");

        add(new JScrollPane(painelProfessores));
        add(new JScrollPane(painelAlunos));
        add(new JScrollPane(painelDisciplinas));

        atualizarQuadro();

        setVisible(true);
    }

    private JPanel criarColuna(String titulo, String tipo){

        JPanel coluna = new JPanel();
        coluna.setLayout(new BoxLayout(coluna,BoxLayout.Y_AXIS));

        coluna.add(criarCabecalho(titulo, tipo));
        coluna.add(Box.createVerticalStrut(10));

        return coluna;
    }

    private void cadastrarProfessor(){

        String nome = JOptionPane.showInputDialog("Nome do professor:");
        if(nome == null || nome.trim().isEmpty()) return;

        String matricula = JOptionPane.showInputDialog("Matrícula:");
        if(matricula == null || matricula.trim().isEmpty()) return;

        String email = JOptionPane.showInputDialog("Email:");
        if(email == null || email.trim().isEmpty()) return;

        String resultado = controller.cadastrarProfessor(
                nome,matricula,email,"");

        JOptionPane.showMessageDialog(this,resultado);

        atualizarQuadro();
    }

    private void cadastrarAluno(){

        if(!controller.temDisciplinas()){
            JOptionPane.showMessageDialog(this,
                    "Cadastre uma disciplina primeiro!");
            return;
        }

        String nome = JOptionPane.showInputDialog("Nome do aluno:");
        if(nome == null || nome.trim().isEmpty()) return;

        String matricula = JOptionPane.showInputDialog("Matrícula:");
        if(matricula == null || matricula.trim().isEmpty()) return;

        // 🔥 LISTA DE DISCIPLINAS
        Object[] opcoes = controller.getDisciplinas().toArray();

        Object escolha = JOptionPane.showInputDialog(
                this,
                "Escolha a disciplina:",
                "Disciplinas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if(escolha == null) return;

        int indice = controller.getDisciplinas().indexOf(escolha);

        String resultado = controller.cadastrarAluno(
                nome,matricula,indice);

        JOptionPane.showMessageDialog(this,resultado);

        atualizarQuadro();
    }

    private void cadastrarDisciplina(){

        String nome = JOptionPane.showInputDialog("Nome da disciplina:");
        if(nome == null || nome.trim().isEmpty()) return;

        String matriculaProfessor =
                JOptionPane.showInputDialog("Matrícula do professor:");
        if(matriculaProfessor == null || matriculaProfessor.trim().isEmpty()) return;

        String resultado = controller.adicionarDisciplina(
                nome,matriculaProfessor);

        JOptionPane.showMessageDialog(this,resultado);

        atualizarQuadro();
    }

    private void atualizarQuadro(){

        painelProfessores.removeAll();
        painelAlunos.removeAll();
        painelDisciplinas.removeAll();

        painelProfessores.add(criarCabecalho("PROFESSORES","professor"));
        painelProfessores.add(Box.createVerticalStrut(10));

        painelAlunos.add(criarCabecalho("ALUNOS","aluno"));
        painelAlunos.add(Box.createVerticalStrut(10));

        painelDisciplinas.add(criarCabecalho("DISCIPLINAS","disciplina"));
        painelDisciplinas.add(Box.createVerticalStrut(10));

        for(var professor : controller.getProfessores()){
            painelProfessores.add(new CardProfessor(professor, controller, this));
            painelProfessores.add(Box.createVerticalStrut(10));
        }

        for(var aluno : controller.getAlunos()){
            painelAlunos.add(new CardAluno(aluno, controller, this));
            painelAlunos.add(Box.createVerticalStrut(10));
        }

        for(var disciplina : controller.getDisciplinas()){
            painelDisciplinas.add(new CardDisciplina(disciplina));
            painelDisciplinas.add(Box.createVerticalStrut(10));
        }

        painelProfessores.add(Box.createVerticalGlue());
        painelAlunos.add(Box.createVerticalGlue());
        painelDisciplinas.add(Box.createVerticalGlue());

        painelProfessores.revalidate();
        painelProfessores.repaint();

        painelAlunos.revalidate();
        painelAlunos.repaint();

        painelDisciplinas.revalidate();
        painelDisciplinas.repaint();
    }

    private JPanel criarCabecalho(String titulo, String tipo){
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel(titulo);
        label.setFont(new Font("Arial", Font.BOLD,14));

        JButton botaoAdd = new JButton("+");
        botaoAdd.setPreferredSize(new Dimension(40,25));

        botaoAdd.addActionListener(e -> {
            if(tipo.equals("professor")) cadastrarProfessor();
            if(tipo.equals("aluno")) cadastrarAluno();
            if(tipo.equals("disciplina")) cadastrarDisciplina();
        });

        cabecalho.add(label, BorderLayout.WEST);
        cabecalho.add(botaoAdd, BorderLayout.EAST);

        return cabecalho;
    }

    public void atualizar(){
        atualizarQuadro();
    }
}
