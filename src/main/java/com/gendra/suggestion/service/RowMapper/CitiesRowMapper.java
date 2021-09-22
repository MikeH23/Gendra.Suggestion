package com.gendra.suggestion.service.RowMapper;

import com.gendra.suggestion.model.citiesDTO;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class CitiesRowMapper implements FieldSetMapper<Object> {

    @Override
    public citiesDTO mapFieldSet(FieldSet fieldSet) throws FlatFileParseException{

        citiesDTO registro = new citiesDTO();
        try {
            //registro.setId(fieldSet.readLong("id"));
            registro.setName(fieldSet.readString("name"));
            registro.setAscii(fieldSet.readString("ascii"));
            registro.setAltName(fieldSet.readString("alt_name"));
            registro.setLatitude(fieldSet.readDouble("lat"));
            registro.setLongitude(fieldSet.readDouble("long"));
            registro.setFeatureClass(fieldSet.readString("feat_class"));
            registro.setFeatureCode(fieldSet.readString("feat_code"));
            registro.setCountryCode(fieldSet.readString("country"));
            registro.setCc2(fieldSet.readString("cc2"));
            registro.setAdmin1Code(fieldSet.readString("admin1"));
            registro.setAdmin2Code(fieldSet.readString("admin2"));
            registro.setAdmin3Code(fieldSet.readString("admin3"));
            registro.setAdmin4Code(fieldSet.readString("admin4"));
            registro.setPopulation(fieldSet.readInt("population"));
            registro.setElevation(fieldSet.readString("elevation"));
            registro.setDem(fieldSet.readInt("dem"));
            registro.setTimezone(fieldSet.readString("tz"));
            registro.setModificationDate(fieldSet.readString("modified_at"));

            return registro;
        }
        catch (FlatFileParseException e){
            return registro;
        }
    }
}
