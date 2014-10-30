package massmailer;

import ezvcard.VCard;
import ezvcard.io.text.VCardReader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SMTPFormController {
    public TextField txtFrom;
    public TextField txtHost;
    public TextField txtPort;
    public TextField txtBenutzername;
    public TextField txtPasswort;
    public CheckBox chkTLS;
    public CheckBox chkSSL;
    public Button btnSave;

    public void initialize(){
        MailSettings s = MailSettings.getInstance();
        txtFrom.setText(s.getFrom());
        txtHost.setText(s.getHost());
        txtPort.setText(String.valueOf(s.getPort()));
        txtBenutzername.setText(s.getUsername());
        txtPasswort.setText(s.getPassword());
        chkSSL.setSelected(s.isSsl());
        chkTLS.setSelected(s.isTls());
    }

    public void btnSave_Click(ActionEvent actionEvent) {
        MailSettings s = MailSettings.getInstance();
        s.setFrom(txtFrom.getText());
        s.setHost(txtHost.getText());
        s.setPort(Integer.parseInt(txtPort.getText()));
        s.setUsername(txtBenutzername.getText());
        s.setPassword(txtPasswort.getText());
        s.setSsl(chkSSL.isSelected());
        s.setTls(chkTLS.isSelected());
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
}
