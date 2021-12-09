package com.IW.controllers;

import com.IW.App;
import com.IW.interfaces.IBeans.IAuthor;
import com.IW.interfaces.IBeans.IBook;
import com.IW.model.dao.AuthorDAO;
import com.IW.model.dao.BookDAO;
import com.IW.utils.Dialog;
import com.IW.utils.Tools;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Objects;

public class BooksController {

    @FXML
    private MenuItem mi_close_session;
    @FXML
    private MenuItem mi_edit_profile;
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
    private TableColumn<IAuthor, String> tc_authors_name;
    @FXML
    private TableView<IBook> table_books;
    @FXML
    private TableColumn<IBook, String> tc_book_title;
    @FXML
    private ImageView iview_book_cover;
    @FXML
    private TableView<IAuthor> table_all_authors;
    @FXML
    private TableColumn<IAuthor, String> tc_all_authors_name;

    private static AuthorDAO actual_author;

    @FXML
    protected void initialize() {
        table_books.setItems(FXCollections.observableList(actual_author.getBooks()));
        table_all_authors.setItems(FXCollections.observableList(AuthorDAO.listAll()));
        table_all_authors.getItems().remove(actual_author);
        table_books.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (btn_edit_book.isDisabled() && btn_delete_book.isDisabled()) {
                    btn_edit_book.setDisable(false);
                    btn_delete_book.setDisable(false);
                }
                btn_delete_book.setDisable(!newValue.getCreator().equals(actual_author));
                iview_book_cover.setImage(Objects.requireNonNullElse(Tools.getImage(newValue.getCover(), false), Tools.default_photo_cover));
                if (newValue.getEditors() != null)
                    table_authors.setItems(FXCollections.observableList(newValue.getEditors()));
                btn_delete_author.setDisable(newValue.getCreator().equals(actual_author));
                table_authors.getItems().clear();
                if (newValue.getEditors() != null)
                    table_authors.getItems().addAll(newValue.getEditors());
                table_authors.getItems().add(newValue.getCreator());
                btn_add_author.setDisable(table_all_authors.getItems().size() == 0);
            }
        });
        table_authors.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (btn_delete_author.isDisabled()) {
                    btn_delete_book.setDisable(false);
                }
            }
        });
        btn_add_book.setOnAction(event -> {
            BookDAO b = new BookDAO();
            b.setTitle(Dialog.showDialogString("Titulo del nuevo libro", "Titulo Libro", "Introduce el nuevo titulo del libro"));
            String cover = Dialog.showDialogExamine("Portada del libro", "Selecciona la portada", "Introduce la URL de la foto del libro");
            if (b.getTitle() != null) {
                if (!cover.equals("")) {
                    if (Tools.FileCopy(cover, "assets/books_covers/" + b.getTitle() + cover.substring(cover.lastIndexOf(".")))) {
                        Dialog.showInformation("", "Exito al copiar la foto", "Hemos guardado una copia de tu foto en otra carpeta!");
                        b.setCover("assets/books_covers/" + b.getTitle() + cover.substring(cover.lastIndexOf(".")));
                    }
                }
                b.setCreator(actual_author);
                b.persist();
                table_books.getItems().add(b);
            }
        });
        btn_edit_book.setOnAction(event -> {
            if (table_books.getSelectionModel().getSelectedItem() != null) {
                EditorController.setBook(new BookDAO(table_books.getSelectionModel().getSelectedItem().getId()));
                App.loadScene(new Stage(), "editor", " Imagination Writer - " + table_books.getSelectionModel().getSelectedItem().getTitle(), false, true);
            }
        });
        btn_delete_book.setOnAction(event -> {
            if (table_books.getSelectionModel().getSelectedItem() != null) {
                if (table_books.getSelectionModel().getSelectedItem().getCreator().equals(actual_author)) {
                    BookDAO b = new BookDAO(table_books.getSelectionModel().getSelectedItem().getId());
                    b.remove();
                    table_books.getItems().remove(b);
                } else {
                    Dialog.showError("Error", "Este libro no es tuyo", "No puedes borrar un libro que no es tuyo");
                }
            }
        });
        btn_add_author.setOnAction(event -> {
            if (table_books.getSelectionModel().getSelectedItem() != null) {
                BookDAO b = new BookDAO(table_books.getSelectionModel().getSelectedItem().getId());
                if (table_all_authors.getSelectionModel().getSelectedItem() != null) {
                    AuthorDAO a = new AuthorDAO(table_all_authors.getSelectionModel().getSelectedItem().getId());
                    b.addAuthor(a);
                    b.persist();
                    table_authors.getItems().add(a);
                } else {
                    Dialog.showError("Error", "Autor no seleccionado", "No has seleccionado ningún autor en la tabla de todos los autores");
                }
            }
        });
        btn_delete_author.setOnAction(event -> {
            if (table_authors.getSelectionModel().getSelectedItem() != null && table_books.getSelectionModel().getSelectedItem() != null) {
                BookDAO b = new BookDAO(table_books.getSelectionModel().getSelectedItem().getId());
                if (table_all_authors.getSelectionModel().getSelectedItem() != null) {
                    AuthorDAO a = new AuthorDAO(table_all_authors.getSelectionModel().getSelectedItem().getId());
                    b.deleteAuthor(a);
                    b.persist();
                    table_authors.getItems().remove(a);
                } else {
                    Dialog.showError("Error", "Autor no seleccionado", "No has seleccionado ningún autor en la tabla de todos los autores");
                }
            }
        });
        mi_close_session.setOnAction(event -> {
            App.closeScene((Stage) btn_add_author.getScene().getWindow());
            App.loadScene(new Stage(), "login", " Imagination Writer - Login", false, false);
        });
        mi_edit_profile.setOnAction(event -> {
            ProfileController.setActual_author(actual_author);
            App.loadScene(new Stage(), "profile", "Perfil", true, false);
        });
        configureTableColumns();
        addTableBookButtons();
        setIcons();
    }

    private void setIcons() {
        btn_add_book.setGraphic(Tools.getIcon("add"));
        btn_edit_book.setGraphic(Tools.getIcon("edit"));
        btn_delete_book.setGraphic(Tools.getIcon("remove"));
        btn_add_author.setGraphic(Tools.getIcon("arrow-next"));
        btn_delete_author.setGraphic(Tools.getIcon("arrow-back"));
        mi_close_session.setGraphic(Tools.getIcon("close-session"));
        mi_edit_profile.setGraphic(Tools.getIcon("profile"));
    }

    private void addTableBookButtons() {
        TableColumn<IBook, Void> tc_book_scenes = new TableColumn<>("");
        tc_book_scenes.setStyle("-fx-alignment: CENTER;");
        Callback<TableColumn<IBook, Void>, TableCell<IBook, Void>> btn_scenes = new Callback<>() {
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
                                BookDAO b = new BookDAO(getTableView().getItems().get(getIndex()).getId());
                                BookItemsController.setCurrent_book(b);
                                App.loadScene(new Stage(), "book_items", " Imagination Writer - " + getTableView().getItems().get(getIndex()).getTitle(), true, false);
                            });
                            btn.setStyle("-fx-background-color: lightblue");
                            setGraphic(btn);
                        }
                    }
                };
            }
        };
        //Ponemos icono al título de la tabla
        tc_book_scenes.setGraphic(Tools.getIcon("book_scenes"));
        //Ponemos los botones en las celdas
        tc_book_scenes.setCellFactory(btn_scenes);
        //Añadimos las columnas a la tabla de libros
        table_books.getColumns().add(tc_book_scenes);
        //Para que se quede ajustado al tamaño de la tabla
        tc_book_title.prefWidthProperty().bind(table_books.widthProperty().divide(1.70));
        tc_book_scenes.prefWidthProperty().bind(table_books.widthProperty().divide(2));
        tc_book_scenes.setResizable(false);
        tc_book_title.setResizable(false);
    }

    private void configureTableColumns() {
        tc_authors_name.setCellValueFactory(eachAuthor -> new SimpleStringProperty(eachAuthor.getValue().getName()));
        tc_book_title.setCellValueFactory(eachBook -> new SimpleStringProperty(eachBook.getValue().getTitle()));
        tc_all_authors_name.setCellValueFactory(eachAuthor -> new SimpleStringProperty(eachAuthor.getValue().getName()));
    }

    /**
     * Calling this, we set the Author to the view
     *
     * @param author new Author
     */
    public static void setAuthor(AuthorDAO author) {
        actual_author = author;
    }
}
