package com.platzi.play.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface PlatziPlayAiService {

    @UserMessage("""
            Generate a welcome greeting for the PlatziPlay Movie Management {{platform}}.
            Use fewer than 120 characters and do it in Platzi’s style.
            """)
    String generateGreeting(@V("platform") String platform);

    @SystemMessage("""
            Eres un experto en cine que recomienda películas personalizadas según los gustos del ususario.
            Debes recomendar máximo 3 películas.        
            No incluyas películas que estén por fuera de la plataforma PlatziPlay.
            """)
    String generateMovieSuggestions(@UserMessage String userPreferences);
}
