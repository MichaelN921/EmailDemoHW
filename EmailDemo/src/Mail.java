import java.lang.Thread;
import java.util.*;
public class Mail {
    public String from;
    public String to;
    public String date;
    public String body;

    /**
     * Constructor for objects of class Mail
     */
    public Mail(String from, String to, String date, String body) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.body = body;
    }
    
    public String setFrom(String name) {
        return this.from;
    }
    
    public String setTo(String name) {
        return this.to;
    }
    
    public String setDate(String time) {
        return this.date;
    }
    
    public String setBody(String words) {
        return this.body;
    }
    
    @Override
    public String toString() {
        String mail = String.format("From: " + this.from + "\nTo: " + this.to +
        "\nDate: " + this.date + "\n" + this.body);
        return mail;
    }
    
    /**
     * @return    the email
     */
    public String email() {
        return toString();
    }
}
