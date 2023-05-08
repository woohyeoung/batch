package woo.hyeoung.batch.redis.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

/**
 * name         : Person
 * version      : 1.0.0.1
 * date         : 2023-05-08
 * description  : 설명
 */
@Getter
@RedisHash(value = "people", timeToLive = -1L)
public class Person {
	@Id
	private String id;
	private String name;
	private Integer age;
	private LocalDateTime createdAt;

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
		this.createdAt = LocalDateTime.now();
	}
}
