package com.mft.securwa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.mft.securwa.entities.User;

public class UserUnitTest {
    
    @Test
    public void whenCalledGetName_thenCorrect() {
        User user = new User("Toto", "toto@tata.com");
        assertThat(user.getName()).isEqualTo("Toto");
    }
    
    @Test
    public void whenCalledGetEmail_thenCorrect() {
        User user = new User("Toto", "toto@tata.com");
        assertThat(user.getEmail()).isEqualTo("toto@tata.com");
    }
    
    @Test
    public void whenCalledSetName_thenCorrect() {
        User user = new User("Toto", "toto@tata.com");
        user.setName("John");
        assertThat(user.getName()).isEqualTo("John");
    }
    
    @Test
    public void whenCalledSetEmail_thenCorrect() {
        User user = new User("Toto", "toto@tata.com");
        user.setEmail("john@domain.com");
        assertThat(user.getEmail()).isEqualTo("john@domain.com");
    }
    
    @Test
    public void whenCalledtoString_thenCorrect() {
        User user = new User("Toto", "toto@tata.com");
        assertThat(user.toString()).isEqualTo("User{id=0, name=Toto, email=toto@tata.com}");
    }
}