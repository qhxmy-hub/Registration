package com.mycompany.registration;
import java.util.*;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

 public class Registration {

    public static void main(String[] args) {

           Scanner input = new Scanner(System.in);
           Login user = new Login();

           int option = 0;

    // MAIN MENU
        while (option != 3) {

           System.out.println("\n MAIN MENU ");
           System.out.println("1. Register");
           System.out.println("2. Login");
           System.out.println("3. Exit");
           System.out.print("Choose option: ");

           option = input.nextInt();
           input.nextLine();

    // REGISTER
            
        switch (option) {
                
        case 1:
            {
            System.out.println("\n REGISTER");
                        
        while (true) {
            System.out.print("Enter username: ");
            String username = input.nextLine();

        if (user.checkUserName(username)) {

            System.out.println("Username successfully captured.");
            user.userName = username;
            break;

            } else {

            System.out.println("Username is not correctly formatted.");
            System.out.println("Username must contain a '_' ");
            System.out.println("Username characters should not be more than 5 characters in length.");
             }
            }
                        
    // PASSWORD VALIDATION      
        while (true) {

            System.out.print("Enter password: ");
            String password = input.nextLine();

        if (user.checkPasswordComplexity(password)) {

            System.out.println("Password successfully captured!");
            user.Password = password;
            break;

            } else {
            System.out.println("Password is not correctly formatted.");
            System.out.println("Password to contain at least 8 characters.");
            System.out.println("Password must contain a Uppercase."); 
            System.out.println("Password should contain a number.");
            System.out.println("Password must contain a special character.");
            }
        } 
                        
    // PHONE NUMBER VALIDATION
        while (true) {
                            
            System.out.print("Enter cellphone number: ");
            String phoneNumber = input.nextLine();
                        
        if (user.checkCellPhoneNumber(phoneNumber)) {
                            
            System.out.println("Cellphone number successfully captured!");
            user.cellPhoneNumber = phoneNumber;
            break;
                        
            } else {
            System.out.println("Cellphone number incorrectly formatted.");
            System.out.println("Cell Phone Number does not include international code.");
            } 
          }
            System.out.println("Registration successful!");
            break;
        }
                    
        case 2:
            {
            System.out.println("\n LOGIN");
            System.out.print("Enter username: ");
            String username = input.nextLine();
                        
            System.out.print("Enter password: ");
            String password = input.nextLine();
                        
            boolean loginStatus = user.loginUser(username, password);
            System.out.println(user.returnLoginStatus(loginStatus));
                        
    //PART 2 QUICKCHAT APP 
       
        if (loginStatus == true) {
            
            Message.loadStoredMessages();
               
            System.out.println("\nWELCOME TO QUICKCHAT!");           
            System.out.print("How many messages do you want to send? ");
            int numberOfMessages = input.nextInt();
            input.nextLine();
            
            int choice = 0;
            
            while (choice != 3) {

           System.out.println("\n QUICKCHAT MENU");
           System.out.println("1. Send Messages");
           System.out.println("2. Show recently sent messages");
           System.out.println("3. Quit");
           System.out.print("Choose option: ");

           choice = input.nextInt();
           input.nextLine();

           switch (choice) {

        case 1 -> {

            Random random = new Random();

    // MESSAGE COUNTER
            for (int i = 0; i < numberOfMessages; i++) {

            System.out.println("\nMessage " + (i + 1));

    // MESSAGE ID
             long randomID = 1000000000L +(long)(random.nextDouble() * 9000000000L);
             String messageID = String.valueOf(randomID);

    // RECIPIENT NUMBER
            String recipient;

            while (true) {

            System.out.print("Enter recipient number: ");
            recipient = input.nextLine();

            if (recipient.startsWith("+27")&& recipient.length() == 12) {
            System.out.println("Recipient number captured.");
            break;
            } else {
            System.out.println("Invalid recipient number.");
            System.out.println("Recipient number does not include international code.");
             }
            }

    // MESSAGE
           System.out.print("Enter your message: ");
           String messageText = input.nextLine();

    // MESSAGE OBJECT
            Message msg = new Message(messageID, recipient, messageText);

    // VALIDATION
            System.out.println(msg.printMessages(messageText));

            if (!msg.isMessageValid(messageText)) {
            continue;
        }

    // DISPLAY
            System.out.println("Message ID: " + messageID);
            System.out.println("Message Hash: "+ msg.messageHash);

    // ACTION MENU
        int action = 0;

        while (action < 1 || action > 3) {

            System.out.println("\n ACTION MENU");
            System.out.println("1. Send Message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message");
            System.out.print("Choose option: ");

            action = input.nextInt();
            input.nextLine();

        if (action < 1 || action > 3) {

            System.out.println("Invalid option. Try again.");
            } else {System.out.println(msg.handleMessageAction(action));

        if (action == 3){
                        msg.saveMessageToJSON();
                        }
                    }
                }
            }

            System.out.println("\nAll messages processed.");

            System.out.println("Total messages sent: "+ Message.totalMessageNumber);
        }

        case 2 -> System.out.println("Coming Soon!");

        case 3 ->{input.close();
                   System.exit(0);
                  }
        default -> System.out.println("Invalid Option. Please Try Again.");
           }
           
        //PART 3 STORING DATA    
                 
        // STORED MESSAGES MENU
        int stored = 0;

        while (stored != 7) {

            System.out.println("\n STORED MESSAGES MENU");
            System.out.println("1. Display sender and recipient stored messages.");
            System.out.println("2. Display the longest stored message.");
            System.out.println("3. Search for messageID and display it's message and recipient.");
            System.out.println("4. Search for messages stored for a certain recipient.");
            System.out.println("5. Delete a message by using message hash.");
            System.out.println("6. Display a report that shows all stored stored messages and details.");
            System.out.println("7. Exit to send some more messages.");
            System.out.print("Choose option: ");
            
            stored = input.nextInt();
            input.nextLine();

        switch (stored) {

    // DISPLAY THE SENDER AND RECIPIENT'S STORED MESSAGES
    case 1:

        if (Message.storedMessages.isEmpty()) {
        System.out.println("No messages found.");
        }
        else {
		for (Message msg : Message.storedMessages) {
                System.out.println("\nRecipient: " + msg.recipientNum);
                System.out.println("Message: " + msg.messageInput);
                System.out.println("Message Hash: " + msg.messageHash);
                System.out.println("Message ID: " + msg.messageID);
            }
        }
        break;

    // DISPLAY THE LONGEST MESSAGE
    case 2:

        if (Message.storedMessages.isEmpty()) {
        System.out.println("No messages found.");
        }
        else { 
		Message longest = Message.storedMessages.get(0);
                for (Message msg : Message.storedMessages) {
                if (msg.messageInput.length() > longest.messageInput.length()) {
                    longest = msg;
                }
            }
            System.out.println("Longest Message:");
            System.out.println(longest.messageInput);
        }
        break;

    // SEARCH MESSAGE ID AND SHOW CORRECT MESSAGE AND RECIPIENT
    case 3:

        System.out.print("Enter Message ID: ");
        String searchID = input.nextLine();
		
        boolean foundID = false;
		
        for (Message msg : Message.storedMessages) {
		
            if (msg.messageID.equals(searchID)) {
                System.out.println("Recipient: " + msg.recipientNum);
                System.out.println("Message: "+ msg.messageInput);
                foundID = true;
                break;
            }
        }

        if (!foundID) {
            System.out.println("Message ID not found.");
        }
        break;

    // SEARCH STORED MESSAGES BY THE RECIPIENT
    case 4:

        System.out.print("Enter Recipient Number: ");
        String searchRecipient = input.nextLine();
		
        boolean foundRecipient = false;
		
        for (Message msg : Message.storedMessages) {
		
            if (msg.recipientNum.equals(searchRecipient)) {
                System.out.println("Message ID: " + msg.messageID);
                System.out.println("Message: " + msg.messageInput);
                foundRecipient = true;
            }
        }

        if (!foundRecipient) {
            System.out.println("No messages found.");
        }
        break;

    // DELETE MESSAGE USING THE MESSAGE HASH
    case 5:

        System.out.print("Enter Message Hash: ");
        String searchHash = input.nextLine();
        boolean deleted = false;

        for (int i = 0; i < Message.storedMessages.size(); i++) {

            if (Message.storedMessages.get(i).messageHash.equals(searchHash)) {
                Message.storedMessages.remove(i);
                System.out.println("Message deleted successfully.");
                deleted = true;
                break;
            }
        }

        if (!deleted) {
            System.out.println("Message hash not found.");
        }
        break;

    // FULL REPORT OF ALL STORED MESSAGES
    case 6:

        if (Message.storedMessages.isEmpty()) {
            System.out.println("No messages found.");
        }
        else {
            for (Message msg : Message.storedMessages) {
			
                System.out.println("                   ");
                System.out.println("Message ID: " + msg.messageID);
                System.out.println("Recipient: " + msg.recipientNum);
                System.out.println("Message: " + msg.messageInput);
                System.out.println("Message Hash: " + msg.messageHash);
                System.out.println("Status: " + msg.messageStatus);
            }
        }
        break;

    // EXIT STORED MENU
    case 7:
        System.out.println("Returning to QuickChat Menu.");
        break;

    default: System.out.println("Invalid option.");
    
           }
          }
         }
        }
       }
      }
     }      
    }
   }
   