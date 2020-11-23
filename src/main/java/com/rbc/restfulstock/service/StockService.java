package com.rbc.restfulstock.service;

import com.rbc.restfulstock.controller.StockController;
import com.rbc.restfulstock.dto.StockDto;
import com.rbc.restfulstock.exception.ErrorMessages;
import com.rbc.restfulstock.model.Stock;
import com.rbc.restfulstock.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Service
public class StockService {

    @Autowired
    public StockRepository stockRepository;

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    /**
     * Description: Method to save the stockDto. Uses the StockDto and saves it to the Stock Entity Dto.
     * @param stockDto
     * @return Stock Object.
     * @throws ErrorMessages
     */
    public StockDto saveDto(StockDto stockDto) throws ErrorMessages {
        Stock stockEntity = new Stock();
        try {
            BeanUtils.copyProperties(stockDto, stockEntity);
            Stock updatedStock = stockRepository.save(stockEntity);
            if(null != updatedStock){
                BeanUtils.copyProperties(updatedStock, stockDto);
            }
            return stockDto;
        }
        catch (Exception e) {
            logger.error("Error Message for SaveDto() " + e.getMessage());
            throw new ErrorMessages(e.getMessage());
        }
    }

    /**
     * Description: Method to get all the stocks present in DB
     * @return List of Stock
     * @throws ErrorMessages Exception is caught and stored in ErrorMessage.
     */

    public List<StockDto> getAllStocks() throws ErrorMessages {

        List<StockDto> stockDtoList = new ArrayList<>();;
        try {
            List<Stock> stocksList = stockRepository.findAll();
            if(null != stockDtoList){
                convertStockDtoStockEntity(stocksList, stockDtoList);
            }
            return stockDtoList;
        }
        catch (Exception e) {
            logger.error("Error Message for getAllStocks() " + e.getMessage());
            throw new ErrorMessages(e.getMessage());
        }
    }

    /**
     * Description: Method to get the stock details using stockname
     * @param stockName
     * @return List of stocks
     * @throws ErrorMessages Exception is caught and stored in ErrorMessage.
     */
    public List<StockDto> getStockByName(String stockName) throws ErrorMessages {
        List<StockDto> stockDtoList = new ArrayList<>();;
        try {
            List<Stock> stockList = stockRepository.findByStockName(stockName);
            if(!stockList.isEmpty()){
                convertStockDtoStockEntity(stockList,stockDtoList);
            }
            return stockDtoList;
        } catch (Exception e) {
            logger.error("Error Message for getStockByName() " + e.getMessage());
            throw new ErrorMessages(e.getMessage());
        }
    }

    /**
     * Description: Method to convert or copy the stock object to Stock db Object or stock entity.
     * @param entityList  -A List of the type Stock Entity(DB) Object.
     * @param dtoList -A List of the type Stock Dto Object.
     * @return List of the type Stock Entity Object
     */

    private List<StockDto> convertStockDtoStockEntity(List<Stock> entityList, List<StockDto> dtoList) {
        entityList.forEach(stocks ->{
                    StockDto stockDto = new StockDto();
                    BeanUtils.copyProperties(stocks,stockDto);
                    dtoList.add(stockDto);
                }
        );
        return dtoList;
    }


    /**
     * Description: Use the Data file to upload the stock details to the Db.
     * @param dataFile
     * @return List of the Stock(Db) Object.
     */
    public List<Stock> getStockList(MultipartFile dataFile){
        System.out.println("the name of FIle is: "+ dataFile.getOriginalFilename());
        logger.info("the name of FIle is: "+ dataFile.getOriginalFilename());

        List<Stock> stockList = new ArrayList<>();
        List<String> result = new ArrayList<>();
        BufferedReader br;
        try {
            String line;
            InputStream is = dataFile.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
            for(int i = 1; i < result.size();i++){
                Stock stock = new Stock();
                String[] record = result.get(i).split(",");

                // set attributes of stock object
                if(!record[0].isEmpty())stock.setQuarter(Integer.parseInt(record[0]));
                if(!record[1].isEmpty())stock.setStock(record[1]);
                //        if(!record[2].isEmpty())  stock.setLastBusinessDate(Date.parse(record[2]));
                if(!record[3].isEmpty())stock.setOpen(Float.parseFloat(record[3].substring(1)));
                if(!record[4].isEmpty())stock.setHigh(Float.parseFloat(record[4].substring(1)));
                if(!record[5].isEmpty())stock.setLow(Float.parseFloat(record[5].substring(1)));
                if(!record[6].isEmpty())stock.setClose(Float.parseFloat(record[6].substring(1)));
                if(!record[7].isEmpty())stock.setVolume(Double.parseDouble(record[7]));
                if(!record[8].isEmpty())stock.setPercentPriceChange(Float.parseFloat(record[8]));
                if(!record[9].isEmpty())stock.setPercentVolChangeLastWeek(Float.parseFloat(record[9]));
                if(!record[10].isEmpty())stock.setVolPreveiousWeek(Float.parseFloat(record[10]));
                if(!record[11].isEmpty())stock.setOpenNextWeek(Float.parseFloat(record[11].substring(1)));
                if(!record[12].isEmpty())stock.setCloseNextWeek(Float.parseFloat(record[12].substring(1)));
                if(!record[13].isEmpty())stock.setPercentPriceChangeNextWeek(Float.parseFloat(record[13]));
                if(!record[14].isEmpty())stock.setDaysToNextDivident(Integer.parseInt(record[14]));
                if(!record[15].isEmpty())stock.setPercentReturnNextWeek(Float.parseFloat(record[15]));

                stockList.add(stock);
            }

        } catch (IOException e) {
            logger.error(e.getMessage());
            System.err.println(e.getMessage());
        }
        return stockList;
    }
}

