package massmailer;

import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.io.text.VCardReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    public Label lblEML;

    @FXML public TableColumn tcMail;
    @FXML public TableColumn tcStatus;
    @FXML public TableView<tvMailsEntry> tvMails;

    ObservableList<tvMailsEntry> email_lst = FXCollections.observableArrayList();

    private String vcfFile = null;
    private String emlFile = null;

    public void initialize(){
        tcMail.setCellValueFactory(new PropertyValueFactory<tvMailsEntry,String>("mail"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<tvMailsEntry,String>("status"));
    }

    public void SMTP_Click(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SMTPForm.fxml"));
        Stage stage = new Stage();
        stage.setTitle("SMTP-Einstellungen - Paul's MassMailer");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void VCF_Click(ActionEvent actionEvent) throws IOException {
        email_lst.clear();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("VCF-Datei","*.vcf"));
        File file = fc.showOpenDialog(null);
        VCardReader reader = new VCardReader(file);
        VCard vcard = null;
        while ((vcard = reader.readNext()) != null){
            email_lst.add(new tvMailsEntry(vcard.getEmails().get(0).getValue(), "nicht gesendet!"));
        }
        reader.close();
        tvMails.setItems(email_lst);
    }

    public void EML_Click(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("EML-Datei","*.eml"));
        File file = fc.showOpenDialog(null);
        emlFile = file.getAbsolutePath();
        lblEML.setText(file.getName());
    }

    public void btnClipboard_Click(ActionEvent actionEvent) {
        email_lst.clear();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if(clipboard.hasString()) {
            String[] emails = clipboard.getString().split("\n");
            for (int i = 0; i < emails.length; i++)
                if(emails[i].contains("@"))
                    email_lst.add(new tvMailsEntry(emails[i].trim(), "nicht gesendet!"));
            tvMails.setItems(email_lst);
        }
    }
}
