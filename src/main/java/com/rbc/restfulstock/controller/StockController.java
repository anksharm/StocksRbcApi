package com.rbc.restfulstock.controller;

import com.rbc.restfulstock.dto.StockDto;
import com.rbc.restfulstock.exception.ErrorMessages;
import com.rbc.restfulstock.model.Stock;
import com.rbc.restfulstock.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.rbc.restfulstock.service.StockService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
/**
 * Description: Get and save Stock details to the DB.
 */
@RestController
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    /**
     * Description: Lists out all the stocks present in the DB.
     * Parameter:
     * @return : returns a list.
     */
    @GetMapping("/stocks")
    public ResponseEntity<Object> getStocks() {

        try {
            List<StockDto> stockDtoList = stockService.getAllStocks();
            if(null == stockDtoList || stockDtoList.isEmpty()){
                logger.info("No Record found for getStocks" );
                return new ResponseEntity<>("No record found", HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(stockDtoList, HttpStatus.OK);
            }
        } catch (ErrorMessages errorMessages) {
            logger.info("Error Message for GetStocks() :" + errorMessages.getMessage());
            return new ResponseEntity<>(errorMessages.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Description: Lists out all the stocks for the stockname provided.
     * @param stockName
     * @return a List
     */
    @RequestMapping("/stocks/{stockName}")
    @ResponseBody
    public ResponseEntity<Object> getStockByName(@PathVariable String stockName){

        try {
            List<StockDto> stockDtoList = stockService.getStockByName(stockName);
            if(null == stockDtoList || stockDtoList.isEmpty()){
                logger.info("No record Found for GetStocksByName()" );
                return new ResponseEntity<>("No record found", HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(stockDtoList, HttpStatus.OK);
            }
        } catch (ErrorMessages errorMessages) {
            logger.error("Error Message for GetStocksByName() :" + errorMessages.getMessage());
            return new ResponseEntity<>(errorMessages.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Description: Method to save the details of the stock.
     * @param stockDto
     * @return Updated Stock Object.
     */
    @PostMapping("/stocks")
    public ResponseEntity<Object> insertStock(@Valid @RequestBody StockDto stockDto) {
        try {
            StockDto stockDtoSaved = stockService.saveDto(stockDto);
            logger.info("Successfully Posted ");
            return new ResponseEntity<Object>(stockDtoSaved, HttpStatus.OK);

        } catch (ErrorMessages errorMessages) {
            logger.error("No record inserted for insertStock() -" + errorMessages.getMessage());
            return new ResponseEntity<Object>(errorMessages.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Description: Method to save details of the stock using file upload.
     * @param dataFile
     * @return List of the stock updated.
     * @throws Exception
     */
    @PostMapping("/stocks/bulkupload")
    public ResponseEntity<Object>  bulkupload(@RequestParam("file") MultipartFile dataFile) throws Exception {

        if (dataFile == null) {
            logger.error("datafile is null for buklupload");
            throw new ErrorMessages("You must select the a file param for uploading");
        }

        try {
            List<StockDto> stockDtoList = stockService.saveBulkUpload(dataFile);
            return new ResponseEntity<Object>(stockDtoList, HttpStatus.OK);
        } catch (ErrorMessages errorMessages) {
            logger.error("No record Inserted for buklUpload() -" + errorMessages.getMessage());
            return new ResponseEntity<Object>(errorMessages.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
