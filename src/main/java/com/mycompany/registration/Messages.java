/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

import java.util.ArrayList;

/**
 *
 * @author qhxme
 */
public class Messages {
    public class Message {

    //DECLARATIONS
    public String messageID;
    public String recipientNum;
    public String messageInput;
    public String messageHash;
    public String messageStatus;
    public String sentMessage;
    public int messageNumber;

    //ARRAY TO STORE SAVED MESSAGES
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static int totalMessageNumber = 0 ;
    public static int messageCounter = 0 ;
    
    //CONSTRUCTORS
    public Message(String recipient, String messageText) {
        Message.messageCounter++;
        this.messageNumber = Message.messageCounter;
        this.recipientNum = recipient;
        this.messageHash = createmessageHash(messageText);
        this.messageInput = messageText;
    }
    
    // MESSAGE ID VALIDATION
    public boolean checkMessageID(String messageID) {

        if (messageID.length() != 10)
            return false;

        return true;
    }

    // CHECK RECIPIENT VALIDATION
    public boolean checkRecipientCell(String phone) {

        if (!phone.startsWith("+27"))
            return false;

        if (phone.length() != 12)
            return false;

        for (int i = 3; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {

                return false;
            }
        }

        return true;
    }

    // MESSAGE HASH VALIDATION
    public String createmessageHash(String hash) {
      String firstTwo = messageID.substring(0, 2);
      String[] words = messageInput.trim().split(" ");
      String firstWord = words[0].toUpperCase();
      String lastWord = words[words.length - 1].toUpperCase();
   return (firstTwo + ":"+ messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // SENT MESSAGES VALIDATION
     public String sentMessage(int choices) {
         
         switch (choices) {

            case 1:
                     messageStatus = "sent";
                     sentMessages.add(this);
                     messageNumber++;
                     return "Message successfully sent.";

            case 2 :
                   messageStatus = "Disregard";
                   return "Press 0 to delete the message.";

            case 3 : messageStatus = "Stored";
                   return "Message successfully stored.";
                   
            default: return "Invalid Option. Please Try Again.";
         }
     }

    
    // MESSAGE VALIDATION
    public String printMessages(String messages) {

        if (messages.length() > 250) {

            return "Message exceeds 250 characters.";

        } else {

            return "Message successfully sent.";
        }
    }
    
    // TOTAL MESSAGES VALIDATION
    public int returnTotalMessages(int messages) {

        return messages;
    }

}
}
