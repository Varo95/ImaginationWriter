package com.IW.controllers;

import com.IW.App;
import com.IW.model.dao.AuthorDAO;
import com.IW.utils.Dialog;
import com.IW.utils.PersistenceUnit;
import com.IW.utils.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private ImageView imageview;
    @FXML
    private TextField tf_name;
    @FXML
    private MenuItem mi_about;
    @FXML
    private PasswordField tf_passwd;
    @FXML
    private MenuItem download;
    @FXML
    private MenuItem close;
    @FXML
    private Button btn_register;
    @FXML
    private Button btn_login;

    @FXML
    protected void initialize() {
        menuItemsSetIcons();
        imageview.setImage(Tools.getImage("assets/user_default.png", true));
        tf_name.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onClickLogin();
        });
        tf_passwd.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onClickLogin();
        });
        btn_login.setOnAction(event -> onClickLogin());
        btn_register.setOnAction(event -> onClickRegister());
        mi_about.setOnAction(event -> App.loadScene(new Stage(), "about", "Sobre Imagination Writer", true, false));
        download.setOnAction(event -> {
            /*if(PersistenceUnit.getType().equals("H2")){
                PersistenceUnit.setType("MariaDB");
                PersistenceUnit.changeConnection();
            }else{
                PersistenceUnit.setType("H2");
                PersistenceUnit.changeConnection();
            }*/
        });
        close.setOnAction(event -> App.closeScene((Stage) btn_register.getScene().getWindow()));
    }

    /**
     * This event is fired when user try to login into app
     */
    private void onClickLogin() {
        if (!tf_name.getText().trim().equals("") && !tf_passwd.getText().trim().equals("")) {
            AuthorDAO a = new AuthorDAO();
            a.setName(tf_name.getText().replace("\n", ""));
            a.setPassword(tf_passwd.getText().replace("\n", ""));
            if (a.checkUser()) {
                App.closeScene((Stage) tf_name.getScene().getWindow());
                BooksController.setAuthor(a);
                App.loadScene(new Stage(), "books", " Imagination Writer - Tus Libros", true, true);
                tf_name.clear();
                tf_passwd.clear();
            } else {
                Dialog.showError("Login incorrecto", "Usuario y contraseña incorrectos", "Si no recuerda su usuario y contraseña, regístrese de nuevo");
            }
        }
    }

    private void menuItemsSetIcons(){
        close.setGraphic(Tools.getIcon("close"));
        download.setGraphic(Tools.getIcon("download"));
        mi_about.setGraphic(Tools.getIcon("info"));
    }

    private void onClickRegister(){
        AuthorDAO a = new AuthorDAO();
        ProfileController.setActual_author(a);
        App.loadScene(new Stage(),"profile", " Imagination Writer - Registro", false, false);
    }
}
