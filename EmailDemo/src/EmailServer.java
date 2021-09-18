import java.lang.Thread;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Acts as the email server by collecting emails and sending them to users.
 * Also, acts as the starting point for the user's email clients.
 *
 * @author Michael Nasuti
 * @author Josiah Kowalski
 * @version 1.0
 */
public class EmailServer extends Thread {
    public static ArrayList<Mail> emails;
    public static final long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);

    /**
     * Constructor for objects of class EmailServer
     */
    public EmailServer() {
        emails = new ArrayList<>();
    }

    public static void main(String[] args) {
        EmailServer server = new EmailServer();
        server.start();
    }

    /**
     * This method acts as a sort, if mail is meant for Alice, it is sent to her, and 
     * so on.
     *
     * @param alice One of the clients using the email.
     * @param bob One of the clients using the email.
     */
    public synchronized void sendMail(AlicesMailbox alice, BobsMailbox bob) {
        try {
            int count = 0;
            for (Mail mail : emails) {
                if (Objects.equals(mail.to, "Alice")) {
                    alice.inbox.add(mail);
                    count ++;
                } else if (Objects.equals(mail.to, "Bob")) {
                    bob.inbox.add(mail);
                    count ++;
                }
            }
            if (count != 0) {
                emails.clear();
            }

        }
        catch (Exception ex) {
            System.out.println("Server issue, will restart. (" + ex + ")");
        }
    }



    /**
     * Updates the email server, does not return anything.  
     * Clears the server after the mail is sent.
     *
     */
    public void run () {
        AlicesMailbox alice = new AlicesMailbox();
        alice.start();
        BobsMailbox bob = new BobsMailbox();
        bob.start();
        try {
            while (System.nanoTime() < EmailServer.endTime) {
                sendMail(alice, bob);
            }
        } catch (Exception ex) {
            System.err.println("Server.exe stopped working. (" + ex + ")");
        }
    }
}