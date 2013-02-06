package com.nwice.barapp.report.service;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.data.general.Dataset;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.nwice.barapp.report.ReportConstant;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

public class MovingAverageHelper implements DatasetProducer, Serializable {
     
	private static final Logger log = Logger.getLogger(DailyAverage.class);
     
     private TimeSeries timeSeries;
     private int skip = 0;
     private String periodString;
     private int period;
     private String suffix = " Moving Average";

     public void setSkip(int i) {
    	 skip = i;
     }
     
     public void setPeriod(int i) {
    	 period = i;
     }
     
     public void setPeriodString(String s) {
    	 periodString = s;
     }
     
     public void setTimeSeries(TimeSeries ts) {
    	 timeSeries = ts;
     }
     
     public Object produceDataset(Map params) throws DatasetProduceException {
    	 TimeSeries ts2 = MovingAverage.createMovingAverage(timeSeries, periodString + suffix, period, period - 1);
    	 TimeSeriesCollection dataset = new TimeSeriesCollection(ts2);
    	 return dataset;
     }

     public boolean hasExpired(Map params, Date since) {
         return true;
     }
     public String getProducerId() {
         return getClass().getName();
     }

}