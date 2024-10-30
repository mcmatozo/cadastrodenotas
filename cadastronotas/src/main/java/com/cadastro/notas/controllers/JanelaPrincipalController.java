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
    // FXMLpra conectar os componentes da interface
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

    // lista observável de alunos
    ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    @FXML
    public void handleCreateNewAluno(ActionEvent event) {
        try {
            // carrega a tela para cadastrar um novo aluno
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cadastro/notas/views/NovoAluno.fxml"));
            Parent root = fxmlLoader.load();

            // passa a lista de alunos para o controlador da nova tela
            NovoAlunoController novoAlunoController = fxmlLoader.getController();
            novoAlunoController.setAlunos(alunos);

            // cria e exibe a nova janela
            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Aluno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace(); // imprime a stack trace em caso de erro
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // inicializa a tabela e seus botões de ação
        adicionarBotoesDeAcao();

        // configura as colunas da tabela para exibir as propriedades dos alunos
        nome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        dataNascimento.setCellValueFactory(cellData -> cellData.getValue().dataNascimentoProperty());
        cpf.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        nota1.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota1"));
        nota2.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota2"));
        nota3.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota3"));
        nota4.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("nota4"));
        mediaNotas.setCellValueFactory(new PropertyValueFactory<Aluno, Double>("mediaNotas"));

        // define a lista de alunos na tabela
        tabela.setItems(alunos);

        // carrega os alunos do arquivo ao iniciar
        inicializarAlunos();
    }

    private void inicializarAlunos() {
        try {
            // lê os dados dos alunos de um arquivo
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);

            // para cada linha, cria um objeto Aluno e adiciona à lista
            while (reader.ready()) {
                String[] alunotexto = reader.readLine().split(",");
                Aluno aluno = new Aluno(alunotexto[0], alunotexto[1], alunotexto[2], alunotexto[3],
                        new Notas(
                                Double.parseDouble(alunotexto[4]),
                                Double.parseDouble(alunotexto[5]),
                                Double.parseDouble(alunotexto[6]),
                                Double.parseDouble(alunotexto[7])));

                alunos.add(aluno);
            }
            reader.close(); // fecha o leitor após a leitura
        } catch (Exception e) {
            System.out.println(e.getMessage()); // imprime o erro, se houver
        }
    }

    private void adicionarBotoesDeAcao() {
        // adiciona a funcionalidade de edição na tabela
        // lambda é algo que ajuda a implementar interfaces, algo que diz "faz isso"
        // <> cria nova celula na tabela, para cada célula na coluna, crie uma nova
        // célula
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

        // adiciona a funcionalidade de remoção na tabela
        removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Excluir");

            {
                // define uma ação
                // encontra aluno específico
                // exclui o aluno
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
            // carrega a tela para editar um aluno existente
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cadastro/notas/views/NovoAluno.fxml"));
            Parent root = fxmlLoader.load();

            NovoAlunoController novoAlunoController = fxmlLoader.getController();
            novoAlunoController.setAlunos(alunos);

            if (aluno != null) {
                novoAlunoController.setAluno(aluno); // passa o aluno a ser editado
                novoAlunoController.habilitarEdicao(); // habilita a edição no controlador
            }

            Stage stage = new Stage();
            stage.setTitle("Cadastrar Novo Aluno");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace(); // imprime a stack trace em caso de erro
        }
    }

    private void excluirAluno(Aluno aluno, int index) {
        alunos.remove(aluno); // remove o aluno da lista
        atualizarArquivoAlunos(); // atualiza o arquivo com os dados atuais
    }

    private void atualizarArquivoAlunos() {
        try {
            String path = System.getProperty("user.dir")
                    + "\\cadastronotas\\src\\main\\java\\com\\cadastro\\notas\\alunos.txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            // escreve os dados atuais dos alunos de volta no arquivo
            // concatena os novos dados
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

            bufferedWriter.close(); // fecha o escritor após a escrita
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
