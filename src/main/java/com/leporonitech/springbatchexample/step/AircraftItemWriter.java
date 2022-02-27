package com.leporonitech.springbatchexample.step;

import javax.sql.DataSource;

import com.leporonitech.springbatchexample.model.Aircraft;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

@Component
public class AircraftItemWriter extends JdbcBatchItemWriter<Aircraft> {

    private static final String SQL_COMMAND =
            "INSERT INTO aircraft (record_key, iata_code, icao_code, name) " +
                    "VALUES (:recordKey, :iataCode, :icaoCode, :name) " +
                    "ON DUPLICATE KEY " +
                    "UPDATE iata_code = :iataCode, icao_code = :icaoCode, name = :name";

    public AircraftItemWriter(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setSql(SQL_COMMAND);
        this.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        this.afterPropertiesSet();
    }
}
