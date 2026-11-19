import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String cell = JOptionPane.showInputDialog("Enter cell number (e.g. +27838884567):");
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");
        JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + ", it is great to see you.");

        Message.loadFromJSON();

        boolean running = true;
        while (running) {
            String choice = JOptionPane.showInputDialog("Welcome to QuickChat\n1) Send Messages\n2) Show Recently Sent Messages\n3) Quit\n4) Stored Messages");
            if (choice == null) break;

            if (choice.equals("1")) {
                String numStr = JOptionPane.showInputDialog("How many messages do you want to send?");
                int num = Integer.parseInt(numStr);
                for (int i = 0; i < num; i++) {
                    String recipient = JOptionPane.showInputDialog("Enter recipient number:");
                    Message m = new Message();
                    if (!m.checkRecipientCell(recipient)) {
                        JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted.");
                        continue;
                    }
                    String text = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
                    if (text.length() > 250) {
                        JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
                        continue;
                    }
                    String id = m.createMessageID();
                    String hash = m.createMessageHash(id, Message.getNumMessagesSent(), text);
                    String option = JOptionPane.showInputDialog("What do you want to do?\n1) Send\n2) Disregard\n3) Store");
                    String flag = "";
                    if (option.equals("1")) {
                        flag = "Sent";
                        Message.incrementSent();
                        JOptionPane.showMessageDialog(null, "Message sent!\nID: " + id + "\nHash: " + hash);
                    } else if (option.equals("2")) {
                        flag = "Disregard";
                        JOptionPane.showMessageDialog(null, "Message disregarded.");
                    } else if (option.equals("3")) {
                        flag = "Stored";
                        JOptionPane.showMessageDialog(null, "Message stored.");
                    }
                    Message.addMessage(cell, recipient, text, hash, id, flag);
                }
            } else if (choice.equals("2")) {
                JOptionPane.showMessageDialog(null, "Coming soon - Recently Sent Messages");
            } else if (choice.equals("3")) {
                running = false;
                JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.getNumMessagesSent());
            } else if (choice.equals("4")) {
                String sub = JOptionPane.showInputDialog("Stored Messages\na) Display Sender and Recipient\nb) Display Longest Message\nc) Search by Message ID\nd) Search by Recipient\ne) Delete by Hash\nf) Display Report");
                if (sub == null) continue;
                if (sub.equalsIgnoreCase("a")) JOptionPane.showMessageDialog(null, Message.displaySenderAndRecipient());
                else if (sub.equalsIgnoreCase("b")) JOptionPane.showMessageDialog(null, "Longest Stored Message:\n" + Message.displayLongestMessage());
                else if (sub.equalsIgnoreCase("c")) {
                    String id = JOptionPane.showInputDialog("Enter Message ID:");
                    JOptionPane.showMessageDialog(null, Message.searchByMessageID(id));
                } else if (sub.equalsIgnoreCase("d")) {
                    String rec = JOptionPane.showInputDialog("Enter Recipient (e.g. +27838884567):");
                    JOptionPane.showMessageDialog(null, Message.searchByRecipient(rec));
                } else if (sub.equalsIgnoreCase("e")) {
                    String hash = JOptionPane.showInputDialog("Enter Message Hash:");
                    JOptionPane.showMessageDialog(null, Message.deleteByHash(hash));
                } else if (sub.equalsIgnoreCase("f")) JOptionPane.showMessageDialog(null, Message.displayReport());
            }
        }
    }
}