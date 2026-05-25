/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Message {

    // DECLARATIONS
    public String messageID;
    public String recipientNum;
    public String messageInput;
    public String messageHash;
    public String messageStatus;
    public int messageNumber;

    // ARRAYLIST
    public static ArrayList<Message> sentMessages = new ArrayList<>();

    public static int totalMessageNumber = 0;
    public static int messageCounter = 0;

    // CONSTRUCTORS
    public Message(String messageID, String recipientNum, String messageInput) {

        this.messageID = messageID;
        this.recipientNum = recipientNum;
        this.messageInput = messageInput;

        messageCounter++;
		
        this.messageNumber = messageCounter;
        this.messageHash = createMessageHash(messageID, messageInput);
    }

    // MESSAGE ID VALIDATION
    public boolean checkMessageID(String messageID) {
	
        if (messageID.length() != 10) {
        return false;
        }

        for (int i = 0; i < messageID.length(); i++) {

        if (!Character.isDigit(messageID.charAt(i))) {
        return false;
            }
        }

        return true;
    }

    // RECIPIENT NUMBER VALIDATION
    public boolean checkRecipientCell(String phone) {

        if (phone.startsWith("+27") && phone.length() == 12) {

        for (int i = 3; i < phone.length(); i++) {

        if (!Character.isDigit(phone.charAt(i))) {
        return false;
                }
            }
        return true;

        }else {
        return false;
        }
    }

    // MESSAGE HASH VALIDATION
        public String createMessageHash(String messageID, String messageText) {

        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String hash = firstTwo + ":" + messageNumber + ":"+ firstWord + lastWord;
        return hash.toUpperCase();
    }

    // MESSAGE LENGTH VALIDATION
    public boolean isMessageValid(String message) {

        if (message.length() <= 250) {
        return true;
        } else {
        return false;
        }
    }

    // PRINT MESSAGES VALIDATION
    public String printMessages(String message) {

        if (isMessageValid(message)) {
        return "Message ready to send.";
        }else {

        int extraCharacters = message.length() - 250;

        return "Message exceeds 250 characters by "+ extraCharacters + " characters.";
        }
    }

    // ACTION MENU VALIDATION
    public String handleMessageAction(int option) {

        // Send message
        if (option == 1) {

            messageStatus = "Sent";

            sentMessages.add(this);

            totalMessageNumber++;

            return "Message successfully sent.";
        }

        else if (option == 2) {

            messageStatus = "Disregarded";

            return "Message disregarded.";
        }
		
        else if (option == 3) {

            messageStatus = "Stored";

            return "Message successfully stored.";
        }
		
        else {

        return "Invalid option.";
        }
    }

   // JSON FILE OF STORED MESSAGES
   
    public void saveMessageToJSON() {

        try {

            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{");
            writer.write("\"MessageID\" : \"" + messageID + "\",");
            writer.write("\"Recipient\" : \"" + recipientNum + "\",");
            writer.write("\"Message\" : \"" + messageInput + "\",");
            writer.write("\"MessageHash\" : \"" + messageHash + "\",");
            writer.write("\"Status\" : \"" + messageStatus + "\"");
            
            writer.write("}\n");

            writer.close();

            System.out.println("Message stored successfully.");

        } catch (IOException e) {

            System.out.println("Error saving message.");
        }
    }
}
