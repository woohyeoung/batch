package woo.hyeoung.batch.rdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import woo.hyeoung.batch.rdb.domain.MARKET;

import java.math.BigDecimal;

/**
 * name         : MarketRepository
 * version      : 1.0.0.1
 * date         : 2023-05-06
 * description  : 설명
 */
@Repository
public interface MarketRepository extends JpaRepository<MARKET, BigDecimal> {
}
