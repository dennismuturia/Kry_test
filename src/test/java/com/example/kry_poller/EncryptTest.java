package com.example.kry_poller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
class EncryptTest {
    String password = "hello";
    String getPassword1 = "Hello";

    @Test
    void getSHA() throws NoSuchAlgorithmException {
        assertNotEquals(new Encrypt().getSHA(password), new Encrypt().getSHA(getPassword1));
    }
}