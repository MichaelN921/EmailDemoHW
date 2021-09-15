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
    int count = 0;

    /**
     * Constructor for objects of class BobsMailbox
     */
    public BobsMailbox() {
        this.inbox = new ArrayList<>();
    }

    public boolean checkMailbox() {
        return inbox.size() != 0;
    }

    /**
     * This thread acts as our client Bob.
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
                        System.out.println(mail + "\n" + "Received at " + timeStr + "\n");
                    }
                    inbox.clear();
                    //write reply to Alice
                    String timeStr = EmailServer.dateFormatter();
                    String hello = "Hello ".repeat(count);
                    Mail newMail = new Mail("Bob", "Alice", timeStr, hello);
                    EmailServer.emails.add(newMail);
                }
                else {
                    String timeStr = EmailServer.dateFormatter();
                    System.out.println("Bob: I didn't get mail. " + timeStr + "\n");
                }
            }
        } catch (Exception ex) {
            System.err.println("Mail box not working");
        }
    }
}