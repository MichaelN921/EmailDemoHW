import java.lang.Thread;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AlicesMailbox extends Thread {
    public final int check = 5000;
    public ArrayList<Mail> inbox;
    public ArrayList<Mail> outbox;
    int count = 0;

    /**
     * Constructor for objects of class AlicesMailbox
     */
    public AlicesMailbox() {
        this.inbox = new ArrayList<>();
        this.outbox = new ArrayList<>();
//        String timeStr = EmailServer.dateFormatter();
//        Mail preloadedMail = new Mail("Bob", "Alice", timeStr, "preloaded mail");
//        inbox.add(preloadedMail);
    }

    
    public boolean checkMailbox() {
        return inbox.size() != 0;
    }

    /**
     * This thread acts as our client Alice.
     *
     */
    public void run() {       
        try {
            while (System.nanoTime() < EmailServer.endTime) {
                Thread.sleep(check);
                boolean gotMail = checkMailbox();
                if (gotMail) {
                    count ++;
                    for (Mail mail: inbox) {
                        String timeStr = EmailServer.dateFormatter();
                        System.out.println(mail.email() + "\n" + "Received at " + timeStr + "\n");
                    }
                    inbox.clear();
                    //write reply to Bob
                    String timeStr = EmailServer.dateFormatter();
                    String hello = "Hello ".repeat(count);
                    Mail newMail = new Mail("Alice", "Bob", timeStr, hello);
                    EmailServer.emails.add(newMail);
                }
                else {
                    String timeStr = EmailServer.dateFormatter();
                    System.out.println("Alice: I didn't get mail. " + timeStr + "\n");
                }
            }
        } catch (Exception ex) {
            System.err.println("Mail box not working");
        }
    }
}
