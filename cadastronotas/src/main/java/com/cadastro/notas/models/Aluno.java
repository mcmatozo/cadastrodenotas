package com.cadastro.notas.models;

public class Aluno {

    private String nome;
    private String dataNascimento;
    private String cpf;
    private String email;
    private Notas notas;

    public Aluno(String nome, String dataNascimento, String cpf, String email) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;

        this.notas = new Notas(0.0, 0.0, 0.0, 0.0);

    }
}
