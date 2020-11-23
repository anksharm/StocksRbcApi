package com.rbc.restfulstock.dto;

public class StockDto {

    private Long id;
    private int quarter;
    private String stock;

    private String lastBusinessDate;

    private float open;
    private float high;
    private float low;
    private float close;

    private double volume;

    private float percentPriceChange;

    private float percentVolChangeLastWeek;

    private float volPreveiousWeek;
    private float openNextWeek;
    private float closeNextWeek;

    private float percentPriceChangeNextWeek;
    private int daysToNextDivident;
    private float percentReturnNextWeek;

    public String getLastBusinessDate() {
        return lastBusinessDate;
    }

    public void setLastBusinessDate(String lastBusinessDate) {
        this.lastBusinessDate = lastBusinessDate;
    }

    public StockDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public float getPercentPriceChange() {
        return percentPriceChange;
    }

    public void setPercentPriceChange(float percentPriceChange) {
        this.percentPriceChange = percentPriceChange;
    }

    public float getPercentVolChangeLastWeek() {
        return percentVolChangeLastWeek;
    }

    public void setPercentVolChangeLastWeek(float percentVolChangeLastWeek) {
        this.percentVolChangeLastWeek = percentVolChangeLastWeek;
    }

    public float getVolPreveiousWeek() {
        return volPreveiousWeek;
    }

    public void setVolPreveiousWeek(float volPreveiousWeek) {
        this.volPreveiousWeek = volPreveiousWeek;
    }

    public float getOpenNextWeek() {
        return openNextWeek;
    }

    public void setOpenNextWeek(float openNextWeek) {
        this.openNextWeek = openNextWeek;
    }

    public float getCloseNextWeek() {
        return closeNextWeek;
    }

    public void setCloseNextWeek(float closeNextWeek) {
        this.closeNextWeek = closeNextWeek;
    }

    public float getPercentPriceChangeNextWeek() {
        return percentPriceChangeNextWeek;
    }

    public void setPercentPriceChangeNextWeek(float percentPriceChangeNextWeek) {
        this.percentPriceChangeNextWeek = percentPriceChangeNextWeek;
    }

    public int getDaysToNextDivident() {
        return daysToNextDivident;
    }

    public void setDaysToNextDivident(int daysToNextDivident) {
        this.daysToNextDivident = daysToNextDivident;
    }

    public float getPercentReturnNextWeek() {
        return percentReturnNextWeek;
    }

    public void setPercentReturnNextWeek(float percentReturnNextWeek) {
        this.percentReturnNextWeek = percentReturnNextWeek;
    }

}