package com.leporonitech.springbatchexample.step;

import com.leporonitech.springbatchexample.model.Aircraft;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class AircraftItemReader extends FlatFileItemReader<Aircraft> {

    public AircraftItemReader() {
        DefaultLineMapper<Aircraft> defaultLineMapper = new DefaultLineMapper<>();
        defaultLineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        defaultLineMapper.setFieldSetMapper(new AircraftFieldSetMapper());
        this.setLineMapper(defaultLineMapper);
        this.setResource(new ClassPathResource("aircrafts.dat"));
    }
}
