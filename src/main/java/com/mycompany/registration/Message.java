/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Message {

    // DECLARATIONS
    public String messageID;
    public String recipientNum;
    public String messageInput;
    public String messageHash;
    public String messageStatus;
    public int messageNumber;

    // ARRAY LIST
    public static ArrayList<Message> sentMessages = new ArrayList<>();
    public static ArrayList<Message> storedMessages = new ArrayList<>();
    public static ArrayList<Message> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    
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
        
        messageIDs.add(messageID);
        messageHashes.add(messageHash);
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
            
            disregardedMessages.add(this);
            
            return "Message disregarded.";
        }
		
        else if (option == 3) {

            messageStatus = "Stored";

            storedMessages.add(this);
            
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
    
    // PART 3 METHODS AND VALIDATIONS
    
    //LONGEST MESSAGE VALIDATION
    public static String getLongestStoredMessage() {

    String longest = "Where are you? You are late! I have asked you to be on time.";

    for (Message msg : storedMessages) {

        if (msg.messageInput.length() > longest.length()) {

            longest = msg.messageInput;
        }
    }

    return longest;
    }
    
    //SEARCH MESSAGE ID VALIDATION
    public static Message searchMessageID(String id) {

    for (Message msg : storedMessages) {

        if (msg.messageID.equals(id)) {

            return msg;
        }
    }

    return null;
    }
    
    // SEARCH RECIPIENT MESSAGES VALIDATION
    public static ArrayList<Message> searchRecipientMessages(String recipient) {

    ArrayList<Message> results = new ArrayList<>();

    for (Message msg : storedMessages) {

        if (msg.recipientNum.equals(recipient)) {

            results.add(msg);
        }
    }

    return results;
    }
    
    // DELETE MESSAGE HASH VALIDATION
    public static boolean deleteMessage(String hash) {

    for (int i = 0; i < storedMessages.size(); i++) {

        if (storedMessages.get(i).messageHash.equals(hash)) {

            storedMessages.remove(i);

            return true;
        }
    }

    return false;
    }
    
    // PART 3 LOADING THE STORED FILE
    public static void loadStoredMessages()
    {
    storedMessages.clear();
    
    try
    {
        BufferedReader reader = new BufferedReader( new FileReader("messages.json"));
        String line;

        while((line = reader.readLine()) != null)
        {
            String messageID = "";
            String recipient = "";
            String message = "";
            String hash = "";
            String status = "";
            
            String[] parts = line.split(",");

            for (String part : parts)
            {
                if (part.contains("MessageID"))
                {
                    messageID = part.split(":")[1].replace("\"", "").replace("{", "").trim();
                }

                else if (part.contains("Recipient"))
                {
                    recipient = part.split(":")[1].replace("\"", "").trim();
                }

                else if (part.contains("\"Message\""))
                {
                    message = part.split(":")[1].replace("\"", "").trim();
                }

                else if (part.contains("MessageHash"))
                {
                    hash = part.split(":")[1].replace("\"", "").trim();
                }

                else if (part.contains("Status"))
                {
                    status = part.split(":")[1].replace("\"", "").replace("}", "").trim();
                }
            }

            Message msg = new Message(messageID, recipient, message);

            msg.messageHash = hash;
            msg.messageStatus = status;

            storedMessages.add(msg);
        }

        reader.close();

        System.out.println("Stored messages loaded successfully.");
        }
        catch(Exception e)
    {
        System.out.println("Error loading messages.");
    }
 }
}
