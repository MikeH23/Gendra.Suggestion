package com.gendra.suggestion.controller;

import com.gendra.suggestion.exception.Response;
import com.gendra.suggestion.service.ISuggestionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/gendra")
@RestController
public class suggestionController {

    @Autowired
    private ISuggestionService _ISuggestionService;

    @ApiOperation(value = "Obtiene la relación de los campos relacionados entre si")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "No autorizado"),
            @ApiResponse(code = 500, message = "Error interno"),
            @ApiResponse(code = 204, message = "Catálogo sin registros"),
    })
    @GetMapping("/suggestion")
    public ResponseEntity<Response> findSuggestion(@RequestParam String q,
                                                   @RequestParam (required = false) String longitude,
                                                   @RequestParam (required = false) String latitude){
        Response res;
        try {

            res = new Response(Long.parseLong("200"), _ISuggestionService.findSuggestion(q, longitude, latitude));
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        catch (Exception e){
            res = new Response(Long.parseLong("500"),e);
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
