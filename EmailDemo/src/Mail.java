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

    @Override
    public String toString() {
        return "From: " + from + "\nTo: " + to +
                "\nDate: " + date + "\n" + body;
    }
}