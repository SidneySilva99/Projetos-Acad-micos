package model;

import java.util.ArrayList;
import java.util.List;

public class Professor {

    private String nome;
    private String matricula;
    private String email;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String matricula, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
        this.disciplinas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getEmail() {
        return email;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void adicionarDisciplina(Disciplina d) {
        disciplinas.add(d);
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Professor: ").append(nome).append("\n");
        sb.append("Matricula: ").append(matricula).append("\n");
        sb.append("Email: ").append(email).append("\n");

        sb.append("Disciplinas:\n");

        for (Disciplina d : disciplinas) {
            sb.append(" - ").append(d.getNome()).append("\n");
        }

        return sb.toString();
    }
}
