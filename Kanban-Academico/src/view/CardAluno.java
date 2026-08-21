package view;

import controller.SistemaAcademicoController;
import model.Aluno;

import javax.swing.*;
import java.awt.*;

public class CardAluno extends JPanel {

    public CardAluno(Aluno aluno,
                     SistemaAcademicoController controller,
                     TelaKanban tela){

        setLayout(new GridLayout(3,1));

        setBackground(new Color(180,255,180));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setPreferredSize(new Dimension(220,90));
        setMaximumSize(new Dimension(220,90));

        add(new JLabel("Nome: " + aluno.getNome()));
        add(new JLabel("Matricula: " + aluno.getMatricula()));
        add(new JLabel("Disciplinas: " + aluno.getDisciplinas().size()));

        JPopupMenu menu = new JPopupMenu();

        JMenuItem adicionar = new JMenuItem("Adicionar disciplina");
        JMenuItem remover = new JMenuItem("Remover disciplina");

        menu.add(adicionar);
        menu.add(remover);

        setComponentPopupMenu(menu);

        adicionar.addActionListener(e -> {

            String nomeDisciplina = JOptionPane.showInputDialog(
                    this, "Nome da disciplina:");

            if(nomeDisciplina != null && !nomeDisciplina.isEmpty()){

                String resultado = controller.adicionarDisciplinaAluno(
                        aluno.getMatricula(), nomeDisciplina);

                JOptionPane.showMessageDialog(this, resultado);

                tela.atualizar();
            }

        });

        remover.addActionListener(e -> {

            String nomeDisciplina = JOptionPane.showInputDialog(
                    this, "Nome da disciplina para remover:");

            if(nomeDisciplina != null && !nomeDisciplina.isEmpty()){

                String resultado = controller.removerDisciplinaAluno(
                        aluno.getMatricula(), nomeDisciplina);

                JOptionPane.showMessageDialog(this, resultado);

                tela.atualizar();
            }

        });
    }
}
