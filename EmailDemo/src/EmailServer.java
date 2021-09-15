import java.lang.Thread;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Write a description of class EmailServer here.
 *
 * @author Michael Nasuti and Josiah Kowalski
 * @version 0.1
 */
public class EmailServer extends Thread {
    ArrayList<Mail> emails;
    public static long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);

    /**
     * Constructor for objects of class EmailServer
     */
    public EmailServer() {
        this.emails = new ArrayList<Mail>();
    }

    public static void main(String[] args) {
        EmailServer server = new EmailServer();
        server.start();
    }

    public void addMail(Mail email) {
        emails.add(email);
    }

    public void deleteMail() {
        int email = emails.size() - 1;
        emails.remove(email);
    }

    /**
     * Updates the email server, does not return anything.
     *
     */
    public void run () {
        AlicesMailbox alice = new AlicesMailbox();
        alice.start();
        BobsMailbox bob = new BobsMailbox();
        bob.start();

        try {
            while (System.nanoTime() < endTime) {
                Thread.sleep(1000);
                ArrayList<Mail> alicesOutbox = alice.outbox;
                ArrayList<Mail> bobsOutbox = bob.outbox;
                if (alicesOutbox != null){
                    bob.inbox.addAll(alicesOutbox);
                    alicesOutbox.clear();
                }
                if (bobsOutbox != null) {
                    alice.inbox.addAll(bobsOutbox);
                    bobsOutbox.clear();
                }
            }
        } catch (Exception ex) {
            System.err.println("Server.exe stopped working.");
        }
    }
}
