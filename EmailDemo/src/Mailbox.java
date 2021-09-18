import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Checks the user's mailbox after waiting a certain amount of time and responds if they
 * received mail. If they didn't receive mail, that is printed to the console.
 * This class extends Thread to allow multithreaded operations.
 *
 * @author Michael Nasuti
 * @author Josiah Kowalski
 * @version 1.0
 */
public class Mailbox extends Thread{
    public final ArrayList<Mail> inbox;
    public final String user;
    public final String to;
    public final int check;

    /**
     * Constructor for objects of class Mailbox
     *
     * @param user This is the name of the person whose mailbox is being used.
     * @param to This is the name of the person who sent the mail to
     *           the user and who the user is sending mail to.
     * @param check This is the time the person will wait until checking their mail again.
     */
    public Mailbox(String user, String to, int check) {
        this.inbox = new ArrayList<>();
        this.user = user;
        this.to = to;
        this.check = check;
    }

    /**
     * Checks the mailbox, returns true if there is mail, and false if there is no mail.
     *
     * @return True if the eMail was received or false if the inbox is empty.
     */
    public boolean checkMailbox() {
        return inbox.size() != 0;
    }

    /** Gets a time stamp using the DateTimeFormatter class to format it and the LocalDateTime
     *  class to get the current time.
     *
     *
     *  @return The current time using the mm/dd/yyyy hh:mm:ss format.
     */
    public static String getTimeStamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        return dtf.format(time);
    }

    /**
     * Loops through each email in the inbox and prints it and a time
     * stamp to the console. The time stamp is used to show when the user
     * read the email.
     */
    public void printMail(){
        for (Mail mail: inbox) {
            // gives a timestamp for when mail was opened
            System.out.println(mail + "\n" + "Opened at "
                    + getTimeStamp() + "\n");
        }
    }

    /**
     * Writes a reply to recipient by adding a hello to the email the user received.
     *
     * @return The updated reply message or nothing if the user has no mail.
     */
    public Mail writeReply() {
        for (Mail mail : inbox) {
            String newBody = mail.body + "Hello "; //adds a Hello each time
            return new Mail(user, to, getTimeStamp(), newBody);
        }
        return null;
    }

    /**
     * Empties the inbox synchronously to avoid causing a server crash.
     */
    public synchronized void emptyInbox(){
        inbox.clear();
    }

   /**
    * Runs a client thread that constantly checks for emails that are to the user
    * and sends replies to the sender.
    */
    public void run() {
        try {
            while (System.nanoTime() < EmailServer.endTime) {
                // waits for the amount of time the user wants to before checking again
                Thread.sleep(check);
                boolean gotMail = checkMailbox();
                if (gotMail) {
                    printMail();
                    EmailServer.emails.add(writeReply());
                    emptyInbox();
                }
                else {
                    System.out.println(user + ": I didn't get mail. "
                            + getTimeStamp() + "\n");
                }
            }
        } catch (Exception ex) {
            System.err.println("Mail box not working");
        }
    }
}
