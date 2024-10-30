module com.cadastro.notas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.cadastro.notas.controllers to javafx.fxml;
    opens com.cadastro.notas.models to javafx.base;

    exports com.cadastro.notas;
}
