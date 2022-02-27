package com.leporonitech.springbatchexample;

import com.leporonitech.springbatchexample.model.Aircraft;
import com.leporonitech.springbatchexample.step.AircraftItemProcessor;
import com.leporonitech.springbatchexample.step.AircraftItemReader;
import com.leporonitech.springbatchexample.step.AircraftItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchExampleApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	public static void main(String[] args) {

		SpringApplication.run(SpringBatchExampleApplication.class, args);
	}

	@Bean("aircraftStep")
	public Step buildAircraftStep(AircraftItemReader aircraftItemReader, AircraftItemProcessor aircraftItemProcessor,
								  AircraftItemWriter aircraftItemWriter) {
		return stepBuilderFactory.get("aircraftStep")
				.<Aircraft, Aircraft>chunk(10)
				.reader(aircraftItemReader)
				.processor(aircraftItemProcessor)
				.writer(aircraftItemWriter)
				.build();
	}

	@Bean("aircraftJob")
	public Job buildAircraftJob(final Step aircraftStep) {
		return jobBuilderFactory.get("aircraftLoadingJob")
				.incrementer(new RunIdIncrementer())
				.flow(aircraftStep)
				.end()
				.build();
	}
}


