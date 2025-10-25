import Models.Contact;

public class Main {
    public static void main(String[] args) {
        Contact contact = new Contact( "Salvatore", "Pirro", "0123456789", "");
        contact.setVorname("Salvatore");
        System.out.println(contact.getTelefonNumer());
    }
}