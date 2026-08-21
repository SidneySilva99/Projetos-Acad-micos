package view;

import controller.SistemaAcademicoController;
import model.Professor;

import javax.swing.*;
import java.awt.*;

public class CardProfessor extends JPanel {

    public CardProfessor(Professor professor,
                         SistemaAcademicoController controller,
                         TelaKanban tela){

        setLayout(new GridLayout(4,1));

        setBackground(new Color(255,230,120));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setPreferredSize(new Dimension(220,100));
        setMaximumSize(new Dimension(220,100));

        add(new JLabel("Nome: "+professor.getNome()));
        add(new JLabel("Matricula: "+professor.getMatricula()));
        add(new JLabel("Email: "+professor.getEmail()));
        add(new JLabel("Disciplinas: "+professor.getDisciplinas().size()));

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

                String resultado = controller.adicionarDisciplinaProfessor(
                        professor.getMatricula(), nomeDisciplina);

                JOptionPane.showMessageDialog(this, resultado);

                tela.atualizar();
            }

        });

        remover.addActionListener(e -> {

            String nomeDisciplina = JOptionPane.showInputDialog(
                    this, "Nome da disciplina para remover:");

            if(nomeDisciplina != null && !nomeDisciplina.isEmpty()){

                String resultado = controller.removerDisciplinaProfessor(
                        professor.getMatricula(), nomeDisciplina);

                JOptionPane.showMessageDialog(this, resultado);

                tela.atualizar();
            }

        });
    }
}
