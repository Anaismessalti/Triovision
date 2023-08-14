module com.example.triovisiongame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.triovisiongame to javafx.fxml;
    exports com.example.triovisiongame;
    exports com.example.triovisiongame.controllers;
    opens com.example.triovisiongame.controllers to javafx.fxml;
}