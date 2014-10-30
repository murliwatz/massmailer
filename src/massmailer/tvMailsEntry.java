package massmailer;

/**
 * Created by Paul on 30.10.2014.
 */
public class tvMailsEntry {
    private String mail;
    private String status;

    public tvMailsEntry(String mail, String status){
        this.mail = mail;
        this.status = status;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public String getStatus() {
        return status;
    }
}
