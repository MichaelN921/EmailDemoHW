import java.util.*;

public class AlicesMailbox extends Thread {
    public final int check = 5000;
    public ArrayList<Mail> inbox;

    /**
     * Constructor for objects of class AlicesMailbox
     */
    public AlicesMailbox() {
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
            if (Objects.equals(mail.from, "Bob")) { //checks if from Bob, replies
                String newBody = mail.body + "Hello "; //adds a Hello each time
                Mail reply = new Mail("Alice", "Bob", getTimeStamp(), newBody);
                return reply;
            }
        }
        return null;
    }

    public String getTimeStamp() {
        return EmailServer.dateFormatter();
    }

    /**
     * This thread acts as our client Alice.
     *
     */
    public void run() {       
        try {
            while (System.nanoTime() < EmailServer.endTime) {
                Thread.sleep(check); //Alice checks every 5 seconds
                boolean gotMail = checkMailbox();
                if (gotMail) {
                    for (Mail mail: inbox) {
                        //gives a timestamp for when mail is recieved
                        System.out.println(mail + "\n" + "Received at " 
                            + getTimeStamp() + "\n");
                    }
                    EmailServer.emails.add(writeReply()); 
                    inbox.clear(); //clear previous received mail
                }
                else {
                    System.out.println("Alice: I didn't get mail. " 
                        + getTimeStamp() + "\n");
                }
            }
        } catch (Exception ex) {
            System.err.println("Mail box not working");
        }
    }
}