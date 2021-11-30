module ImaginationWriter {
    requires MaterialFX;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.base;
    requires org.slf4j;
    requires java.persistence;
    requires org.jfxtras.styles.jmetro;

    opens com.IW.controllers to javafx.fxml, javafx.controls, javafx.graphics, javafx.media, javafx.base, MaterialFX;
    exports com.IW;
}