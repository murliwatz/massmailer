package massmailer;

import java.io.Serializable;

/**
 * Created by Paul on 30.10.2014.
 */
public class MailSettings implements Serializable {
    private static MailSettings settings = null;

    private String from;
    private String host;
    private int port;
    private String username;
    private String password;
    private boolean ssl;
    private boolean tls;

    public MailSettings(){

    }

    public static MailSettings getInstance(){
        if(settings == null)
            settings = new MailSettings();
        return settings;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public void setTls(boolean tls) {
        this.tls = tls;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSsl() {
        return ssl;
    }

    public boolean isTls() {
        return tls;
    }

    public int getPort() {
        return port;
    }

    public String getFrom() {
        return from;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
