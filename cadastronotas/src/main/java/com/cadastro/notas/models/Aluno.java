package com.cadastro.notas.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aluno {

    private final StringProperty nome;
    private final StringProperty dataNascimento;
    private final StringProperty cpf;
    private final StringProperty email;
    private Notas notas;

    public Aluno(String nome, String dataNascimento, String cpf, String email, Notas notas) {
        this.nome = new SimpleStringProperty(nome);
        this.dataNascimento = new SimpleStringProperty(dataNascimento);
        this.cpf = new SimpleStringProperty(cpf);
        this.email = new SimpleStringProperty(email);
        this.notas = notas;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento.get();
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento.set(dataNascimento);
    }

    public StringProperty dataNascimentoProperty() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf.get();
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
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
