package com.mft.securwa;

import static org.assertj.core.api.Assertions.assertThat;

import com.mft.securwa.entities.Document;
import org.junit.Test;

import com.mft.securwa.entities.User;

import java.util.Date;

public class DocumentUnitTest {

    @Test
    public void whenCalledGetName_thenCorrect() {
        Document document = new Document("Toto", new Date());
        assertThat(document.getName()).isEqualTo("Toto");
    }

    @Test
    public void whenCalledGetDate_thenCorrect() {
        Date date = new Date();
        Document document = new Document("Toto", date);
        assertThat(document.getDate()).isEqualTo(date);
    }

    @Test
    public void whenCalledSetName_thenCorrect() {
        Document document = new Document("Toto", new Date());
        document.setName("Tata");
        assertThat(document.getName()).isEqualTo("Tata");
    }

}