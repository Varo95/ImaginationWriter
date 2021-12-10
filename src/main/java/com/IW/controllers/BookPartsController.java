package com.IW.controllers;

import com.IW.interfaces.IBeans.IChapter;
import com.IW.interfaces.IBeans.IPart;
import com.IW.model.dao.BookDAO;
import com.IW.model.dao.PartDAO;
import com.IW.model.objects.Part;
import com.IW.utils.Dialog;
import com.IW.utils.Tools;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookPartsController {

    @FXML
    private TableView<IChapter> table_chapters;
    @FXML
    private TableView<IPart> table_parts;
    @FXML
    private TableColumn<IChapter, String> tc_chapters;
    @FXML
    private TableColumn<IPart, String> tc_parts;
    @FXML
    private Button btn_add_part;
    @FXML
    private Button btn_edit_part;
    @FXML
    private Button btn_remove_part;
    @FXML
    private Button btn_add_chapter;
    @FXML
    private Button btn_edit_chapter;
    @FXML
    private Button btn_remove_chapter;
    private static BookDAO current_book;
    private static PartDAO current_part;

    @FXML
    protected void initialize() {
        configureTables();
        setIcons();
        table_parts.setItems(FXCollections.observableList(current_book.getParts()));
        btn_add_part.setOnAction(event -> {
            PartDAO p = new PartDAO();
            p.setNPart(Dialog.showDialogPart_Chapter("Nueva parte", "Introduce el número de la nueva parte", ""));
            if (p.getNPart() != -1 && p.getNPart() != 0) {
                p.persist();
                current_book.getParts().add(p);
                current_book.persist();
                table_parts.getItems().add(current_part);
            }
        });
        btn_edit_part.setOnAction(event -> {
            current_part.setNPart(Dialog.showDialogPart_Chapter("Nueva parte", "Introduce el número de la nueva parte", ""));
            if (current_part.getNPart() != -1 && current_part.getNPart() != 0) {
                table_parts.getItems().remove(current_part);
                current_part.persist();
                current_book.persist();
                table_parts.getItems().add(current_part);
            }
        });
        btn_remove_part.setOnAction(event -> {
            current_part.remove();
            table_parts.getItems().remove(current_part);
        });
        btn_add_chapter.setOnAction(event -> {

        });
        btn_edit_chapter.setOnAction(event -> {

        });
        btn_remove_chapter.setOnAction(event -> {

        });
    }

    private void configureTables() {
        tc_parts.setCellValueFactory(eachPart -> new SimpleStringProperty(eachPart.getValue().getNPart() + ""));
        table_parts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (btn_add_chapter.isDisabled() && btn_edit_part.isDisabled() && btn_remove_part.isDisabled()) {
                    btn_add_chapter.setDisable(false);
                    btn_edit_part.setDisable(false);
                    btn_remove_part.setDisable(false);
                }
                table_chapters.setItems(FXCollections.observableList(newValue.getChapters()));
                current_part = new PartDAO(newValue.getId());
            }
        });
        table_chapters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (btn_edit_chapter.isDisabled() && btn_remove_chapter.isDisabled()) {
                    btn_edit_chapter.setDisable(false);
                    btn_remove_chapter.setDisable(false);
                }
            }
        });
        tc_chapters.setCellValueFactory(eachChapter -> new SimpleStringProperty(eachChapter.getValue().getNPage() + ""));
    }

    public static void setCurrent_book(BookDAO book) {
        current_book = book;
    }

    private void setIcons() {
        btn_add_part.setGraphic(Tools.getIcon("add"));
        btn_edit_part.setGraphic(Tools.getIcon("edit"));
        btn_remove_part.setGraphic(Tools.getIcon("remove"));
        btn_add_chapter.setGraphic(Tools.getIcon("add"));
        btn_edit_chapter.setGraphic(Tools.getIcon("edit"));
        btn_remove_chapter.setGraphic(Tools.getIcon("remove"));
    }
}
