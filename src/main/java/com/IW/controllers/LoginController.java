package com.IW.controllers;

import com.IW.App;
import com.IW.model.dao.AuthorDAO;
import com.IW.utils.Dialog;
import com.IW.utils.Tools;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private ImageView imageview;
    @FXML
    private MFXTextField tf_name;
    @FXML
    private MenuItem about;
    @FXML
    private MFXPasswordField tf_passwd;
    @FXML
    private CheckMenuItem connect;

    @FXML
    protected void initialize() {
        //TODO iniciar con H2
        imageview.setImage(Tools.getImage("assets/user_default.png", true));
        tf_name.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onClickLogin();
        });
        tf_passwd.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) onClickLogin();
        });
        about.setOnAction(event -> App.loadScene(new Stage(), "about", "Sobre Imagination Writer", true, false));
        connect.setOnAction(event -> {
            //TODO cambiar conexión a MYSQL
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
}
