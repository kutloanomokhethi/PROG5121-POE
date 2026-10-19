import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    @Test public void testCheckMessageID_Valid() {
        Message msg = new Message();
        assertTrue(msg.checkMessageID());
        assertTrue(msg.getMessageID().length() <= 10);
    }
    @Test public void testCheckRecipientCell_Valid() {
        Message msg = new Message();
        assertTrue(msg.checkRecipientCell("+27729718441"));
    }
    @Test public void testCheckRecipientCell_Invalid() {
        Message msg = new Message();
        assertFalse(msg.checkRecipientCell("0857507589"));
    }
    @Test public void testCheckRecipientCell_InvalidNoPlus() {
        Message msg = new Message();
        assertFalse(msg.checkRecipientCell("27729718441"));
    }
    @Test public void testCreateMessageHash() {
        Message msg = new Message();
        String hash = msg.createMessageHash(0, "Hi there");
        assertTrue(hash.contains("HITHERE"));
    }
    @Test public void testSentMessage_Option1() {
        Message msg = new Message();
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }
    @Test public void testSentMessage_Option2() {
        Message msg = new Message();
        assertEquals("Press 0 to delete message.", msg.SentMessage(2));
    }
    @Test public void testSentMessage_Option3() {
        Message msg = new Message();
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}
