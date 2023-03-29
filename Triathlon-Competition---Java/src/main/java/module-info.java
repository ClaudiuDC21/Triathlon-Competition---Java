module JavaProject{
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.logging.log4j;
    requires java.sql;

    opens JavaProject to javafx.fxml;
    exports JavaProject;

    opens JavaProject.Domain to javafx.fxml;
    exports JavaProject.Domain;
    exports JavaProject.Controller;
    opens JavaProject.Controller to javafx.fxml;
}