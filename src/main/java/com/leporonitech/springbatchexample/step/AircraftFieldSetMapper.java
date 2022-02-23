package com.leporonitech.springbatchexample.step;

import com.leporonitech.springbatchexample.model.Aircraft;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class AircraftFieldSetMapper implements FieldSetMapper<Aircraft> {

    public Aircraft mapFieldSet(FieldSet fieldSet) throws ParseException {
        Aircraft aircraft = new Aircraft();
        aircraft.setIataCode(fieldSet.readString(0));
        aircraft.setIcaoCode(fieldSet.readString(1));
        aircraft.setName(fieldSet.readString(2));
        return aircraft;
    }
}
