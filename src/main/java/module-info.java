module com.example.inzynieria_sprint_n {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires junit;
    requires commons.csv;

    opens com.example.inzynieria_sprint_n to javafx.fxml;
    exports com.example.inzynieria_sprint_n;
    exports com.example.inzynieria_sprint_n.tests;
    opens com.example.inzynieria_sprint_n.tests to javafx.fxml;
    exports com.example.inzynieria_sprint_n.password;
    opens com.example.inzynieria_sprint_n.password to javafx.fxml;
}