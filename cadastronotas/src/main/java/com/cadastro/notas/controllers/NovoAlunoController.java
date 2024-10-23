package com.cadastro.notas.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.cadastro.notas.models.Aluno;
import com.cadastro.notas.models.Notas;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoAlunoController implements Initializable {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker dataNascimentoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField notaOne;
    @FXML
    private TextField notaTwo;
    @FXML
    private TextField notaThree;
    @FXML
    private TextField notaFour;

    private ObservableList<Aluno> alunos;

    public void setAlunos(ObservableList<Aluno> alunos) {
        this.alunos = alunos;
    }

    @FXML
    public void handleSalvar() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String dataNascimento = dataNascimentoField.getValue().toString();
        String email = emailField.getText();
        double nota1 = Double.parseDouble(notaOne.getText());
        double nota2 = Double.parseDouble(notaTwo.getText());
        double nota3 = Double.parseDouble(notaThree.getText());
        double nota4 = Double.parseDouble(notaFour.getText());

        Notas notas = new Notas(nota1, nota2, nota3, nota4);
        Aluno aluno = new Aluno(nome, dataNascimento, cpf, email, notas);

        this.alunos.add(aluno);

        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }
}
