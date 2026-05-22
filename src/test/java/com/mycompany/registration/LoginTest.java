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
public class LoginTest {
    
    public LoginTest() {
    }
    
    //Object of the login class
    Login login = new Login(); 
    
    //Tests of checkUserName Methods of the Login class.
    
    @Test
    public void testCheckUserName_Valid() {
      assertEquals(true,login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
      assertEquals(false,login.checkUserName("kyle!!!!!!!"));
    }
    
   
    //Tests of checkPasswordComplexity Methods of the Login class.
    
    @Test
    public void testCheckPasswordComplexity_Valid() { 
      assertEquals(true,login.checkPasswordComplexity("Ch&&sec@ke99!")); 
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() { 
      assertEquals(false,login.checkPasswordComplexity("password")); 
    }
    
    //Tests of checkCellPhoneNumber Methods of the Login class.
    
    @Test
    public void testCheckCellPhoneNumber_Valid() {  
     assertTrue(login.checkCellPhoneNumber("+27659874564"));
    }
    
    @Test
    public void testCheckCellPhoneNumber_Invalid() {
      assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    //Tests of Login Methods of the Login class.
    
    @Test
    public void testLoginSuccessful() {
    login.loginUser("kyl_1", "pass@123");
    assertFalse(login.loginUser("kyl_1", "pass@123"));
}
    
     @Test
    public void testLoginFailed() {
    login.loginUser("kyle!!!!!!!", "Ch&&sec@ke99!");
    assertFalse(login.loginUser("kyle!!!!!!!", "Ch&&sec@ke99!"));
}
    
    //Tests of Username Methods of the Login class.
    
    //Tests of Password Methods of the Login class.
    
    @Test
    public void testPasswordMeetsComplexity() { 
      assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!")); 
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexity() { 
      assertFalse(login.checkPasswordComplexity("password")); 
    }
    
    //Tests of CellPhone Methods of the Login class.
    
    @Test
    public void testCellPhoneCorrectlyFormatted() {  
     assertTrue(login.checkCellPhoneNumber("+27659874564"));
    }
    
    @Test
    public void testCellPhoneIncorrectlyFormatted() {  
     assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    
}
