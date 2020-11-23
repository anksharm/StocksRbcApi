package com.rbc.restfulstock.repository;

import com.rbc.restfulstock.dto.StockDto;
import com.rbc.restfulstock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    /**
     * Description: Query Method to get the details from Stock table using a stockname(stock).
     * @param stockName
     * @return List of the Stock(DB) Entity object.
     */
    @Query(value="select * from stock where stock =:stockName",nativeQuery=true)
    List<Stock> findByStockName(@Param("stockName") String stockName);

}