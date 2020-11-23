package com;

import com.rbc.restfulstock.Application;
import com.rbc.restfulstock.dto.StockDto;
import com.rbc.restfulstock.model.Stock;
import com.rbc.restfulstock.repository.StockRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {


	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private StockRepository stockRepository;

	@LocalServerPort
	private int port;

	private String getRootUrl(){
		return "http://localhost:" +port;
	}

	@Test
	void contextLoads() {
	}


	@Test
	public void testGetAllStocks(){
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl()+"/stocks",
				HttpMethod.GET, entity, String.class);

		Assert.assertNotNull(response.getBody());

	}

	@Test
	public void testStockByNameNotFound(){
		Stock stock = new Stock();
		stock.setStock("AAA");

		List<Stock> stockList = stockRepository.findByStockName("A");
		assertTrue(stockList.isEmpty());
	}

	@Test
	public void testStockNameLengthConstraintCheck(){
		StockDto stock = new StockDto();
		stock.setStock("TestConstraintCheck");
		stock.setQuarter(1);
		stock.setOpen(12.5f);
		stock.setClose(55.5f);
		stock.setHigh(12.3f);
		stock.setLow(12.5f);
		stock.setVolume(12);
		stock.setPercentPriceChange(12.6f);
		stock.setPercentPriceChangeNextWeek(12.4f);
		stock.setVolPreveiousWeek(45.5f);
		stock.setOpenNextWeek(45.5f);
		stock.setCloseNextWeek(55.5f);
		stock.setPercentPriceChangeNextWeek(33.3f);
		stock.setDaysToNextDivident(56);
		stock.setPercentReturnNextWeek(55.55f);

		String response = testRestTemplate.postForObject(getRootUrl() + "/stocks",
				stock, String.class);

		assertThat(response.contains("RollbackException"));
	}


	@Test
	public void testPostStock(){
		StockDto stock = new StockDto();
		stock.setQuarter(1);
		stock.setStock("TST");
		stock.setOpen(12.5f);
		stock.setClose(55.5f);
		stock.setHigh(12.3f);
		stock.setLow(12.5f);
		stock.setVolume(12);
		stock.setPercentPriceChange(12.6f);
		stock.setPercentPriceChangeNextWeek(12.4f);
		stock.setVolPreveiousWeek(45.5f);
		stock.setOpenNextWeek(45.5f);
		stock.setCloseNextWeek(55.5f);
		stock.setPercentPriceChangeNextWeek(33.3f);
		stock.setDaysToNextDivident(56);
		stock.setPercentReturnNextWeek(55.55f);

		ResponseEntity<StockDto> postTestResponse = testRestTemplate.postForEntity(getRootUrl()+ "/stocks"
				, HttpMethod.POST, StockDto.class);
		assertThat(testRestTemplate.getForObject(getRootUrl() + "/stocks/TST" , String.class)
				.contains(stock.getStock()));
		Assert.assertNotNull(postTestResponse);
		Assert.assertNotNull(postTestResponse.getBody());
	}

	@Test
	public void testStockByName(){
		Stock stock = new Stock();
		stock.setStock("AAA");

		List<Stock> stockList = stockRepository.findByStockName("AAA");
		if(stockList != null){
			stockList.forEach(stockName -> assertThat(stock.getStock()).isIn(stockName.getStock()));
		}
	}

/*	@Test
	public void testGetAllStockRepo(){
		Stock stock = new Stock();
		stock.setStock("AA");
		List<Stock> stockList = stockRepository.findAll();
		if(stockList != null){
			stockList.forEach(stockId -> assertThat(stock.getStock()).isEqualTo(stockId.getStock()));
		}
	}*/

}
