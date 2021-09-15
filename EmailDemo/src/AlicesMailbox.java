import java.lang.Thread;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AlicesMailbox extends Thread {
    public final int check = 5000;
    public ArrayList<Mail> inbox;
    public ArrayList<Mail> outbox;
    public boolean gotMail;
    // this variable (dtf) is used to set the time format
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    int count = 0;

    /**
     * Constructor for objects of class AlicesMailbox
     */
    public AlicesMailbox() {
        this.inbox = new ArrayList<Mail>();
        this.outbox = new ArrayList<Mail>();
        // these two lines are used to get the current time and format it
        LocalDateTime time = LocalDateTime.now();
        String timeStr = dtf.format(time);
        Mail preloadedMail = new Mail("Bob", "Alice", timeStr, "preloaded mail");
        inbox.add(preloadedMail);
    }

    
    public boolean checkMailbox() {
        if (inbox.size() == 0) {
            gotMail = false;
        }
        else {
            gotMail = true;
        }
        return gotMail;
    }

    /**
     * This thread acts as our client Alice.
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
                    //write reply to Bob
                    LocalDateTime time = LocalDateTime.now();
                    String timeStr = dtf.format(time);
                    String hello = "Hello ".repeat(count);
                    Mail newMail = new Mail("Alice", "Bob", timeStr, hello);
                    outbox.add(newMail);
                }
                else {
                    System.out.println("Alice: I didn't get mail. \n");
                }
            }
        } catch (Exception ex) {
            
        }
    }
}
