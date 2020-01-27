package com.mft.securwa.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    @Lob
    private byte[] file;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Document() {
    }

    public Document(String name, Date date) {
        this.setName(name);
        this.setDate(date);
    }

    public Document(String name, Date date, byte[] fileContent) {
        this.setName(name);
        this.setDate(date);
        this.setFile(fileContent);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}