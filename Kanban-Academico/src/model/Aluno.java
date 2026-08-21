package model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private String nome;
    private String matricula;
    private List<Disciplina> disciplinas;

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Aluno: ").append(nome).append("\n");
        sb.append("Matricula: ").append(matricula).append("\n");

        sb.append("Disciplinas:\n");

        for (Disciplina d : disciplinas) {
            sb.append(" - ").append(d.getNome()).append("\n");
        }

        return sb.toString();
    }
}
