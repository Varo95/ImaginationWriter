package com.IW.controllers;

import com.IW.App;
import com.IW.model.dao.AuthorDAO;
import com.IW.utils.Dialog;
import com.IW.utils.PersistenceUnit;
import com.IW.utils.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private ImageView imageview;
    @FXML
    private TextField tf_name;
    @FXML
    private MenuItem about;
    @FXML
    private PasswordField tf_passwd;
    @FXML
    private CheckMenuItem connect;
    @FXML
    private MenuItem close;

    @FXML
    protected void initialize() {
        PersistenceUnit.setInstance("H2");
        menuItemsSetIcons();
        imageview.setImage(Tools.getImage("assets/user_default.png", true));
        tf_name.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onClickLogin();
        });
        tf_passwd.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onClickLogin();
        });
        about.setOnAction(event -> App.loadScene(new Stage(), "about", "Sobre Imagination Writer", true, false));
        connect.setOnAction(event -> {
            if(PersistenceUnit.isH2()){
                PersistenceUnit.setInstance("MariaDB");
            }else{
                PersistenceUnit.setInstance("H2");
            }
        });
    }

    /**
     * This event is fired when user try to login into app
     */
    private void onClickLogin() {
        if (!tf_name.getText().trim().equals("") && !tf_passwd.getText().trim().equals("")) {
            //TODO lógica de negocio
            AuthorDAO a = new AuthorDAO();
            //set nickname y setpassword con las contraseñas REMOVIENDO INTROS
            if (a.checkUser()) {
                //cogemos el autor de la base de datos ya completo con "all"
                //TODO descomentar esta línea cuando tengamos la vista
                //App.closeScene((Stage) tf_name.getScene().getWindow());
                //App.loadScene(new Stage(), "main", " Imagination Writer", true, true);
                tf_name.clear();
                tf_passwd.clear();
            } else {
                Dialog.showError("Login incorrecto", "Usuario y contraseña incorrectos", "Si no recuerda su usuario y contraseña, regístrese de nuevo");
            }
        }
    }

    private void menuItemsSetIcons(){
        close.setGraphic(Tools.getIcon("close"));
        connect.setGraphic(Tools.getIcon("cloud"));
        about.setGraphic(Tools.getIcon("info"));
    }
}
