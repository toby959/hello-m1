package com.toby959.hello_m1.output;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    private final ChatClient chatClient;


    public ActorController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/films-string")
    public String getActorFilmsString() {
        return chatClient.prompt()
                .user("Generame la filmografia de Anthony Hopkins del año 2010.")
                .call()
                .content();
    }

    @GetMapping("/films")
    public ActorFilms getActorFilms() {
        return chatClient.prompt()
                .user("Genera la filmografia del actor Anthony Hopkins.")
                .call()
                .entity(ActorFilms.class);
    }
    @GetMapping("/films-list")
    public List<ActorFilms> listActorFilms() {
        return chatClient.prompt()
                .user("Genera la filmografia de los actores Denzel Washington, Lewonardo DiCaprio y Tom _Hanks.")
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }
}
