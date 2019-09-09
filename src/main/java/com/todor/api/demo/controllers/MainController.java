package com.todor.api.demo.controllers;

import java.util.regex.Pattern;

import com.todor.api.demo.models.Content;
import com.todor.api.demo.repositories.ContentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private ContentRepository contentRepository;

    private Pattern digitPattern;

    public MainController() {
        digitPattern = Pattern.compile(".*\\d.*");
    }

    @PostMapping("/messages/{thing_type}")
    public ResponseEntity<?> send_text(@RequestBody String what, @PathVariable(value="thing_type") String thing_type) {
        
        // blow up validation for text
        if(thing_type.equals("send_text")){
            if(what.length() > 160 || what.length() < 1) {
                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }
        }
        // blow up validation for emotion
        if(thing_type.equals("send_emotion")){
            if(what.length() > 10 || this.digitPattern.matcher(what).matches() || what.length() < 2) {
                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }
        }
        // is it none of the above?! why do a 404 when I could have done 2 methods? 
        if(!thing_type.equals("send_emotion") && !thing_type.equals("send_text")) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        // try to save something
        else {
            System.out.println("Saving to database");
            String data_type = thing_type.split("_")[1];
            Content something = new Content(data_type, what);
            this.contentRepository.save(something);
            System.out.println("Saved " + what);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }   
    }

}
