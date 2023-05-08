package woo.hyeoung.batch.redis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import woo.hyeoung.batch.redis.domain.Person;

/**
 * name         : PersonRedisRepository
 * version      : 1.0.0.1
 * date         : 2023-05-08
 * description  : 설명
 */
@Repository
public interface PersonRedisRepository extends CrudRepository<Person, String> {
}
