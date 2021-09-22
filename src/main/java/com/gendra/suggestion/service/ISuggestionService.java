package com.gendra.suggestion.service;

import com.gendra.suggestion.model.response.ItemResponse;

public interface ISuggestionService {
    ItemResponse findSuggestion(String pais, String longitud, String latitud);
}
