package com.gendra.suggestion.repository;

import com.gendra.suggestion.model.response.ItemResponse;

import java.io.IOException;

public interface ISuggestionRepository {
    ItemResponse findSuggestion(String pais, String longitud, String latitud) throws IOException;
}
