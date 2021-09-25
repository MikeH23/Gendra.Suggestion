package com.gendra.suggestion.service;

import com.gendra.suggestion.model.response.ItemResponse;

import java.io.IOException;

public interface ISuggestionService {
    ItemResponse findSuggestion(String pais, String longitud, String latitud) throws IOException;
}
