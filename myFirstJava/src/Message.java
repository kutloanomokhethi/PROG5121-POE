import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class Message {
    // ===== YOUR OLD CODE - PART 1 & 2 - KEPT =====
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private static int numMessagesSent = 0;

    public Message() {
        Random rand = new Random();
        this.messageID = String.format("%010d", rand.nextInt(1000000000));
    }

    public boolean checkMessageID() { return messageID.length() <= 10; }
    public String getMessageID() { return messageID; }

    public boolean checkRecipientCell(String cell) {
        if (cell == null) return false;
        return cell.matches("^\\+27\\d{9}$");
    }

    public void setRecipientCell(String cell) { this.recipientCell = cell; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return messageText; }
    public void setMessageText(String text) { this.messageText = text; }

    public String createMessageHash(String id, int numSent, String message) {
        String[] words = message.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase().replace("!", "").replace(".", "").replace(",", "");
        return id.substring(0, 2) + ":" + numSent + ":" + first + ":" + last;
    }

    public String createMessageID() {
        long num = (long) (Math.random() * 9000000000L) + 1000000000L;
        return String.valueOf(num);
    }

    public static int getNumMessagesSent() { return numMessagesSent; }
    public static void incrementSent() { numMessagesSent++; }

    // ===== PART 3 - ARRAYS - RUBRIC =====
    public static String[] sentMessages = new String[100];
    public static String[] disregardedMessages = new String[100];
    public static String[] storedMessages = new String[100];
    public static String[] messageHashes = new String[100];
    public static String[] messageIDs = new String[100];
    public static String[] allMessages = new String[100];
    public static String[] allRecipients = new String[100];
    public static String[] allSenders = new String[100];
    public static String[] storedRecipients = new String[100];
    public static String[] storedSenders = new String[100];
    public static int sentCount = 0;
    public static int disregardedCount = 0;
    public static int storedCount = 0;
    public static int totalCount = 0;

    public static void addMessage(String sender, String recipient, String text, String hash, String id, String flag) {
        allMessages[totalCount] = text;
        allRecipients[totalCount] = recipient;
        allSenders[totalCount] = sender;
        messageHashes[totalCount] = hash;
        messageIDs[totalCount] = id;
        totalCount++;
        if (flag.equals("Sent")) {
            sentMessages[sentCount] = text;
            sentCount++;
        } else if (flag.equals("Stored")) {
            storedMessages[storedCount] = text;
            storedRecipients[storedCount] = recipient;
            storedSenders[storedCount] = sender;
            storedCount++;
            saveToJSON();
        } else if (flag.equals("Disregard")) {
            disregardedMessages[disregardedCount] = text;
            disregardedCount++;
        }
    }

    // Test Data - 5 messages from POE
    public static void populateTestData() {
        sentCount = 0; storedCount = 0; disregardedCount = 0; totalCount = 0;
        addMessage("0838884567", "+27834557896", "Did you get the cake?", "00:0:DID:CAKE", "1234567890", "Sent");
        addMessage("0838884567", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "01:1:WHERE:TIME", "2345678901", "Stored");
        addMessage("0838884567", "+27834484567", "Yohoooo, I am at your gate.", "02:2:YOHOOOO:GATE", "3456789012", "Disregard");
        addMessage("0838884567", "0838884567", "It is dinner time !", "03:3:IT:DINNER", "0838884567", "Sent");
        addMessage("0838884567", "+27838884567", "Ok, I am leaving without you.", "04:4:OK:YOU", "4567890123", "Stored");
    }

    // a) Display sender + recipient of STORED messages
    public static String displaySenderAndRecipient() {
        String output = "";
        for (int i = 0; i < storedCount; i++) {
            output += "Sender: " + storedSenders[i] + ", Recipient: " + storedRecipients[i] + "\n";
        }
        if (output.equals("")) return "No stored messages.";
        return output;
    }

    // b) Display longest STORED message
    public static String displayLongestMessage() {
        String longest = "";
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].length() > longest.length()) longest = storedMessages[i];
        }
        return longest;
    }

    // c) Search by Message ID
    public static String searchByMessageID(String searchID) {
        for (int i = 0; i < totalCount; i++) {
            if (messageIDs[i].equals(searchID)) {
                return "Recipient: " + allRecipients[i] + "\nMessage: " + allMessages[i];
            }
        }
        return "Message ID not found.";
    }

    // d) Search by Recipient
    public static String searchByRecipient(String searchRecipient) {
        String result = "";
        for (int i = 0; i < totalCount; i++) {
            if (allRecipients[i].equals(searchRecipient)) result += allMessages[i] + "\n";
        }
        if (result.equals("")) return "No messages found.";
        return result;
    }

    // e) Delete by Hash
    public static String deleteByHash(String hash) {
        for (int i = 0; i < totalCount; i++) {
            if (messageHashes[i].equals(hash)) {
                String deletedMsg = allMessages[i];
                for (int k = 0; k < storedCount; k++) {
                    if (storedMessages[k].equals(deletedMsg)) {
                        for (int j = k; j < storedCount - 1; j++) {
                            storedMessages[j] = storedMessages[j + 1];
                            storedRecipients[j] = storedRecipients[j + 1];
                            storedSenders[j] = storedSenders[j + 1];
                        }
                        storedCount--;
                        break;
                    }
                }
                for (int j = i; j < totalCount - 1; j++) {
                    allMessages[j] = allMessages[j + 1];
                    allRecipients[j] = allRecipients[j + 1];
                    allSenders[j] = allSenders[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                    messageIDs[j] = messageIDs[j + 1];
                }
                totalCount--;
                saveToJSON();
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    // f) Display Report - Hash, Recipient, Message
    public static String displayReport() {
        String report = "";
        for (int i = 0; i < totalCount; i++) {
            report += "Message Hash: " + messageHashes[i] + "\n";
            report += "Recipient: " + allRecipients[i] + "\n";
            report += "Message: " + allMessages[i] + "\n\n";
        }
        if (report.equals("")) return "No messages.";
        return report;
    }

    // JSON - Save stored messages
    public static void saveToJSON() {
        try {
            FileWriter writer = new FileWriter("storedMessages.json");
            writer.write("[");
            for (int i = 0; i < storedCount; i++) {
                writer.write("{\"sender\":\"" + storedSenders[i] + "\",\"recipient\":\"" + storedRecipients[i] + "\",\"message\":\"" + storedMessages[i] + "\"}");
                if (i < storedCount - 1) writer.write(",");
            }
            writer.write("]");
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public static void loadFromJSON() {
        try {
            File file = new File("storedMessages.json");
            if (!file.exists()) return;
            Scanner scanner = new Scanner(file);
            String content = "";
            while (scanner.hasNextLine()) content += scanner.nextLine();
            scanner.close();
            if (content.equals("[]") || content.equals("")) return;
            String[] parts = content.split("\\{\"sender\":\"");
            for (int i = 1; i < parts.length; i++) {
                String sender = parts[i].split("\"")[0];
                String recipient = parts[i].split("\"recipient\":\"")[1].split("\"")[0];
                String msg = parts[i].split("\"message\":\"")[1].split("\"}")[0];
                storedMessages[storedCount] = msg;
                storedRecipients[storedCount] = recipient;
                storedSenders[storedCount] = sender;
                storedCount++;
            }
        } catch (Exception e) {
            System.out.println("Error loading: " + e.getMessage());
        }
    }
}