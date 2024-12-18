package cl.gvidal.neoris.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PassValidatorTest {
    String passOk = "GVidal980@";
    String passFail = "Pass123";
    String regex = "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}$";

    @Test
    public void validaPassOkTest(){
        assertEquals(PassValidator.validaPassword(passOk, regex),true);
    }

    @Test
    public void validaPassFailTest(){
        assertEquals(PassValidator.validaPassword(passFail, regex),false);
    }
}