/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author qhxme
 */

public class MessageTest {

     // TEST Message Length Success
        public void testMessageLengthSuccess() {
        Message msg = new Message("0012345678", "+27718693002","Hi Mike, can you join us for dinner tonight?");
        String expected = "Message ready to send.";
        String actual = msg.printMessages("Hi Mike, can you join us for dinner tonight?");
        assertEquals(expected, actual);
        }

        // TEST Message Length Failure
        @Test
        public void testMessageLengthFailure() {
        Message msg = new Message("0012345678","+27718693002", "" );
        String longMessage = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                         "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "aaaaa";
       String expected = "Message exceeds 250 characters by 5 characters.";
       String actual = msg.printMessages(longMessage);
       assertEquals(expected, actual);
       }

       // TEST Recipient Number Success
       @Test
       public void testRecipientNumberSuccess() {
       Message msg = new Message("0012345678", "+27718693002", "Test message");
       boolean result = msg.checkRecipientCell("+27718693002");
       assertTrue(result);
       }

       // TEST Recipient Number Failure
       @Test
       public void testRecipientNumberFailure() {
       Message msg = new Message( "0012345678","08575975889","Test message");
       boolean result = msg.checkRecipientCell("08575975889");
       assertFalse(result);
       }

    
       // TEST 5: Message Hash Test Case 1
       @Test
       public void testMessageHash() {
       Message msg = new Message("0012345678", "+27718693002", "Hi Mike, can you join us for dinner tonight?" );
       String expected = "00:0:HITONIGHT?";
       String actual = msg.createMessageHash("0012345678", "Hi Mike, can you join us for dinner tonight?");
       assertEquals(expected, actual);
       }

       // TEST Message ID Valid
       @Test
       public void testMessageIDSuccess() {
       Message msg = new Message("1234567890","+27718693002", "Hello" );
       boolean result = msg.checkMessageID("1234567890");
       assertTrue(result);
       }

       // TEST Message ID Invalid
       @Test
       public void testMessageIDFailure() {
       Message msg = new Message("12345","+27718693002", "Hello");
       boolean result = msg.checkMessageID("12345");
       assertFalse(result);
        }

        // TEST Send Message
       @Test
        public void testSendMessage() {
        Message msg = new Message( "1234567890", "+27718693002","Hello");
        String expected = "Message successfully sent.";
        String actual = msg.handleMessageAction(1);
        assertEquals(expected, actual);
        }

        // TEST Disregard Message
        @Test
        public void testDisregardMessage() {
        Message msg = new Message( "1234567890","+27718693002","Hello");
        String expected = "Message disregarded.";
        String actual = msg.handleMessageAction(2);
        assertEquals(expected, actual);
        }

        // TEST Store Message
  
        @Test
        public void testStoreMessage() {
        Message msg = new Message( "1234567890","+27718693002", "Hello");
        String expected = "Message successfully stored.";
        String actual = msg.handleMessageAction(3);
        assertEquals(expected, actual);
    }

    // TEST MESSAGE ID VALID
    @Test
    public void testCheckMessageID_Valid() {
        Message msg = new Message("1234567890", "+27718693002","Hi Mike, can you join us for dinner tonight?" );
        boolean result = msg.checkMessageID("1234567890");
        assertTrue(result);
    }

    // TEST MESSAGE ID INVALID
    @Test
    public void testCheckMessageID_Invalid() {
        Message msg = new Message("12345","+27718693002","Hi Mike, can you join us for dinner tonight?");
        boolean result = msg.checkMessageID("12345");
        assertFalse(result);
    }

    // TEST RECIPIENT NUMBER VALID
    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg = new Message("1234567890", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        boolean result = msg.checkRecipientCell("+27718693002");
        assertTrue(result);
    }

    // TEST RECIPIENT NUMBER INVALID
    @Test
    public void testCheckRecipientCell_Invalid() {
        Message msg = new Message("1234567890","0718693002","Hi Mike, can you join us for dinner tonight?");
        boolean result = msg.checkRecipientCell("0718693002");
        assertFalse(result);
    }

    // TEST MESSAGE HASH CREATION
    @Test
    public void testCreateMessageHash() {
    Message msg = new Message( "1234567890","+27718693002","Hi Mike, can you join us for dinner tonight?" );
    String expectedHash = "12:1:HITONIGHT?";
    String actualHash = msg.createMessageHash("1234567890", "Hi Mike, can you join us for dinner tonight?");
    assertEquals(expectedHash, actualHash);
    }

    // TEST MESSAGE LENGTH VALID
    @Test
    public void testIsMessageValid_Valid() {
    Message msg = new Message("1234567890","+27718693002", "Hi Mike, can you join us for dinner tonight?");
    boolean result = msg.isMessageValid("Hi Mike, can you join us for dinner tonight?");
    assertTrue(result);
    }

    // TEST MESSAGE LENGTH INVALID
    @Test
    public void testIsMessageValid_Invalid() {
    Message msg = new Message( "1234567890","+27718693002","a".repeat(260));
    boolean result = msg.isMessageValid("a".repeat(260));
    assertFalse(result);
    }

    // TEST PRINT MESSAGE VALID
    @Test
    public void testPrintMessages_Valid() {
    Message msg = new Message("1234567890","+27718693002","Hi Mike, can you join us for dinner tonight?");
    String expected = "Message ready to send.";
    String actual = msg.printMessages("Hi Mike, can you join us for dinner tonight?");
    assertEquals(expected, actual);
    }

    // TEST PRINT MESSAGE INVALID
    @Test
    public void testPrintMessages_Invalid() {
    Message msg = new Message("1234567890", "+27718693002","a".repeat(260));
    String expected = "Message exceeds 250 characters by 10 characters.";
    String actual = msg.printMessages("a".repeat(260));
    assertEquals(expected, actual);
    }

    // TEST SEND MESSAGE ACTION
    @Test
    public void testHandleMessageAction_Send() {
    Message msg = new Message("1234567890", "+27718693002", "Hi Mike, can you join us for dinner tonight?");
    String result = msg.handleMessageAction(1);
    assertEquals("Message successfully sent.", result);
    assertEquals("Sent", msg.messageStatus);
    }

    // TEST DISREGARD MESSAGE ACTION
    @Test
    public void testHandleMessageAction_Disregard() {
    Message msg = new Message("1234567890","+27718693002","Hi Mike, can you join us for dinner tonight?");
    String result = msg.handleMessageAction(2);
    assertEquals("Message disregarded.", result);
    assertEquals("Disregarded", msg.messageStatus);
    }

    // TEST STORE MESSAGE ACTION
    @Test
    public void testHandleMessageAction_Store() {
    Message msg = new Message("1234567890", "+27718693002","Hi Mike, can you join us for dinner tonight?" );
    String result = msg.handleMessageAction(3);
    assertEquals("Message successfully stored.", result);
    assertEquals("Stored", msg.messageStatus);
    }

    // TEST INVALID MENU OPTION
    @Test
    public void testHandleMessageAction_Invalid() {
    Message msg = new Message("1234567890","+27718693002","Hi Mike, can you join us for dinner tonight?");
    String result = msg.handleMessageAction(5);
    assertEquals("Invalid option.", result);
    }

    // TEST TOTAL NUMBER SENT
    @Test
    public void testTotalMessagesSent() {
    Message.totalMessageNumber = 0;
    Message msg1 = new Message("1234567890","+27718693002","Hi Mike, can you join us for dinner tonight?");
    Message msg2 = new Message( "0987654321", "+27718693003", "Hi Keegan, did you receive the payment.");
    msg1.handleMessageAction(1);
    msg2.handleMessageAction(1);
    assertEquals(2, Message.totalMessageNumber);
    }
    
}


    