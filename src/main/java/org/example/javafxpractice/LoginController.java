package org.example.javafxpractice;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    public TextField loginUsernameTextField;
    @FXML
    public Button loginButton;
    @FXML
    public Button registerButton;
    @FXML
    private Label loginLabel;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Button closeButton;


    public void onClickLoginButton(ActionEvent event) {
        if (loginUsernameTextField.getText().isBlank()) {
            loginLabel.setText("Username is empty");
            loginLabel.setVisible(true);
        } else if (loginPasswordField.getText().isBlank()) {
            loginLabel.setText("Password is empty");
            loginLabel.setVisible(true);
        } else {
            isValidLogin();
            System.out.println("Yes");
        }
    }

    public void closeButtonOnActionEvent(ActionEvent event) {
        System.out.println("Ye2s");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    // Login Checker
    public void isValidLogin() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection1 = dbConnection.getConnection();

        String verifyLogin = "SELECT count(1) FROM userlist WHERE userName = '" + loginUsernameTextField.getText() + "'AND password ='" + loginPasswordField.getText() + "'";

        try {
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    BaseUI ui = new BaseUI("/fxml/app.fxml");
                    loginLabel.setText("Login Sucessful");
                    loginLabel.setVisible(true);
                }else{
                    loginLabel.setText("No matches for that user/password combination was found.");
                    loginLabel.setVisible(true);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
        System.out.println("register clicked");

        SceneChange baseUI = new SceneChange("/fxml/register.fxml", stage);

        // Optionally, if you need to change the scene later:
        // baseUI.setScene("/fxml/otherScene.fxml");
    }

    private void handleEnterKey() {
        System.out.println("Enter key pressed! Executing code...");
        // Add your code to be executed here
    }
}