import java.util.*;

/**
 * Write a description of class BobsMailbox here.
 *
 */
public class BobsMailbox extends Thread {
    public final int check = 3000;
    public ArrayList<Mail> inbox;

    /**
     * Constructor for objects of class BobsMailbox
     */
    public BobsMailbox() {
        this.inbox = new ArrayList<>();
    }

    /**
     * Checks the mailbox, returns true if mail, and false if no mail.
     * 
     * @return true  eMail recieved
     * @return false  inbox empty
     */
    public boolean checkMailbox() {
        return inbox.size() != 0;
    }

    /**
     * This method writes a reply email to be sent to the other client.
     * 
     * @return reply  Updated reply message
     * @return null  eMail sent to the wrong person
     */
    public Mail writeReply() {
        for (Mail mail : inbox) {
            if (Objects.equals(mail.from, "Alice")) { //checks if from Alice, replies
                String newBody = mail.body + "Hello "; //adds a Hello each reply
                Mail reply = new Mail("Bob", "Alice", getTimeStamp(), newBody);
                return reply;
            }
        }
        return null;
    }

    public String getTimeStamp() {
        return EmailServer.dateFormatter();
    }

    /**
     * This thread acts as our client Bob.
     *
     */
    public void run() {
        try {
            while (System.nanoTime() < EmailServer.endTime) {
                Thread.sleep(check); //Bob checks his mail every 3 seconds
                boolean gotMail = checkMailbox();
                if (gotMail) {
                    for (Mail mail: inbox) {
                        //gives a timestamp for when mail is recieved
                        System.out.println(mail + "\n" + "Received at " 
                            + getTimeStamp() + "\n");
                    }
                    //write reply to Alice
                    EmailServer.emails.add(writeReply());
                    inbox.clear(); //clears received mail
                }
                else {
                    System.out.println("Bob: I didn't get mail. " 
                        + getTimeStamp() + "\n");
                }
            }
        } catch (Exception ex) {
            System.err.println("Mail box not working");
        }
    }
}