package com.gendra.suggestion.repository;

import com.gendra.suggestion.model.response.ItemResponse;

public interface ISuggestionRepository {
    ItemResponse findSuggestion(String pais, String longitud, String latitud);
}
