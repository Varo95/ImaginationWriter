package com.IW.controllers;

import com.IW.interfaces.IBeans.IChapter;
import com.IW.interfaces.IBeans.IPart;
import com.IW.model.dao.BookDAO;
import com.IW.model.dao.ChapterDAO;
import com.IW.model.dao.PartDAO;
import com.IW.model.objects.Chapter;
import com.IW.model.objects.Part;
import com.IW.utils.PersistenceUnit;
import com.IW.utils.Tools;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class EditorController {

    @FXML
    private TreeTableView<String> table_parts_chapters;
    @FXML
    private TreeTableColumn<String, String> tc_part_chapter;
    @FXML
    private Button btn_controlPC;
    @FXML
    private ComboBox<IPart> cb_parts;
    @FXML
    private ComboBox<IChapter> cb_chapters;
    @FXML
    private TextArea ta_chapter;

    private static BookDAO current_book;
    private Timer auto_save;
    private TimerTask tt_autosave;

    @FXML
    protected void initialize() {
        //SampleData
        PersistenceUnit.setType("H2");
        PersistenceUnit.changeConnection();
        current_book = new BookDAO();
        current_book.setTitle("Hola");
        Chapter cp = new Chapter();
        cp.setNPage(1);
        Chapter cp1 = new Chapter();
        cp1.setNPage(2);
        Part p = new Part();
        p.setNPart(1);
        p.setBook(current_book);
        Part c = new Part();
        c.setNPart(2);
        c.setBook(current_book);
        p.setChapters(List.of(cp, cp1));
        c.setChapters(List.of(cp));
        cp.setText("Hola esto es una prueba");
        current_book.setParts(List.of(c, p));
        current_book.persist();
        configureTreeTableAndComboBox();
        setIcons();
        setAutosave();
        btn_controlPC.getScene().getWindow().setOnCloseRequest(event -> {
            if (auto_save != null) {
                tt_autosave.cancel();
                auto_save.cancel();
            }
        });
    }

    private void configureTreeTableAndComboBox() {
        tc_part_chapter.setCellValueFactory((TreeTableColumn.CellDataFeatures<String, String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue()));
        TreeItem<String> root = new TreeItem<>(current_book.getTitle());
        for (IPart p : current_book.getParts()) {
            TreeItem<String> tp = new TreeItem<>("Parte " + p.getNPart());
            root.getChildren().add(tp);
            for (IChapter c : p.getChapters()) {
                TreeItem<String> tc = new TreeItem<>("Capítulo " + c.getNPage());
                tp.getChildren().add(tc);
            }
        }
        table_parts_chapters.setRoot(root);
        cb_parts.setItems(FXCollections.observableList(current_book.getParts()));
        cb_parts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cb_chapters.setItems(FXCollections.observableList(newValue.getChapters()));
                //TODO actualizar cosas de las partes
            }
        });
        cb_chapters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ta_chapter.setText(newValue.getText());
                //TODO actualizar cosas de los capítulos
            }
        });
        cb_parts.setConverter(new StringConverter<>() {
            @Override
            public String toString(IPart object) {
                return object == null ? null : object.toCombobox();
            }

            @Override
            public IPart fromString(String string) {
                IPart p = null;
                if (string != null) {
                    long id = Long.parseLong(string.substring(string.lastIndexOf(".")));
                    p = new PartDAO(id);
                }
                return p;
            }
        });
        cb_chapters.setConverter(new StringConverter<>() {
            @Override
            public String toString(IChapter object) {
                return object == null ? null : object.toCombobox();
            }

            @Override
            public IChapter fromString(String string) {
                IChapter c = null;
                if (string != null) {
                    long id = Long.parseLong(string.substring(string.lastIndexOf(".")));
                    c = new ChapterDAO(id);
                }
                return c;
            }
        });
    }

    private void setAutosave() {
        auto_save = new Timer();
        tt_autosave = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> current_book.persist());
            }
        };
        auto_save.schedule(tt_autosave, 0, 30000);
    }

    private void setIcons() {
        btn_controlPC.setGraphic(Tools.getIcon("list-cp"));
    }

    public static void setBook(BookDAO book) {
        current_book = book;
    }
}
