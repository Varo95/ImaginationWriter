package com.IW.controllers;

import com.IW.interfaces.IBeans.ICharacter;
import com.IW.interfaces.IBeans.IScene;
import com.IW.model.dao.BookDAO;
import com.IW.utils.Tools;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class BookItemsController {

    @FXML
    private Label lb_description;
    @FXML
    private ImageView iview_photo;
    @FXML
    private TableView<ICharacter> table_items_characters;
    @FXML
    private TableView<IScene> table_items_scenes;
    @FXML
    private TableColumn<IScene, String> tc_items_scenes;
    @FXML
    private TableColumn<ICharacter, String> tc_items_characters;

    private static BookDAO current_book;

    private static final String default_photo_scene = "assets/user_default.png";
    private static final String default_photo_character = "assets/user_default.png";

    @FXML
    protected void initialize() {
        configureTables();
        table_items_characters.setItems(FXCollections.observableList(current_book.getCharacters()));
        table_items_scenes.setItems(FXCollections.observableList(current_book.getScenes()));
    }

    private void configureTables() {
        tc_items_scenes.setCellValueFactory(eachScene -> new SimpleStringProperty(eachScene.getValue().getTitle()));
        tc_items_characters.setCellValueFactory(eachCharacter -> new SimpleStringProperty(eachCharacter.getValue().getName()));
        table_items_scenes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lb_description.setText(newValue.getDescription());
                iview_photo.setImage(Objects.requireNonNullElseGet(Tools.getImage(newValue.getPhoto(), false), () -> Tools.getImage(default_photo_scene, true)));
            }
        });
        table_items_characters.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                lb_description.setText(newValue.getDescription());
                iview_photo.setImage(Objects.requireNonNullElseGet(Tools.getImage(newValue.getPhoto(), false), () -> Tools.getImage(default_photo_character, true)));
            }
        });
    }

    public static void setCurrent_book(BookDAO book) {
        current_book = book;
    }
}
