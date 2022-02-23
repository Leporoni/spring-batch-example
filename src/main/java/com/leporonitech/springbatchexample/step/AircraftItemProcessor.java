package com.leporonitech.springbatchexample.step;

import com.leporonitech.springbatchexample.model.Aircraft;
import java.util.Optional;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AircraftItemProcessor implements ItemProcessor<Aircraft, Aircraft> {

    @Override
    public Aircraft process(final Aircraft aircraft) {
        String recordKey = !StringUtils.isEmpty(aircraft.getIataCode()) ? aircraft.getIataCode() : aircraft.getIcaoCode();
        if (StringUtils.isEmpty(recordKey)) {
            return null;
        } else {
            aircraft.setRecordKey(recordKey);
            aircraft.setIataCode(Optional.ofNullable(aircraft.getIataCode()).map(String::toUpperCase).orElse(null));
            aircraft.setIcaoCode(Optional.ofNullable(aircraft.getIcaoCode()).map(String::toUpperCase).orElse(null));
            return aircraft;
        }
    }
}
