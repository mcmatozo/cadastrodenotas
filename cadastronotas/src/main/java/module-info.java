module com.cadastro.notas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cadastro.notas to javafx.fxml;
    exports com.cadastro.notas;
}
