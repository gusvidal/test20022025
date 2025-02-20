package cl.gvidal.techtest.utils;

import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class PassValidator {
    public static Boolean validaPassword (String pass, String regularExp) {
        Pattern pattern = Pattern.compile(regularExp);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }
}
