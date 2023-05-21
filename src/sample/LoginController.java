package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    User user = null;

    @FXML
    private TextField login_button;

    @FXML
    private PasswordField password_button;

    @FXML
    private Button sign_in_button;

    @FXML
    void login(ActionEvent event) throws IOException {
        Alert alert;
        String sql = Queries.LOGIN;
        Connection connect = DatabaseConnection.getConnection();
        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_button.getText());
            ResultSet result = prepare.executeQuery();
            if (login_button.getText().isEmpty() || password_button.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Blank fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    if (password_button.getText().equals(result.getString("password"))) {
                        //for future if we need authentication
                        user = new User(result.getString("login"), result.getString("password"), result.getString("role"));
                        PrimaryController.currentUser = user;
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Login!");
                        alert.showAndWait();
                        sign_in_button.getScene().getWindow().hide();
                        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();
                        connect.close();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("No such user. Password or login incorrect");
                        alert.showAndWait();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No such user. Password or login incorrect");
                    alert.showAndWait();
                }
            }
            prepare.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
