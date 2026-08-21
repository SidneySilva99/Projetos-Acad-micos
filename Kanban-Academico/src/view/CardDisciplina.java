package view;

import model.Disciplina;

import javax.swing.*;
import java.awt.*;

public class CardDisciplina extends JPanel {

    public CardDisciplina(Disciplina disciplina){

        setLayout(new GridLayout(3,1));

        setBackground(new Color(170,220,255));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setPreferredSize(new Dimension(220,90));
        setMaximumSize(new Dimension(220,90));

        add(new JLabel("Disciplina: " + disciplina.getNome()));
        add(new JLabel("Professor: " + disciplina.getProfessor().getNome()));
        add(new JLabel("Alunos: " + disciplina.getQuantidadeAlunos()));

    }

}
