/**
 * Checks Bob's mailbox every 3 seconds and responds if he received mail.
 * If he didn't receive mail, that is printed to the console. This class extends
 * MailBox which extends Thread to allow multithreaded operations.
 *
 * @author Michael Nasuti
 * @author Josiah Kowalski
 * @version 1.0
 */
public class BobsMailbox extends Mailbox {
    public static final int check = 3000;

    public BobsMailbox() {
        super("Bob", "Alice", check);
    }
}