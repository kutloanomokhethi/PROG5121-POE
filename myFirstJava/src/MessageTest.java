import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    @Test public void testSentMessagesArrayPopulated() {
        Message.populateTestData();
        assertEquals("Did you get the cake?", Message.sentMessages[0]);
        assertEquals("It is dinner time !", Message.sentMessages[1]);
    }
    @Test public void testDisplayLongestMessage() {
        Message.populateTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.", Message.displayLongestMessage());
    }
    @Test public void testSearchByMessageID() {
        Message.populateTestData();
        assertTrue(Message.searchByMessageID("0838884567").contains("It is dinner time !"));
    }
    @Test public void testSearchByRecipient() {
        Message.populateTestData();
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    @Test public void testDeleteByHash() {
        Message.populateTestData();
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", Message.deleteByHash("01:1:WHERE:TIME"));
    }
    @Test public void testDisplayReport() {
        Message.populateTestData();
        String report = Message.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
    }
    @Test public void testCheckRecipientCell() {
        Message m = new Message();
        assertTrue(m.checkRecipientCell("+27838884567"));
        assertFalse(m.checkRecipientCell("123"));
    }
    @Test public void testCreateMessageHash() {
        Message m = new Message();
        assertEquals("12:0:HI:THERE", m.createMessageHash("12", 0, "Hi there"));
    }
}