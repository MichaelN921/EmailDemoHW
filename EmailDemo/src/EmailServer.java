import java.lang.Thread;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Write a description of class EmailServer here.
 *
 * @author Michael Nasuti and Josiah Kowalski
 * @version 0.1
 */
public class EmailServer extends Thread {
    public static ArrayList<Mail> emails;
    public static long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);

    /**
     * Constructor for objects of class EmailServer
     */
    public EmailServer() {
        emails = new ArrayList<>();
        String timeStr = dateFormatter();
        Mail preloadedMail = new Mail("Bob", "Alice", timeStr, "preloaded mail");
        emails.add(preloadedMail);
    }

    public static void main(String[] args) {
        EmailServer server = new EmailServer();
        server.start();
    }

    public static String dateFormatter() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        return dtf.format(time);
    }

    public void sendMail(AlicesMailbox alice, BobsMailbox bob) {
        for (Mail mail : emails) {
            if (Objects.equals(mail.to, "Alice")) {
                alice.inbox.add(mail);
            } else if (Objects.equals(mail.to, "Bob")) {
                bob.inbox.add(mail);
            }
        }
        emails.clear();
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
                Thread.sleep(100);
                sendMail(alice, bob);
            }
        } catch (Exception ex) {
            System.err.println("Server.exe stopped working.");
        }
    }
}
