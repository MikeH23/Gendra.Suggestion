package com.gendra.suggestion.service;

import com.gendra.suggestion.model.response.ItemResponse;
import com.gendra.suggestion.repository.ISuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class suggestionService implements ISuggestionService{

    @Autowired
    private ISuggestionRepository _ISuggestionRepository;

    public ItemResponse findSuggestion(String pais, String longitud, String latitud) throws IOException {
        ItemResponse result = _ISuggestionRepository.findSuggestion(pais, longitud, latitud);
        return  result;
    }
}
