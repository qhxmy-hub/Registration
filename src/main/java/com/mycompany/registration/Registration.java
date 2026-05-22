package com.mycompany.registration;
import java.util.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

public class Registration {

    private static void sendMessages(Login user, Scanner obj, int sender) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void main(String[] args) {

        Scanner obj = new Scanner(System.in);

        Login user = new Login();

        int choice;

        do {
            System.out.println("\n MENU ");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = obj.nextInt();
            obj.nextLine();

            switch (choice) {

                case 1 -> register(user, obj);

                case 2 -> login(user, obj);
                
                case 3 -> System.out.println("Goodbye!");

                default -> System.out.println("Invalid Option.");
            }

        } while (choice != 3);

        obj.close();
    }

    // REGISTRATION PROCESS
    public void register(Login user, Scanner obj) { 

        System.out.println("\n REGISTER ");

        // USERNAME INPUT
        while (true) {
            System.out.print("Enter username: ");
            String usernameInput = obj.nextLine();

            if(user.checkUserName(usernameInput)) {
                System.out.println("Username successfully captured!");
                user.userName = usernameInput;
                break;
            }else{
                System.out.println("Username must contain a '_' ");
                System.out.println("Username characters should not be more than 5 characters in length.");
            }
        }

        // PASSWORD INPUT
        while (true) {
            System.out.print("Enter password: ");
            String passwordInput = obj.nextLine();

            if(user.checkPasswordComplexity(passwordInput)) {
                System.out.println("Password successfully captured!");
                user.Password = passwordInput;
                break;
            }else{
                System.out.println("Password is not correctly formatted.");
                System.out.println("Password to contain at least 8 characters.");
                System.out.println("Password must contain a Uppercase."); 
                System.out.println("Password should contain a number.");
                System.out.println("Password must contain a special character.");
            }
        }

        // CELL PHONE NUMBER INPUT
        while(true) {
            System.out.print("Enter phone (+27...): ");
            String phoneInput = obj.nextLine();

            if(user.checkCellPhoneNumber(phoneInput)) { 
                System.out.println("Cell Phone Number successfully added!");
                user.cellPhoneNumber = phoneInput;
                break;
            }else{
                System.out.println("Cell Phone Number incorrectly formatted.");
                System.out.println("Cell Phone Number does not include international code.");
            }
        }

        System.out.println("Registration Successful!\n");
    }

    // LOGIN PROCESS
    public static void login(Login user, Scanner obj) { 

        System.out.println("\n LOGIN ");

        System.out.print("Enter username: ");
        String username = obj.nextLine();

        System.out.print("Enter password: ");
        String password = obj.nextLine();

        boolean status = user.loginUser(username, password);
        System.out.println(user.returnLoginStatus(status));
    
    // PART 2 : THE USER MUST TYPE A MESSAGE 
        System.out.print("Welcome to QuickChat!!\n");
        
        System.out.print("How many messages would you like to send?\n");
        int sender = obj.nextInt();
        obj.nextLine();

        int choice;
        
        System.out.print("You are sending " + sender + " messages to the recipient\n");
        for (int i = 1; i <= sender; i++) {
            
        do {
            System.out.println("\nMENU");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.print("Choose option: ");

            choice = obj.nextInt();
            obj.nextLine();

            switch (choice) {

                case 1 -> sendMessages(user, obj, sender);

                case 2 -> System.out.println("Coming Soon!");

                case 3 -> System.out.println("Goodbye!");

                default -> System.out.println("Invalid Option. Please Try Again.");
            }

        } while (choice != 3);
       

        // MESSAGE ID VALIDATION
         Random random = new Random(); 
        
        long randomID = 1000000000L +
                        (long)(random.nextDouble() * 9000000000L);

        String messageID = String.valueOf(randomID);

        // MESSAGE NUMBER VALIDATION
        System.out.print("Your Message Limit is: " + sender + "\n");

        if (user.checkMessageID(messageID)) {

            System.out.println("MessageID successfully created.");
        }

        // RECIPIENT NUMBER VALIDATION
        while (true) {

            System.out.print("Enter recipients Phone number (+27...): ");
            String phoneInput = obj.nextLine();

            if (user.checkRecipientCell(phoneInput)) {

                System.out.println("Recipient Phone Number successfully added!");
                user.recipientNum = phoneInput;
                break;

            } else {

                System.out.println("Recipient Phone Number incorrectly formatted.");
                System.out.println("Recipient Phone Number must include a international code.");
                System.out.println("Recipient Phone Number is less than 10 digits.");
            }
        

        // MESSAGE TO THE RECIPIENT
        System.out.print("Please enter a message (250 Char): ");
        String messageInput = obj.nextLine();

        System.out.println(user.printMessages(messageInput));
        System.out.println("Your messageID is: " + messageID);
         
        //CALL MESSAGE VALIDATION
        int choices;

        do {

            System.out.println("\nMESSAGE MENU");
            System.out.println("1. Delete The Message");
            System.out.println("2. Send Another Message");
            System.out.println("3. Send The Message");
            System.out.print("Choose option: ");

            choices = obj.nextInt();
            obj.nextLine();

         switch (choices) {

            case 1 -> System.out.println("Message has been deleted Successfully");

            case 2 -> {
                   System.out.print("Enter another message: ");
                   messageInput = obj.nextLine();
                   }

            case 3 -> System.out.println("Message has been sent Successfully");
            
            default -> System.out.println("Invalid Option. Please Try Again.");
         }

        } while (choices != 3);
        
        System.out.println("Your messageID is: " + messageID);
        System.out.println("The Recipient Number is " + user.recipientNum);
    
       }
     }
    }
}
   

    
   