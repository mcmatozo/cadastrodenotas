package com.cadastro.notas.models;

public class Aluno {

    private String nome;
    private String dataNascimento;
    private String cpf;
    private String email;
    private Notas notas;

    public Aluno(String nome, String dataNascimento, String cpf, String email, Notas notas) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.notas = notas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Notas getNotas() {
        return notas;
    }

    public void setNotas(Notas notas) {
        this.notas = notas;
    }

    public Double getNota1() {
        return this.notas.getNota1();
    }

    public Double getNota2() {
        return this.notas.getNota2();
    }

    public Double getNota3() {
        return this.notas.getNota3();
    }

    public Double getNota4() {
        return this.notas.getNota4();
    }

    public Double getMediaNotas() {
        double valor = notas.getNota1() + notas.getNota2() + notas.getNota3() + notas.getNota4();
        return valor / 4;
    }

}
