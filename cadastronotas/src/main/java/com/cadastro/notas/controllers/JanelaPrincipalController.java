package com.cadastro.notas.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;

import com.cadastro.notas.models.Aluno;
import com.cadastro.notas.models.Notas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JanelaPrincipalController implements Initializable {

    @FXML
    private Button buttonNovoAluno;

    @FXML
    private TableColumn<Aluno, String> cpf;

    @FXML
    private TableColumn<Aluno, String> dataNascimento;

    @FXML
    private TableColumn<Aluno, String> email;

    @FXML
    private TableColumn<Aluno, Double> mediaNotas;

    @FXML
    private TableColumn<Aluno, String> nome;

    @FXML
    private TableColumn<Aluno, Double> nota1;

    @FXML
    private TableColumn<Aluno, Double> nota2;

    @FXML
    private TableColumn<Aluno, Double> nota3;

    @FXML
    private TableColumn<Aluno, Double> nota4;

    @FXML
    private TableView<Aluno> tabela;

    ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    @FXML
    public void handleCreateNewAluno(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cadastro/notas/views/NovoAluno.fxml"));
            Parent root = fxmlLoader.load();

            NovoAlunoController novoAlunoController = fxmlLoader.getController();
            novoAlunoController.setAlunos(alunos);

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Aluno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        dataNascimento.setCellValueFactory(new PropertyValueFactory<Aluno, String>("dataNascimento"));
        cpf.setCellValueFactory(new PropertyValueFactory<Aluno, String>("cpf"));
        email.setCellValueFactory(new PropertyValueFactory<Aluno, String>("email"));
        nota1.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota1"));
        nota2.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota2"));
        nota3.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota3"));
        nota4.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota4"));
        mediaNotas.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("mediaNotas"));
        tabela.setItems(alunos);

        // inicializarAlunos();

    }

    private void inicializarAlunos() {
        String caminho = getClass().getResource("../alunos.txt").toString();
        FileReader fileReader = new FileReader(caminho);
        BufferedReader reader = new BufferedReader(fileReader);

        while (reader.ready()) {
            String[] alunotexto = reader.readLine().split(",");
            System.out.println(alunotexto);

            Aluno aluno = new Aluno(alunotexto[0], alunotexto[1], alunotexto[2], alunotexto[3], new Notas(0, 0, 0, 0));

        }
    }

}
