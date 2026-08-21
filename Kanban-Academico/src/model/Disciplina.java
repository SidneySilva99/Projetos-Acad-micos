package model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private Professor professor;

    public Disciplina(String nome, Professor professor) {
        this.nome = nome;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Professor getProfessor() {
        return professor;
    }

    private ArrayList<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void removerAluno(Aluno aluno){
        alunos.remove(aluno);
    }

    public int getQuantidadeAlunos(){
        return alunos.size();
    }

    public String toString() {
        return nome + " - Prof: " + professor.getNome() + "";
    }
}
