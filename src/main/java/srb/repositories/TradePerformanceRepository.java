package srb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import srb.model.TradePerformanceModel;


@Repository
public interface TradePerformanceRepository extends JpaRepository<TradePerformanceModel, Long>{

}