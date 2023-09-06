package com.microservices.utils;

import java.util.regex.Pattern;

public class UserCreationUtils {
	
	private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
	
	private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=(?:[^0-9]*[0-9]){2})[a-zA-Z0-9]{8,12}$";

    private static final Pattern patternMail = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern patternPassword = Pattern.compile(PASSWORD_PATTERN);
    
    public static boolean validateMail(String email) {
        return patternMail.matcher(email).matches();
    }
    
    public static boolean validatePassword(String password) {
        return patternPassword.matcher(password).matches();
    }
}
