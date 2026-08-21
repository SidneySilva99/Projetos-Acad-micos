package controller;

import model.*;

import java.util.ArrayList;

public class SistemaAcademicoController {

    private SistemaAcademico sistema;

    public SistemaAcademicoController(){
        sistema = new SistemaAcademico();
    }

    public String cadastrarProfessor(String nome, String matricula, String email, String disciplina){

        Professor professor = new Professor(nome,matricula,email);
        sistema.adicionarProfessor(professor);

        return "Professor cadastrado com sucesso";
    }

    public String cadastrarAluno(String nome, String matricula, int indiceDisciplina){

        if(indiceDisciplina < 0 || indiceDisciplina >= sistema.getDisciplinas().size()){
            return "Disciplina inválida";
        }

        Disciplina disciplina = sistema.getDisciplinas().get(indiceDisciplina);

        Aluno aluno = new Aluno(nome,matricula);

        aluno.adicionarDisciplina(disciplina);
        disciplina.adicionarAluno(aluno); // 🔥 IMPORTANTE

        sistema.adicionarAluno(aluno);

        return "Aluno cadastrado com sucesso";
    }

    public String adicionarDisciplina(String nomeDisciplina, String matriculaProfessor){

        Professor professor = sistema.buscarProfessor(matriculaProfessor);

        if(professor == null){
            return "Professor não encontrado";
        }

        Disciplina disciplina = new Disciplina(nomeDisciplina,professor);

        sistema.adicionarDisciplina(disciplina);
        professor.adicionarDisciplina(disciplina);

        return "Disciplina cadastrada com sucesso";
    }

    public String adicionarDisciplinaProfessor(String matricula, String nomeDisciplina){

        Professor professor = sistema.buscarProfessor(matricula);

        if(professor == null){
            return "Professor não encontrado";
        }

        Disciplina disciplina = new Disciplina(nomeDisciplina, professor);

        professor.adicionarDisciplina(disciplina);
        sistema.adicionarDisciplina(disciplina);

        return "Disciplina adicionada ao professor";
    }

    public String removerDisciplinaProfessor(String matricula, String nomeDisciplina){

        Professor professor = sistema.buscarProfessor(matricula);

        if(professor == null){
            return "Professor não encontrado";
        }

        Disciplina remover = null;

        for(Disciplina d : professor.getDisciplinas()){
            if(d.getNome().equalsIgnoreCase(nomeDisciplina)){
                remover = d;
                break;
            }
        }

        if(remover != null){
            professor.getDisciplinas().remove(remover);
            sistema.getDisciplinas().remove(remover);
            return "Disciplina removida do professor";
        }

        return "Disciplina não encontrada";
    }

    public String adicionarDisciplinaAluno(String matricula, String nomeDisciplina){

        Aluno aluno = sistema.buscarAluno(matricula);

        if(aluno == null){
            return "Aluno não encontrado";
        }

        for(Disciplina d : sistema.getDisciplinas()){
            if(d.getNome().equalsIgnoreCase(nomeDisciplina)){

                aluno.adicionarDisciplina(d);
                d.adicionarAluno(aluno); // 🔥 IMPORTANTE

                return "Disciplina adicionada ao aluno";
            }
        }

        return "Disciplina não encontrada";
    }

    public String removerDisciplinaAluno(String matricula, String nomeDisciplina){

        Aluno aluno = sistema.buscarAluno(matricula);

        if(aluno == null){
            return "Aluno não encontrado";
        }

        Disciplina remover = null;

        for(Disciplina d : aluno.getDisciplinas()){
            if(d.getNome().equalsIgnoreCase(nomeDisciplina)){
                remover = d;
                break;
            }
        }

        if(remover != null){
            aluno.getDisciplinas().remove(remover);
            remover.removerAluno(aluno); // 🔥 IMPORTANTE

            return "Disciplina removida do aluno";
        }

        return "Disciplina não encontrada";
    }

    public ArrayList<Professor> getProfessores(){
        return sistema.getProfessores();
    }

    public ArrayList<Aluno> getAlunos(){
        return sistema.getAlunos();
    }

    public ArrayList<Disciplina> getDisciplinas(){
        return sistema.getDisciplinas();
    }

    public boolean temDisciplinas(){
        return !sistema.getDisciplinas().isEmpty();
    }
}
