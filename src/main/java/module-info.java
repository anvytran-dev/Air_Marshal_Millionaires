module com.millionaires.airmarshal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.json;
    requires java.desktop;

    opens com.millionaires.airmarshal to javafx.fxml;
    exports com.millionaires.airmarshal;
    exports com.millionaires.airmarshal.archive;
    opens com.millionaires.airmarshal.archive to javafx.fxml;
}