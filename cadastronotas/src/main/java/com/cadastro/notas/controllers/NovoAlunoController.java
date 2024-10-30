package com.cadastro.notas.controllers;

// importações necessárias para manipulação de arquivos, datas e interface gráfica
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

    // botão para criar um novo aluno
    @FXML
    private Button criarAlunoButton;
    private BooleanProperty criarAlunoButtonVisivel = new SimpleBooleanProperty(true); // Controle de visibilidade

    // botão para editar um aluno existente
    @FXML
    private Button editarAlunoButton;
    private BooleanProperty editarAlunoButtonVisivel = new SimpleBooleanProperty(false); // Controle de visibilidade

    // campos de entrada de dados do aluno
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

    // objeto Aluno que será editado ou criado
    private Aluno aluno;

    // lista de alunos cadastrados
    private ObservableList<Aluno> alunos;

    // método para receber a lista de alunos
    public void setAlunos(ObservableList<Aluno> alunos) {
        this.alunos = alunos;
    }

    // método chamado para salvar um novo aluno
    @FXML
    public void handleSalvar() {
        // coleta dados do usuário
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String dataNascimento = dataNascimentoField.getValue().toString();
        String email = emailField.getText();
        double nota1 = Double.parseDouble(notaOne.getText());
        double nota2 = Double.parseDouble(notaTwo.getText());
        double nota3 = Double.parseDouble(notaThree.getText());
        double nota4 = Double.parseDouble(notaFour.getText());

        // cria um novo objeto Notas e Aluno
        Notas notas = new Notas(nota1, nota2, nota3, nota4);
        Aluno aluno = new Aluno(nome, dataNascimento, cpf, email, notas);

        // adiciona o aluno à lista
        this.alunos.add(aluno);

        // grava as informações no arquivo
        try {
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            FileWriter fileFileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileFileWriter);

            // formata os dados do aluno para gravação
            String alunoData = aluno.getNome()
                    + "," + aluno.getDataNascimento()
                    + "," + aluno.getCpf()
                    + "," + aluno.getEmail()
                    + "," + aluno.getNota1()
                    + "," + aluno.getNota2()
                    + "," + aluno.getNota3()
                    + "," + aluno.getNota4();

            // escreve os dados no arquivo
            bufferedWriter.write(alunoData);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (Exception e) {
            // tratar exceções de gravação (opcional)
        }

        // fecha a janela após salvar
        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    // método chamado para editar um aluno existente
    @FXML
    public void handleEditar() {
        // coleta dados do usuário
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String dataNascimento = dataNascimentoField.getValue().toString();
        String email = emailField.getText();
        double nota1 = Double.parseDouble(notaOne.getText());
        double nota2 = Double.parseDouble(notaTwo.getText());
        double nota3 = Double.parseDouble(notaThree.getText());
        double nota4 = Double.parseDouble(notaFour.getText());

        // atualiza os dados do aluno existente
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setDataNascimento(dataNascimento);
        aluno.setEmail(email);

        // atualiza o arquivo de alunos
        atualizarArquivoAlunos();

        // fecha a janela após editar
        Stage stage = (Stage) nomeField.getScene().getWindow();
        stage.close();
    }

    // método chamado na inicialização da classe
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // define visibilidade dos botões
        criarAlunoButton.visibleProperty().bind(criarAlunoButtonVisivel);
        editarAlunoButton.visibleProperty().bind(editarAlunoButtonVisivel);
    }

    // método para preencher os campos ao editar um aluno
    public void setAluno(Aluno aluno) {
        // preenche os campos com os dados do aluno
        cpfField.setText(aluno.getCpf());
        nomeField.setText(aluno.getNome());
        dataNascimentoField
                .setValue(LocalDate.parse(aluno.getDataNascimento(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        notaOne.setText(aluno.getNota1().toString());
        notaTwo.setText(aluno.getNota2().toString());
        notaThree.setText(aluno.getNota3().toString());
        notaFour.setText(aluno.getNota4().toString());
        emailField.setText(aluno.getEmail());

        // armazena o aluno para uso posterior
        this.aluno = aluno;
    }

    // habilita modo de edição
    public void habilitarEdicao() {
        criarAlunoButtonVisivel.set(false); // Esconde o botão de criar
        editarAlunoButtonVisivel.set(true); // Mostra o botão de editar
    }

    // atualiza o arquivo com os dados dos alunos
    private void atualizarArquivoAlunos() {
        try {
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            // escreve todos os alunos no arquivo
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
            // tratar exceções de gravação (opcional)
        }
    }
}
