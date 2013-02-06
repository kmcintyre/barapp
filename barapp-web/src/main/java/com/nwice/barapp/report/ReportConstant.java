package com.nwice.barapp.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface ReportConstant {
	
	public static final String[] timePeriods = { "year", "month", "week", "day" };
	
	public static final String[] months = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	
	public static final String[] week = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public static final String defaultPeriodValue = "0-0-0";
	
	public static final String defaultChartType = "stackedverticalbar3d";
	
	public static final int defaultTimePeriod = 0;
	public static final int defaultTimeInterval = 2;
	
	public static final int defaultView = 0;
	
	public static final int defaultShift = 0;
	
	public static final int defaultWidth = 800;
	public static final int defaultHeight = 600;
	
	
	public static final String[] reports = {"Daily Average", "Moving Average", "Missing Shifts"};
	
	public static final int defaultMovingAveragePeriod = 1;
	
	public static final int[] movingAverageCharts = {6};
	
	public static final int defaultMovingAverageChart = 6;
	public static final int defaultDailyAverageChart = 11;
	
	public static final int defaultMovingAverageShift = 0;
	public static final int defaultDailyAverageShift = 0;
	
	public static final int defaultDailyAverageView = 0;
	public static final int defaultMovingAverageView = 0;
	
	
	public static final String[] viewArray = {"Total", "Drop", "Payouts", "Overrings", "Shortages", "Tips", "Buybacks"};
	public static final String[] shiftArray = {"Both", "AM", "PM"};
	
	public static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	public static final DateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final DateFormat fullDbDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final String fromDate = "from_date";	
	public static final String toDate = "to_date";

	public static final int defaultMovingAverageInterval = 0;
	public static final String[] movingAverageIntervals = {"daily", "weekly", "monthly", "quarterly", "yearly"};
	
	public static final String[] movingAveragePeriod0 = {"1-day", "2-day", "3-day", "4-day", "5-day", "6-day", "7-day", "14-day", "28-day", "90-day", "180-day", "360-day"};	
	public static final String[] movingAveragePeriod1 = {"1-week", "2-week", "3-week", "4-week", "8-week", "12-week", "26-week", "52-week"};
	public static final String[] movingAveragePeriod2 = {"1-month", "2-month", "3-month", "4-month", "6-month", "9-month", "12-month"};
	public static final String[] movingAveragePeriod3 = {"1-quarter", "2-quarter", "3-quarter", "4-quarter"};
	public static final String[] movingAveragePeriod4 = {"1-year", "2-year", "3-year", "4-year"};
}



