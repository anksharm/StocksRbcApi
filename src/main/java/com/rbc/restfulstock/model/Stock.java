package com.rbc.restfulstock.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(generator = "id_generator")
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_sequence",
            initialValue = 1000
    )
    private Long id;

    private int quarter;

    @NotBlank
    @Size(min = 1, max = 5)
    private String stock;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "last_business_date", nullable = false)
//    private Date lastBusinessDate;

    private float open;
    private float high;
    private float low;
    private float close;

    private  double volume;

    @Column(name = "percent_change_price")
    private float percentPriceChange;

    @Column(name = "percent_change_volume_over_last_wk")
    private float percentVolChangeLastWeek;

    @Column(name = "previous_weeks_volume")
    private float volPreveiousWeek;
    @Column(name = "next_weeks_open")
    private float openNextWeek;
    @Column(name = "next_weeks_close")
    private float closeNextWeek;

    @Column(name = "percent_change_next_weeks_price")
    private float percentPriceChangeNextWeek;
    @Column(name = "days_to_next_dividend")
    private int daysToNextDivident;
    @Column(name = "percent_return_next_dividend")
    private float percentReturnNextWeek;




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

//    public Date getLastBusinessDate() {
//        return lastBusinessDate;
//    }
//
//    public void setLastBusinessDate(Date lastBusinessDate) {
//        this.lastBusinessDate = lastBusinessDate;
//    }

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
