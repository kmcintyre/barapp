package com.nwice.barapp.calendartag.decorator;

import java.util.Calendar;

import javax.servlet.jsp.PageContext;

import com.nwice.barapp.model.Cashout;


public interface CalendarDecorator {

    public String getDayStyleClass(boolean isOddMonth, boolean isSelectedDay);
    public void initializeCalendar();
    
    public String getCalendarTitle();
    public String getWeekdayTitle(int day);
    public String getEmptyDay();
    public String getDay(String url);

    public void setPageContext(PageContext pageContext);

    public void setCalendar(Calendar calendar);
    
    public String printCashout(Cashout[] co, Calendar c, boolean d, boolean d2, boolean p, boolean b, boolean t, boolean amt, boolean pmt);
    
    public String getPreviousLink(String url);
    public String getPreviousLink(String url,String append);

    public String getNextLink(String url);
    public String getNextLink(String url, String append);
    void setStart(Calendar start);
    void setEnd(Calendar end);

}

