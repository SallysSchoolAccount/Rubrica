package Models;

public class Contact {
    private int id;
    private String vorname;
    private String nachname;
    private String telefonNumer;
    private String email;

    public Contact(String vorname, String nachname, String telefonNumer, String email) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.telefonNumer = telefonNumer;
        this.email = email;
    }
    public String getVorname() {
        return vorname;
    }
    public String getNachname() {
        return nachname;
    }
    public String getTelefonNumer() {
        return telefonNumer;
    }
    public String getEmail() {
        return email;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public void setTelefonNumer(String telefonNumer) {
        this.telefonNumer = telefonNumer;
    }
    public void setEmail(String email) {}
}
