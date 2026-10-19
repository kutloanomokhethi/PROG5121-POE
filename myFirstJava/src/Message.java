import java.util.Random;

public class Message {
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;

    public Message() {
        Random rand = new Random();
        this.messageID = String.format("%010d", rand.nextInt(1000000000));
    }

    public boolean checkMessageID() { return messageID.length() <= 10; }
    public String getMessageID() { return messageID; }

    public boolean checkRecipientCell(String cell) {
        if (cell == null) return false;
        return cell.matches("\\+27\\d{9}");
    }

    public void setRecipientCell(String cell) { this.recipientCell = cell; }
    public String getRecipientCell() { return recipientCell; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String text) { this.messageText = text; }

    public String createMessageHash(int num, String text) {
        this.messageText = text;
        if (text == null || text.trim().isEmpty()) {
            this.messageHash = messageID.substring(0,2) + ":" + num + ":EMPTY";
            return messageHash;
        }
        String[] words = text.trim().split("\\s+");
        String first = words[0].toUpperCase();
        String last = words[words.length-1].toUpperCase();
        this.messageHash = (messageID.substring(0,2) + ":" + num + ":" + first + last).toUpperCase();
        return messageHash;
    }

    public String SentMessage(int option) {
        if (option == 1) return "Message successfully sent.";
        if (option == 2) return "Press 0 to delete message.";
        if (option == 3) return "Message successfully stored.";
        return "Invalid option.";
    }

    public String printMessages() {
        return "MessageID: " + messageID + "\nRecipient: " + recipientCell + "\nHash: " + messageHash + "\nText: " + messageText;
    }
}