/**
 * Stores an email with 4 variables, from, to, date, and body.
 *
 * @author Michael Nasuti
 * @author Josiah Kowalski
 * @version 1.0
 */
public class Mail {
    public final String from;
    public final String to;
    public final String date;
    public final String body;

    /**
     * Constructor for objects of class Mail
     */
    public Mail(String from, String to, String date, String body) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.body = body;
    }

    /**
     * toString method that returns a formatted string with each of the variables.
     *
     * @return A formatted string which can be printed to the console
     */
    public String toString() {
        return "From: " + this.from + "\nTo: " + this.to +
                "\nDate: " + this.date + "\n" + this.body;
    }
}