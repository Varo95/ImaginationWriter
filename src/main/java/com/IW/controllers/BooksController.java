package com.IW.controllers;

import com.IW.App;
import com.IW.interfaces.IBeans.IBook;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.model.dao.AuthorDAO;
import com.IW.model.objects.Book;
import com.IW.utils.Dialog;
import com.IW.utils.Tools;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BooksController {

    @FXML
    private MenuItem mi_close_session;
    @FXML
    private Button btn_add_book;
    @FXML
    private Button btn_edit_book;
    @FXML
    private Button btn_delete_book;
    @FXML
    private Button btn_add_author;
    @FXML
    private Button btn_delete_author;
    @FXML
    private TableView<IAuthor> table_authors;
    @FXML
    private TableColumn<IAuthor, String> tc_author;
    @FXML
    private TableView<IBook> table_books;
    @FXML
    private TableColumn<IBook, String> tc_book;

    private static AuthorDAO a;

    @FXML
    protected void initialize() {
        //table_books.setItems(FXCollections.observableList(AuthorDAO.listAll()));
        setIcons();
        table_books.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (btn_edit_book.isDisabled() && btn_delete_book.isDisabled()) {
                    btn_edit_book.setDisable(false);
                    btn_delete_book.setDisable(false);
                }
                //Insertar datos desde el DAO en la tabla de autores
            }
        });
        table_authors.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                if(btn_delete_author.isDisabled()){
                    btn_delete_book.setDisable(false);
                }
            }
        });
        btn_add_book.setOnAction(event -> {

        });
        btn_edit_book.setOnAction(event -> {
            if (table_books.getSelectionModel().getSelectedItem() != null) {

            }
        });
        btn_delete_book.setOnAction(event -> {
            if (table_books.getSelectionModel().getSelectedItem() != null) {

            }
        });
        btn_add_author.setOnAction(event -> {
            if (table_books.getSelectionModel().getSelectedItem() != null) {

            }
        });
        btn_delete_author.setOnAction(event -> {
            if (table_authors.getSelectionModel().getSelectedItem() != null && table_books.getSelectionModel().getSelectedItem() != null) {

            }
        });
        mi_close_session.setOnAction(event -> {
            App.closeScene((Stage) btn_add_author.getScene().getWindow());
            App.loadScene(new Stage(), "login", " Imagination Writer - Login", true, false);
        });
        addTableBookButtons();
        configureTableColumns();
    }

    private void setIcons() {
        btn_add_book.setGraphic(Tools.getIcon("add"));
        btn_edit_book.setGraphic(Tools.getIcon("edit"));
        btn_delete_book.setGraphic(Tools.getIcon("remove"));
        btn_add_author.setGraphic(Tools.getIcon("arrow-next"));
        btn_delete_author.setGraphic(Tools.getIcon("arrow-back"));
        mi_close_session.setGraphic(Tools.getIcon("close-session"));
    }

    private void addTableBookButtons() {
        TableColumn<IBook, Void> col_view_chars = new TableColumn<>("");
        col_view_chars.setGraphic(Tools.getIcon("book_chars"));
        col_view_chars.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<IBook, Void>, TableCell<IBook, Void>> view_chars = new Callback<>() {
            @Override
            public TableCell<IBook, Void> call(final TableColumn<IBook, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button();

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setGraphic(Tools.getIcon("book_chars"));
                            btn.setOnAction((ActionEvent event) -> {
                                //TODO lanzar la vista de ver personajes con el libro
                                //IBook b = new BookDAO(getTableView().getItems().get(getIndex()).getId());
                                //CharView.setChars(b.getScenes())
                                //App.loadScene(...)
                            });
                            btn.setStyle("-fx-background-color: rgb(241,65,65);");
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        col_view_chars.setCellFactory(view_chars);
        table_books.getColumns().add(col_view_chars);
        TableColumn<IBook, Void> col_view_scenes = new TableColumn<>("");
        col_view_scenes.setGraphic(Tools.getIcon("book_scenes"));
        col_view_scenes.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<IBook, Void>, TableCell<IBook, Void>> view_scenes = new Callback<>() {
            @Override
            public TableCell<IBook, Void> call(final TableColumn<IBook, Void> param) {
                return new TableCell<>() {
                    private final Button btn = new Button();

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            btn.setGraphic(Tools.getIcon("book_scenes"));
                            btn.setOnAction((ActionEvent event) -> {
                                //TODO lanzar la vista de ver las escenas del libro
                                //IBook b = new BookDAO(getTableView().getItems().get(getIndex()).getId());
                                //CharView.setChars(b.getScenes())
                                //App.loadScene(...)
                            });
                            btn.setStyle("-fx-background-color: rgb(241,65,65);");
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        col_view_scenes.setCellFactory(view_scenes);
        table_books.getColumns().add(col_view_scenes);
        tc_book.prefWidthProperty().bind(table_books.widthProperty().divide(1.70));
        col_view_scenes.prefWidthProperty().bind(table_books.widthProperty().divide(5));
        col_view_chars.prefWidthProperty().bind(table_books.widthProperty().divide(5));
        col_view_scenes.setResizable(false);
        col_view_chars.setResizable(false);
    }

    private void configureTableColumns() {
        tc_author.setCellValueFactory(eachAuthor -> new SimpleStringProperty(eachAuthor.getValue().getName()));
        tc_book.setCellValueFactory(eachBook -> new SimpleStringProperty(eachBook.getValue().getTitle()));
    }

    /**
     * Calling this, we set the Author to the view
     * @param author new Author
     */
    public static void setAuthor(AuthorDAO author){
        a = author;
    }
}
