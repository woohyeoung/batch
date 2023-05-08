package woo.hyeoung.batch.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * name         : ExampleScheduler
 * version      : 1.0.0.1
 * date         : 2023-05-06
 * description  : 설명
 */
@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class ExampleScheduler {
	private final Job job;
	private final JobLauncher jobLauncher;

	@Scheduled(fixedDelay = 30000)
	public void startJob() {
		try {
			Map<String, JobParameter> jobParameterMap = new HashMap<>();

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();

			String time = format.format(date);
			jobParameterMap.put("requestDate", new JobParameter(time));
			JobParameters parameter = new JobParameters(jobParameterMap);
			JobExecution jobExecution = jobLauncher.run(job, parameter);

			while (jobExecution.isRunning()) {
				log.info("isRunning============");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
