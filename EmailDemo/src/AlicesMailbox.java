/**
 * Checks Alice's mailbox every 5 seconds and responds if she received mail.
 * If she didn't receive mail, that is printed to the console. This class extends
 * MailBox which extends Thread to allow multithreaded operations.
 *
 * @author Michael Nasuti
 * @author Josiah Kowalski
 * @version 1.0
 */
public class AlicesMailbox extends Mailbox {
    public static final int check = 5000;

    public AlicesMailbox(){
        super("Alice", "Bob", check);
        Mail preloadedMail = new Mail("Bob", "Alice", getTimeStamp(), "Hello ");
        inbox.add(preloadedMail);
    }
}