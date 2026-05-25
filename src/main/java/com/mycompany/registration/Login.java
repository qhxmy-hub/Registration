/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

class Login {

    public String userName;
    public String Password;
    public String cellPhoneNumber;
    public String recipientNum;
    public String recipient;
    
    // USERNAME VALIDATION
    public boolean checkUserName(String username) {
    return username.contains("_") && username.length() >= 5;
     }

    // PASSWORD VALIDATION
    public boolean checkPasswordComplexity(String password) {

        if (password.length() < 8) return false;
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
            hasUpper = true;
            } else if (Character.isDigit(ch)) {
              hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
              hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // CELL PHONE NUMBER VALIDATION
    public boolean checkCellPhoneNumber(String phone) {
        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
        if (!Character.isDigit(phone.charAt(i))) {
        return false;
            }
        }
        return true;
    }

    public boolean loginUser(String username, String Password) {
    return username.equals(this.userName) && Password.equals(this.Password);
    }

    public String returnLoginStatus(boolean loginStatus) {
        if(loginStatus) {
        return "Login successful!";
        }else {
        return "Login failed!";
        }
    }
}