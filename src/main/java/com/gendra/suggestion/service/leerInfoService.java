package com.gendra.suggestion.service;

import com.gendra.suggestion.model.response.ItemResponse;
import com.gendra.suggestion.model.citiesDTO;
import com.gendra.suggestion.service.RowMapper.CitiesRowMapper;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class leerInfoService {

    public static final String Ruta_Cities = "~/cities_canada-usa.tsv";

    public List<citiesDTO> leerArchivo(String strRuta){
        //region Variables
        ItemResponse itemResponse = new ItemResponse();
        List<String> lstNames = new ArrayList<String>();
        List<String> lstdtoerr = new ArrayList<>();
        //endregion

        try {
            Resource resource = new FileSystemResource(strRuta);
            Scanner scanner = new Scanner(resource.getInputStream());
            FlatFileItemReader<Object> itemReader = new FlatFileItemReader<>();
            itemReader.setResource(resource);

            //Contiene Encabezado el archivo
            itemReader.setLinesToSkip(1);
            itemReader.setSkippedLinesCallback(new SkipRecordCallback());

            //Asignamos las columnas del archivo
            String[] names = new String[]{"id","name","ascii","alt_name","lat","long","feat_class","feat_code","country","cc2","admin1","admin2","admin3","admin4","population","elevation","dem","tz","modified_at"};
            lstNames.toArray( names );
            DefaultLineMapper<Object> lineMapper = new DefaultLineMapper<>();
            DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
            tokenizer.setNames(names);
            tokenizer.setStrict(true);
            tokenizer.setDelimiter("\t");
            lineMapper.setLineTokenizer(tokenizer);

            //Mapeamos al objeto del archivo
            lineMapper.setFieldSetMapper(new CitiesRowMapper());
            itemReader.setLineMapper(lineMapper);
            itemReader.open(new ExecutionContext());

            //Lectura del archivo para result
            List<Object> lstFileresults = new ArrayList<>();
            Object Fileresult = null;
            Integer iCount =1;
            do {
                try {

                    Fileresult = itemReader.read();
                    if (Fileresult != null) {
                        String line = scanner.nextLine();
                        FieldSet fs = tokenizer.tokenize(line);
                        lstFileresults.add(Fileresult);
                    }

                    iCount++;
                }
                catch (FlatFileParseException e) {
                    lstdtoerr.add(e.getMessage());
                }
                catch (IncorrectTokenCountException e){
                    lstdtoerr.add(e.getMessage());
                }
                catch(Exception e){
                    itemResponse.setItem(e);
                    System.out.println(e);
                    return null;
                }
            }while (Fileresult != null);

            scanner.close();

            List<citiesDTO> lstCities =(List<citiesDTO>)(Object)lstFileresults;
            return lstCities;
        }
        catch (IOException ex) {
            itemResponse.setStatus(500);
            itemResponse.setItem(ex);
            return null;
        }
    }
}
