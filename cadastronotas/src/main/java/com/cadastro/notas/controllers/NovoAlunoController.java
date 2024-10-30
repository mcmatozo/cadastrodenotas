package com.cadastro.notas.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import com.cadastro.notas.models.Aluno;
import com.cadastro.notas.models.Notas;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovoAlunoController implements Initializable {

    @FXML
    private Button criarAlunoButton;
    private BooleanProperty criarAlunoButtonVisivel = new SimpleBooleanProperty(true);

    @FXML
    private Button editarAlunoButton;
    private BooleanProperty editarAlunoButtonVisivel = new SimpleBooleanProperty(false);

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

    private Aluno aluno;

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

        try {
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            FileWriter fileFileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileFileWriter);

            String alunoData = aluno.getNome()
                    + "," + aluno.getDataNascimento()
                    + "," + aluno.getCpf()
                    + "," + aluno.getEmail()
                    + "," + aluno.getNota1()
                    + "," + aluno.getNota2()
                    + "," + aluno.getNota3()
                    + "," + aluno.getNota4();

            bufferedWriter.write(alunoData);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void handleEditar() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String dataNascimento = dataNascimentoField.getValue().toString();
        String email = emailField.getText();
        double nota1 = Double.parseDouble(notaOne.getText());
        double nota2 = Double.parseDouble(notaTwo.getText());
        double nota3 = Double.parseDouble(notaThree.getText());
        double nota4 = Double.parseDouble(notaFour.getText());

        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setDataNascimento(dataNascimento);
        aluno.setEmail(email);

        atualizarArquivoAlunos();

        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        criarAlunoButton.visibleProperty().bind(criarAlunoButtonVisivel);
        editarAlunoButton.visibleProperty().bind(editarAlunoButtonVisivel);
    }

    public void setAluno(Aluno aluno) {
        cpfField.setText(aluno.getCpf());
        nomeField.setText(aluno.getNome());
        dataNascimentoField
                .setValue(LocalDate.parse(aluno.getDataNascimento(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        notaOne.setText(aluno.getNota1().toString());
        notaTwo.setText(aluno.getNota2().toString());
        notaThree.setText(aluno.getNota3().toString());
        notaFour.setText(aluno.getNota4().toString());
        emailField.setText(aluno.getEmail());

        this.aluno = aluno;
    }

    public void habilitarEdicao() {
        criarAlunoButtonVisivel.set(false);
        editarAlunoButtonVisivel.set(true);
    }

    private void atualizarArquivoAlunos() {
        try {
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            for (Aluno aluno : alunos) {
                String alunoData = aluno.getNome()
                        + "," + aluno.getDataNascimento()
                        + "," + aluno.getCpf()
                        + "," + aluno.getEmail()
                        + "," + aluno.getNota1()
                        + "," + aluno.getNota2()
                        + "," + aluno.getNota3()
                        + "," + aluno.getNota4();

                bufferedWriter.write(alunoData);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
