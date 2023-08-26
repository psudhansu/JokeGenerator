package com.chatGpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chatGpt.dto.ChatRequest;
import com.chatGpt.dto.ChatResponse;
import com.chatGpt.dto.Message;
import com.chatGpt.dto.Prompt;


@RestController
@RequestMapping("/bot")
@CrossOrigin("*")
public class Controller {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @GetMapping("/chat")
    public ResponseEntity<Message> chat(@RequestParam("prompt") String prompt){
    	System.out.println(prompt);
        ChatRequest request=new ChatRequest(model, prompt);
        
        ChatResponse chatGptResponse = template.postForObject(apiURL, request, ChatResponse.class);
        return new ResponseEntity<>(chatGptResponse.getChoices().get(0).getMessage(), HttpStatus.OK );
    }
    @PostMapping("/chat2")
    public ResponseEntity<Message> chat2(@RequestBody Prompt prompt){
    	System.out.println(prompt);
        ChatRequest request=new ChatRequest(model, prompt.getPrompt());
        
        ChatResponse chatGptResponse = template.postForObject(apiURL, request, ChatResponse.class);
        return new ResponseEntity<>(chatGptResponse.getChoices().get(0).getMessage(), HttpStatus.OK );
    }
}
