package com.todor.api.demo.models;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name= "content")
public class Content {

    public Content(String type, String payload) {
        this.content_type = type;
        this.payload = payload;
        this.timestamp = new Date().getTime();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="type")
    private String content_type;

    @Column(name="payload")
    private String payload;

    @Column(name="created_at")
    private Long timestamp;

}