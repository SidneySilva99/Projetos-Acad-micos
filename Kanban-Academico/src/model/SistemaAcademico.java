package model;

import java.util.ArrayList;

public class SistemaAcademico {

    private ArrayList<Professor> professores;
    private ArrayList<Aluno> alunos;
    private ArrayList<Disciplina> disciplinas;

    public SistemaAcademico(){

        professores = new ArrayList<>();
        alunos = new ArrayList<>();
        disciplinas = new ArrayList<>();

    }

    public void adicionarProfessor(Professor professor){
        professores.add(professor);
    }

    public void adicionarAluno(Aluno aluno){
        alunos.add(aluno);
    }

    public void adicionarDisciplina(Disciplina disciplina){
        disciplinas.add(disciplina);
    }

    public Professor buscarProfessor(String matricula){
        for(Professor p : professores){
            if(p.getMatricula().equals(matricula)){
                return p;
            }
        }
        return null;
    }

    public Aluno buscarAluno(String matricula){
        for(Aluno a : alunos){
            if(a.getMatricula().equals(matricula)){
                return a;
            }
        }
        return null;
    }

    public String adicionarDisciplinaProfessor(String matricula, Disciplina disciplina){

        Professor p = buscarProfessor(matricula);

        if(p == null){
            return "Professor não encontrado!";
        }

        p.getDisciplinas().add(disciplina);
        disciplinas.add(disciplina);

        return "Disciplina adicionada ao professor!";
    }

    public String removerDisciplinaProfessor(String matricula, String nomeDisciplina){

        Professor p = buscarProfessor(matricula);

        if(p == null){
            return "Professor não encontrado!";
        }

        p.getDisciplinas().removeIf(d -> d.getNome().equalsIgnoreCase(nomeDisciplina));

        return "Disciplina removida do professor!";
    }

    public String adicionarDisciplinaAluno(String matricula, Disciplina disciplina){

        Aluno a = buscarAluno(matricula);

        if(a == null){
            return "Aluno não encontrado!";
        }

        a.getDisciplinas().add(disciplina);

        return "Disciplina adicionada ao aluno!";
    }

    public String removerDisciplinaAluno(String matricula, String nomeDisciplina){

        Aluno a = buscarAluno(matricula);

        if(a == null){
            return "Aluno não encontrado!";
        }

        a.getDisciplinas().removeIf(d -> d.getNome().equalsIgnoreCase(nomeDisciplina));

        return "Disciplina removida do aluno!";
    }

    public ArrayList<Professor> getProfessores(){
        return professores;
    }

    public ArrayList<Aluno> getAlunos(){
        return alunos;
    }

    public ArrayList<Disciplina> getDisciplinas(){
        return disciplinas;
    }
}
