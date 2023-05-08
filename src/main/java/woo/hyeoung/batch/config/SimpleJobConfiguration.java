package woo.hyeoung.batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import woo.hyeoung.batch.domain.*;

import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * name         : SimpleJobConfiguration
 * version      : 1.0.0.1
 * date         : 2023-05-06
 * description  : 설명
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfiguration {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final EntityManagerFactory entityManagerFactory;

	@Bean
	public Job exampleJob() throws Exception {
		return jobBuilderFactory.get("exampleJob")
				.start(exampleStep()).build();
	}

	@Bean
	@JobScope
	public Step exampleStep() throws Exception {
		return stepBuilderFactory.get("exampleStep")
				.<MARKET, MARKET> chunk(10)
				.reader(reader(null))
				.processor(processor(null))
				.writer(writer(null))
				.build();
	}

	@Bean
	@StepScope
	public JpaPagingItemReader<MARKET> reader(@Value("#{jobParameters[requestDate]}")String requestDate) throws Exception {
		log.info("reader value : {}", requestDate);
		Map<String, Object> parameterValues = new HashMap<>();
		parameterValues.put("price", new BigDecimal("1000"));

		return new JpaPagingItemReaderBuilder<MARKET>()
				.pageSize(10)
				.parameterValues(parameterValues)
				.queryString("SELECT m FROM MARKET m WHERE m.price >= : price")
				.entityManagerFactory(entityManagerFactory)
				.name("JpaPagingItemReader")
				.build();
	}

	@Bean
	@StepScope
	public ItemProcessor<MARKET, MARKET> processor(@Value("#{jobParameters[requestDate]}")String requestDate) {
		return new ItemProcessor<MARKET, MARKET>() {
			@Override
			public MARKET process(MARKET item) throws Exception {
				log.info("processor Market: {}", item);
				log.info("processor value: {}", requestDate);

				item.setPrice(new BigDecimal("100"));
				return item;
			}
		};
	}

	@Bean
	@StepScope
	public JpaItemWriter<MARKET> writer(@Value("#{jobParameters[requestDate]}")String requestDate) {
		log.info("writer value: {}", requestDate);
		return new JpaItemWriterBuilder<MARKET>()
				.entityManagerFactory(entityManagerFactory)
				.build();
	}

}
