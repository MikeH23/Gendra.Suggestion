package com.gendra.suggestion.repository;

import com.gendra.suggestion.model.response.ItemResponse;
import com.gendra.suggestion.model.citiesDTO;
import com.gendra.suggestion.model.response.citiesResponse;
import com.gendra.suggestion.service.leerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class suggestionImp implements ISuggestionRepository{

    @Autowired
    private leerInfoService _LeerInfoService;

    public static final String Ruta_Cities = "~/cities_canada-usa.tsv";

    @Override
    public ItemResponse findSuggestion(String pais, String longitud, String latitud) throws IOException {
        //region variables
        ItemResponse itemResponse =  new ItemResponse();
        /** ruta archivo local*/
        String strRuta = "src/main/resources/data/cities_canada-usa.tsv";
        /** ruta archivo nube */
        //String strRuta = "/application/BOOT-INF/classes/data/cities_canada-usa.tsv";//"BOOT-INF/classes!/org/resources/cities_canada-usa.tsv";
        List<citiesResponse> lstResponse = new ArrayList<>();
        List<citiesDTO> lstFilterCities = new ArrayList<>();
        Double dblLatitude;
        Double dblLongitude;
        //endregion

        try {
            List<citiesDTO> lstCities = (List<citiesDTO>) _LeerInfoService.leerArchivo(strRuta);

            System.out.println("Ruta: " + strRuta);

            if(lstCities != null) {

                if (latitud != null) {
                    dblLatitude = Double.valueOf(latitud);
                    dblLongitude = Double.valueOf(longitud);
                    lstFilterCities = lstCities.stream().filter(x -> x.name.startsWith(pais) || (x.latitude <= dblLatitude || x.longitude <= dblLongitude)).collect(Collectors.toList());
                } else {
                    lstFilterCities = lstCities.stream().filter(x -> x.name.startsWith(pais)).collect(Collectors.toList());
                }
                for (citiesDTO itemFilter : lstFilterCities) {
                    citiesResponse citiesResponse = new citiesResponse();
                    citiesResponse.name = itemFilter.name + ", " + itemFilter.admin1Code + ", " + itemFilter.countryCode;
                    citiesResponse.latitude = itemFilter.latitude.toString();
                    citiesResponse.longitude = itemFilter.longitude.toString();

                    lstResponse.add(citiesResponse);
                }

                itemResponse.setItem(lstResponse);
            } else {
                itemResponse.setStatus(400);
                itemResponse.setItem("No se encontro el archivo cities_canada-usa.tsv en la ruta: " + strRuta);
            }
            return itemResponse;
        }
        catch (Exception e)
        {
            itemResponse.setItem(e);
            System.out.println(e);
            return itemResponse;
        }
    }

}
