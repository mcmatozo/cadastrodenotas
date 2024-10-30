package com.cadastro.notas.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableCell;
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

    @FXML
    private TableColumn<Aluno, Void> editColumn;

    @FXML
    private TableColumn<Aluno, Void> removeColumn;

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

        adicionarBotoesDeAcao();

        nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        dataNascimento.setCellValueFactory(cellData -> cellData.getValue().dataNascimentoProperty());
        cpf.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        nota1.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota1"));
        nota2.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota2"));
        nota3.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota3"));
        nota4.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota4"));
        mediaNotas.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("mediaNotas"));

        tabela.setItems(alunos);

        inicializarAlunos();
    }

    private void inicializarAlunos() {
        try {
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);

            while (reader.ready()) {
                String[] alunotexto = reader.readLine().split(",");
                System.out.println(alunotexto);

                Aluno aluno = new Aluno(alunotexto[0], alunotexto[1], alunotexto[2], alunotexto[3],
                        new Notas(
                                Double.parseDouble(alunotexto[4]),
                                Double.parseDouble(alunotexto[5]),
                                Double.parseDouble(alunotexto[6]),
                                Double.parseDouble(alunotexto[7])));

                alunos.add(aluno);

            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adicionarBotoesDeAcao() {

        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Editar");

            {
                editButton.setOnAction(event -> {
                    Aluno aluno = getTableView().getItems().get(getIndex());
                    editarAluno(aluno);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Excluir");

            {
                deleteButton.setOnAction(event -> {
                    Aluno aluno = getTableView().getItems().get(getIndex());
                    excluirAluno(aluno, getIndex());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }

        });
    }

    private void editarAluno(Aluno aluno) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cadastro/notas/views/NovoAluno.fxml"));
            Parent root = fxmlLoader.load();

            NovoAlunoController novoAlunoController = fxmlLoader.getController();
            novoAlunoController.setAlunos(alunos);

            if (aluno != null) {
                novoAlunoController.setAluno(aluno);
                novoAlunoController.habilitarEdicao();
            }

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Aluno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluirAluno(Aluno aluno, int index) {
        System.out.println(index);
        alunos.remove(aluno);
        atualizarArquivoAlunos();

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
