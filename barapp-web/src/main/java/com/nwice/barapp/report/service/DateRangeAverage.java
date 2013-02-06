package com.nwice.barapp.report.service;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateRangeAverage {

	public static final NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
	
	private Date fromDate, toDate;

	private int view;
	private int shift;	

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	public void setView(int i) {
		view = i;
	}
	
	public int getView() {
		return view;
	}
	
	public void setShift(int i) {
		shift = i;
	}
	
	public int getShift() {
		return shift;
	}		

	
}
