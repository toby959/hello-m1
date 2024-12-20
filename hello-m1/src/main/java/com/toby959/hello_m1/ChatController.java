package com.toby959.hello_m1;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;


    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("Eres un asistente ruidoso que responde con letras mayúsculas.")
                .build();
    }

    @GetMapping("")
    public String chat(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/jokes")
    public String jokes(@RequestParam String topic) {
        return  chatClient.prompt()
                .user(u-> u.text("Cuentame un chiste sobre banqueros {topic}").param("topic", topic))
                .call()
                .content();
    }
}
