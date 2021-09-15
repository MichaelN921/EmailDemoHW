import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Write a description of class BobsMailbox here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BobsMailbox extends Thread {
    public final int check = 3000;
    public ArrayList<Mail> inbox;
    public ArrayList<Mail> outbox;
    public boolean gotMail;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    int count = 0;

    /**
     * Constructor for objects of class BobsMailbox
     */
    public BobsMailbox() {
        this.inbox = new ArrayList<Mail>();
        this.outbox = new ArrayList<Mail>();
    }

    public void checkMailbox() {
        if (inbox.size() == 0) {
            gotMail = false;
        }
        else {
            gotMail = true;
        }
    }

    /**
     * This thread acts as our client Bob.
     *
     */
    public void run() {
        try {
            while (System.nanoTime() < EmailServer.endTime) {
                Thread.sleep(check);
                checkMailbox();
                if (gotMail) {
                    count ++;
                    for (Mail mail: inbox) {
                        System.out.println(mail.email() + "\n");
                    }
                    inbox.clear();
                    //write reply to Alice
                    LocalDateTime time = LocalDateTime.now();
                    String timeStr = dtf.format(time);
                    String hello = "Hello ".repeat(count);
                    Mail newMail = new Mail("Bob", "Alice", timeStr, hello);
                    outbox.add(newMail);
                }
                else {
                    System.out.println("Bob: I didn't get mail. \n");
                }
            }
        } catch (Exception ex) {

        }
    }
}