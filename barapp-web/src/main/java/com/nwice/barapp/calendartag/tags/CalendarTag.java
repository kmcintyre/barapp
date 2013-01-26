package com.nwice.barapp.calendartag.tags;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.nwice.barapp.calendartag.decorator.CalendarDecorator;
import com.nwice.barapp.calendartag.decorator.DefaultCalendarDecorator;
import com.nwice.barapp.calendartag.util.CalendarTagUtil;
import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;

public class CalendarTag implements Tag {

	@Autowired
	private CashoutManager cm;

	private static Logger log = Logger.getLogger(CalendarTag.class);

	private final static String EMPTY_DAY_STYLE = "calendarEmptyDayStyle";
	private final static String TABLE_STYLE = "calendarTableStyle";
	private final static String TITLE_STYLE = "calendarTitleStyle";
	private final static String WEEKDAY_STYLE = "calendarWeekdayStyle";
	private final static String PREVIOUS_LINK_STYLE = "calendarPreviousLinkStyle";
	private final static String NEXT_LINK_STYLE = "calendarNextLinkStyle";

	private final static boolean INTERACTIVE_DEFAULT = true;
	private final static String WEEK_START_DEFAULT = "Sunday";
	private final static int DAY_HEIGHT_DEFAULT = 85;
	private final static int DAY_WIDTH_DEFAULT = 85;

	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

	private PageContext pageContext;
	private Tag parent;

	private String decorator;
	private String requestURI;
	private int dayHeight;
	private int dayWidth;

	private String id;
	private String cssPrefix;
	private String weekStart;
	private String showPreviousNextLinks;

	private String startDate;
	private String endDate;

	private String date;

	private int day = 0;
	private int month = 0;
	private int year = 0;

	private Calendar startCalendar;
	private Calendar endCalendar;
	private Calendar calendar;
	private Boolean showPreviousNextLinksBoolean;

	private Boolean drop;
	private Boolean payouts;
	private Boolean bartenders;
	private Boolean cashbox;
	private Boolean daily;
	private Boolean tips;
	private Boolean amtotal;
	private Boolean pmtotal;

	private CalendarDecorator decoratorObject;

	private Calendar todayCalendar = new GregorianCalendar();

	private boolean singleMonth = false;

	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, pageContext.getServletContext());
	}

	public void setParent(Tag parent) {
		this.parent = parent;
	}

	public String printParams() {
		StringBuffer sb = new StringBuffer("");
		try {
			if (drop.booleanValue()) {
				sb.append("&drop_param=on");
			}
			if (payouts.booleanValue()) {
				sb.append("&payouts_param=on");
			}
			if (bartenders.booleanValue()) {
				sb.append("&bartenders_param=on");
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	public Cashout[] getCashouts(Date d, Date d2) throws JspException {
		try {
			return cm.getCashoutsByDates(d, d2);
		} catch (Exception e) {
			log.error("getCashouts", e);
			throw new JspException(e.getMessage());
		}

	}

	public Tag getParent() {
		return parent;
	}

	public int doStartTag() throws JspException {
		return Tag.EVAL_BODY_INCLUDE;
	}

	private void initializeAttributes() throws JspException {

		CalendarTagUtil.trimCalendar(todayCalendar);

		if (id == null) {
			id = "";
		}

		if (cssPrefix == null) {
			cssPrefix = "";
		}

		// prepare the calendar decorator
		if (!"".equals(decorator) && decorator != null) {
			// see if what the user specified works
			try {
				Class decoratorClass = Class.forName(decorator);
				decoratorObject = (CalendarDecorator) decoratorClass
						.newInstance();
			} catch (Exception e) {
				throw new JspException("Cannot resolve decorator: " + decorator);
			}
		} else {
			// use the default decorator
			decoratorObject = new DefaultCalendarDecorator();
		}

		// try to evaluate the showPreviousNextLink expression
		if ("true".equalsIgnoreCase(showPreviousNextLinks)) {
			showPreviousNextLinksBoolean = new Boolean(true);
		} else if ("false".equalsIgnoreCase(showPreviousNextLinks)) {
			showPreviousNextLinksBoolean = new Boolean(false);
		} else {
			try {
				showPreviousNextLinksBoolean = (Boolean) ExpressionUtil
						.evalNotNull("calendartag", "calendar",
								showPreviousNextLinks, Boolean.class, this,
								pageContext);
			} catch (Exception e) {
				showPreviousNextLinksBoolean = null;
			}
		}

		if (showPreviousNextLinksBoolean == null) {
			showPreviousNextLinksBoolean = new Boolean(INTERACTIVE_DEFAULT);
		}
		if (day == 0 && month == 0 && year == 0) {

			// try to evaluate the date expression
			// **************BEGINNNIG OF CHANGED
			// AREA**********************************
			Date tempDate = null;
			try {
				tempDate = (Date) ExpressionUtil.evalNotNull("calendartag",
						"calendar", date, Date.class, this, pageContext);
			} catch (Exception e) {
				// wow this exception handling is so 2003 (2004?) sorry
				log.debug("date did not parse as el object...");
				tempDate = null;
				calendar = null;
			}
			if (tempDate == null) {
				try {
					tempDate = new Date(date);
				} catch (Exception e) {
					log.debug("date did not parse as string...");
					tempDate = null;
					calendar = null;
				}
			}
			if (tempDate != null) {
				calendar = new GregorianCalendar();
				calendar.setTime(tempDate);
				CalendarTagUtil.trimCalendar(calendar);
			}

			// try to evaluate the startDate expression
			tempDate = null;
			try {
				tempDate = (Date) ExpressionUtil.evalNotNull("calendartag",
						"calendar", startDate, Date.class, this, pageContext);
			} catch (Exception e) {
				log.debug("Start date did not parse as el object...");
				tempDate = null;
				startCalendar = null;
			}
			if (tempDate == null) {
				try {
					tempDate = new Date(startDate);

				} catch (Exception e) {
					log.debug("Start date did not parse as string...");
					tempDate = null;
					startCalendar = null;
				}
			}
			if (tempDate != null) {
				startCalendar = new GregorianCalendar();
				startCalendar.setTime(tempDate);
				CalendarTagUtil.trimCalendar(startCalendar);
			}

			// try to evaluate the endDate expression
			tempDate = null;
			try {
				tempDate = (Date) ExpressionUtil.evalNotNull("calendartag",
						"calendar", endDate, Date.class, this, pageContext);
			} catch (Exception e) {
				log.debug("End Date did not parse as el object...");
				tempDate = null;
				endCalendar = null;
			}
			if (tempDate == null) {
				try {
					tempDate = new Date(endDate);
				} catch (Exception e) {
					log.debug("End Date did not parse as string...");
					tempDate = null;
					endCalendar = null;
				}
			}
			if (tempDate != null) {
				endCalendar = new GregorianCalendar();
				endCalendar.setTime(tempDate);
				CalendarTagUtil.trimCalendar(endCalendar);
			}
			// ************END OF CHANGED
			// AREA************************************************
			if (startCalendar == null && endCalendar != null) {
				throw new JspException(
						"You specified an endDate but not a startDate");
			}

			if (endCalendar == null && startCalendar != null) {
				throw new JspException(
						"You specified a startDate but not an endDate");
			}

			if (startCalendar != null && endCalendar != null) {

				// make sure they don't contain hours, minutes or seconds
				CalendarTagUtil.trimCalendar(startCalendar);
				CalendarTagUtil.trimCalendar(endCalendar);

				// make sure they are in order
				if (startCalendar.after(endCalendar)) {
					throw new JspException(
							"You startDate is after your endDate.");
				}
				log.debug("startCalendar and endCalendar were not null");
				if (calendar == null) {
					// set the current date to the first date overall, this will
					// be overridden by the request
					// parameters if neccessary
					log.debug("calendar is null");
					calendar = (Calendar) startCalendar.clone();
					log.debug("calendar is " + calendar.getTime());
				}

			}

		}

		// check to see if the calendar is null, if it is we should try to use
		// the specified attributes
		if (calendar == null) {
			calendar = (Calendar) todayCalendar.clone();
			// if they do equal 0 then they will default to the current date
			if (day != 0) {
				calendar.set(Calendar.DATE, day);
			}
			if (month != 0) {
				calendar.set(Calendar.MONTH, month);
			}
			if (year != 0) {
				calendar.set(Calendar.YEAR, year);
			}
		}

		// if they are both null we will use a normal month calendar
		if (startCalendar == null && endCalendar == null) {

			endCalendar = (Calendar) todayCalendar.clone();
			startCalendar = (Calendar) todayCalendar.clone();

			// set it to the first day of evaluatedDates month
			startCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
			startCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
			startCalendar.set(Calendar.DATE,
					startCalendar.getActualMinimum(Calendar.DATE));

			// set it to the last day of evaluatedDates month
			endCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
			endCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
			endCalendar.set(Calendar.DATE,
					startCalendar.getActualMaximum(Calendar.DATE));

			singleMonth = true;

		}

		// check the parameter for a changed selected date
		try {
			// even if a tempDate is already specified this takes precidence
			Date tempDate = DATE_FORMAT.parse(pageContext.getRequest()
					.getParameter(id + "date"));
			Calendar parameterCalendar = new GregorianCalendar();
			parameterCalendar.setTime(tempDate);
			if (parameterCalendar != null && !singleMonth) {

				calendar = parameterCalendar;

				// if the tempDate isn't inside the valid range because it was
				// overridden by the parameter
				// we need to calculate the new range
				if (endCalendar != null
						&& startCalendar != null
						&& CalendarTagUtil.dayCompare(calendar, endCalendar) > 0) {

					int dayDifference = CalendarTagUtil.differenceInDays(
							startCalendar, endCalendar) + 1;

					// continue until the tempDate is no longer after the end
					// tempDate
					while (CalendarTagUtil.dayCompare(calendar, endCalendar) > 0) {

						// increment both calendars by the dayDifference
						endCalendar.set(Calendar.DATE,
								endCalendar.get(Calendar.DATE) + dayDifference);
						startCalendar.set(Calendar.DATE,
								startCalendar.get(Calendar.DATE)
										+ dayDifference);

					}

				} else if (endCalendar != null
						&& startCalendar != null
						&& CalendarTagUtil.dayCompare(calendar, startCalendar) < 0) {

					int dayDifference = CalendarTagUtil.differenceInDays(
							startCalendar, endCalendar) + 1;

					// continue until the tempDate is no longer before the start
					// tempDate
					while (CalendarTagUtil.dayCompare(calendar, startCalendar) < 0) {

						// increment both calendars by the dayDifference
						endCalendar.set(Calendar.DATE,
								endCalendar.get(Calendar.DATE) - dayDifference);
						startCalendar.set(Calendar.DATE,
								startCalendar.get(Calendar.DATE)
										- dayDifference);

					}

				}

			} else {

				calendar = parameterCalendar;

				// set it to the first day of evaluatedDates month
				startCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
				startCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
				startCalendar.set(Calendar.DATE,
						startCalendar.getActualMinimum(Calendar.DATE));

				// set it to the last day of evaluatedDates month
				endCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
				endCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
				endCalendar.set(Calendar.DATE,
						startCalendar.getActualMaximum(Calendar.DATE));

			}
		} catch (Exception e) {
			// otherwise it will use what evauluatedDate came out to be in the
			// first place
		}

		// check the height and widths
		if (dayHeight == 0) {
			dayHeight = DAY_HEIGHT_DEFAULT;
		}

		if (dayWidth == 0) {
			dayWidth = DAY_WIDTH_DEFAULT;
		}

	}

	public int doEndTag() throws JspException {

		StringBuffer link = new StringBuffer();

		// evaluate all expressions
		initializeAttributes();

		// figure the week start

		if ("".equals(weekStart) || weekStart == null) {
			weekStart = WEEK_START_DEFAULT;
		}
		if (weekStart.toLowerCase().equals("sunday")) {
			calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		} else if (weekStart.toLowerCase().equals("monday")) {
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
		} else if (weekStart.toLowerCase().equals("tuesday")) {
			calendar.setFirstDayOfWeek(Calendar.TUESDAY);
		} else if (weekStart.toLowerCase().equals("wednesday")) {
			calendar.setFirstDayOfWeek(Calendar.WEDNESDAY);
		} else if (weekStart.toLowerCase().equals("thursday")) {
			calendar.setFirstDayOfWeek(Calendar.THURSDAY);
		} else if (weekStart.toLowerCase().equals("friday")) {
			calendar.setFirstDayOfWeek(Calendar.FRIDAY);
		} else if (weekStart.toLowerCase().equals("saturday")) {
			calendar.setFirstDayOfWeek(Calendar.SATURDAY);
		} else if (weekStart.toLowerCase().equals("today")) {
			calendar.setFirstDayOfWeek(Calendar.getInstance().get(
					Calendar.DAY_OF_WEEK));
		}

		decoratorObject.setCalendar(calendar);
		decoratorObject.setPageContext(pageContext);
		decoratorObject.setCalendar(startCalendar);
		decoratorObject.setStart(startCalendar);
		decoratorObject.setEnd(endCalendar);
		decoratorObject.initializeCalendar();

		String evaluatedRequestURI = (String) ExpressionUtil.evalNotNull(
				"calendartag", "calendar", requestURI, String.class, this,
				pageContext);
		if (evaluatedRequestURI != null) {
			requestURI = evaluatedRequestURI;
		}

		// check the requestURI
		if ("".equals(requestURI) || requestURI == null) {
			// if the user didn't specify one, this might work
			requestURI = ((HttpServletRequest) pageContext.getRequest())
					.getRequestURI();
		}

		link.append(requestURI);

		Enumeration parameterEnumeration = pageContext.getRequest()
				.getParameterNames();
		while (parameterEnumeration.hasMoreElements()) {
			String name = (String) parameterEnumeration.nextElement();
			String value = pageContext.getRequest().getParameter(name);

			if (!(id + "date").equals(name)) {
				link.append("&" + name + "=" + value);
			}
		}
		link.append("&" + id + "date=");
		// change the first & with ?
		int firstDelim = link.indexOf("&");
		link.replace(firstDelim, firstDelim + 1, "?");

		StringBuffer endTag = new StringBuffer();

		// start the table
		endTag.append("<table cellpadding=\"1\" border=\"0\" class=\""
				+ cssPrefix + TABLE_STYLE + "\"");
		if (!"".equals(id) && id != null) {
			endTag.append(" id=\"" + id + "\"");
		}
		endTag.append(">\r\n  <tr>\r\n");

		Cashout[] cashouts = getCashouts(startCalendar.getTime(),
				endCalendar.getTime());

		try {
			cashouts = cm.getCashoutsByDates(startCalendar.getTime(),
					endCalendar.getTime());
		} catch (Exception e) {
		}

		// draw the header stuff
		if (showPreviousNextLinksBoolean.booleanValue()) {

			// used determine the day that that previousl link should direct to
			int dateDiff = 0;
			if (!singleMonth) {
				// calculate the date difference only if neccessary
				dateDiff = CalendarTagUtil.differenceInDays(startCalendar,
						endCalendar) + 1;
			}

			Calendar newCalendar = (Calendar) calendar.clone();

			if (!singleMonth) {
				newCalendar.set(Calendar.DATE, newCalendar.get(Calendar.DATE)
						- dateDiff);
			} else {
				newCalendar.add(Calendar.MONTH, -1);
			}

			endTag.append("    <td colspan=\"1\" class=\"" + cssPrefix
					+ PREVIOUS_LINK_STYLE + "\" >");
			endTag.append(decoratorObject.getPreviousLink(
					link + DATE_FORMAT.format(newCalendar.getTime()),
					printParams()));

			endTag.append("</td>\r\n");

			endTag.append("    <td colspan=\"5\" class=\"" + cssPrefix
					+ TITLE_STYLE + "\" >");
			endTag.append(decoratorObject.getCalendarTitle());
			endTag.append("</td>\r\n");

			newCalendar = (Calendar) calendar.clone();
			if (!singleMonth) {
				newCalendar.set(Calendar.DATE, newCalendar.get(Calendar.DATE)
						+ dateDiff);
			} else {
				newCalendar.add(Calendar.MONTH, 1);
			}

			endTag.append("    <td colspan=\"1\" class=\"" + cssPrefix
					+ NEXT_LINK_STYLE + "\" >");
			if (newCalendar.getTime().compareTo(
					Calendar.getInstance().getTime()) < 0) {
				endTag.append(decoratorObject.getNextLink(
						link + DATE_FORMAT.format(newCalendar.getTime()),
						printParams()));

			}
			endTag.append("</td>\r\n");

		} else {
			// just draw the header, with 7 colspan
			endTag.append("    <td colspan=\"7\" class=\"" + cssPrefix
					+ TITLE_STYLE + "\" >");
			endTag.append(decoratorObject.getCalendarTitle());
			endTag.append("</td>\r\n");
		}

		endTag.append("  </tr>\r\n");

		endTag.append("  <tr>\r\n");
		// draw each weekday abbreviation with the decorator
		for (int i = 0; i < Calendar.DAY_OF_WEEK; i++) {
			int day = i + calendar.getFirstDayOfWeek();
			if (day > 7) {
				day = day - 7;
			}

			endTag.append("<td class=\"" + cssPrefix + WEEKDAY_STYLE + "\">"
					+ decoratorObject.getWeekdayTitle(day) + "</td>\r\n");
		}
		endTag.append("  </tr>\r\n");

		endTag.append("  <tr>\r\n");

		// start drawing the days

		int emptyDays;
		int column = 0;

		// count the number of empty days
		int firstDayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);
		if (calendar.getFirstDayOfWeek() > firstDayOfWeek) {
			emptyDays = firstDayOfWeek - calendar.getFirstDayOfWeek() + 7;
		} else {
			emptyDays = firstDayOfWeek - calendar.getFirstDayOfWeek();
		}

		// draw each empty day
		for (int i = 0; i < emptyDays; i++) {
			endTag.append("    <td class=\"" + cssPrefix + EMPTY_DAY_STYLE
					+ "\" height=\"" + dayHeight + "\" width=\"" + dayWidth
					+ "\">" + decoratorObject.getEmptyDay() + "</td>\r\n");
			column++;
		}

		Calendar iteratingDate = (Calendar) startCalendar.clone();
		boolean isOddMonth = true;
		while (!iteratingDate.after(endCalendar)) {

			decoratorObject.setCalendar(iteratingDate);

			if (column == 0) {
				endTag.append("  <tr>\r\n");
			}

			endTag.append("    <td class=\""
					+ cssPrefix
					+ decoratorObject.getDayStyleClass(isOddMonth,
							CalendarTagUtil.isSameDay(calendar, iteratingDate))
					+ "\" height=\"" + dayHeight + "\" width=\"" + dayWidth
					+ "\">");
			endTag.append(decoratorObject.getDay(link
					+ DATE_FORMAT.format(iteratingDate.getTime())));
			try {
				endTag.append(decoratorObject.printCashout(cashouts,
						iteratingDate, getDaily().booleanValue(), getDrop()
								.booleanValue(), getPayouts().booleanValue(),
						getBartenders().booleanValue(), getTips()
								.booleanValue(), getAmtotal().booleanValue(),
						getPmtotal().booleanValue()));
			} catch (Exception e) {
				endTag.append(e.toString());
			}
			endTag.append("</td>\r\n");
			// increment the column and end it if neccessary
			column++;
			if (column == Calendar.DAY_OF_WEEK) {
				endTag.append("  </tr>\r\n");
				column = 0;
			}

			// check to see if we're changing months & or years
			if (iteratingDate.getActualMaximum(Calendar.DATE) == iteratingDate
					.get(Calendar.DATE)) {
				if (isOddMonth) {
					isOddMonth = false;
				} else {
					isOddMonth = true;
				}
			}

			// increment the date, Calendar wraps it month and/or year if needed
			iteratingDate.set(Calendar.DATE,
					iteratingDate.get(Calendar.DATE) + 1);

		}

		while (column < Calendar.DAY_OF_WEEK && column > 0) {
			endTag.append("    <td class=\"" + cssPrefix + EMPTY_DAY_STYLE
					+ "\" height=\"" + dayHeight + "\" width=\"" + dayWidth
					+ "\">" + decoratorObject.getEmptyDay() + "</td>\r\n");
			column++;
		}

		endTag.append("  </tr>\r\n");
		endTag.append("</table>\r\n");

		try {
			this.pageContext.getOut().print(endTag.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			throw new JspException(e);
		}

		// clean up
		release();

		return Tag.EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getDayHeight() {
		return dayHeight;
	}

	public void setDayHeight(int dayHeight) {
		this.dayHeight = dayHeight;
	}

	public int getDayWidth() {
		return dayWidth;
	}

	public void setDayWidth(int dayWidth) {
		this.dayWidth = dayWidth;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getShowPreviousNextLinks() {
		return showPreviousNextLinks;
	}

	public void setShowPreviousNextLinks(String showPreviousNextLinks) {
		this.showPreviousNextLinks = showPreviousNextLinks;
	}

	public String getDecorator() {
		return decorator;
	}

	public void setDecorator(String decorator) {
		this.decorator = decorator;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getWeekStart() {
		return weekStart;
	}

	public void setWeekStart(String weekStart) {
		this.weekStart = weekStart;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getCssPrefix() {
		return cssPrefix;
	}

	public void setCssPrefix(String cssPrefix) {
		this.cssPrefix = cssPrefix;
	}

	public Boolean getDrop() {
		return drop;
	}

	public void setDrop(Boolean b) {
		this.drop = b;
	}

	public Boolean getPayouts() {
		return payouts;
	}

	public void setPayouts(Boolean b) {
		this.payouts = b;
	}

	public Boolean getBartenders() {
		return bartenders;
	}

	public void setBartenders(Boolean b) {
		this.bartenders = b;
	}

	public Boolean getAmtotal() {
		return amtotal;
	}

	public void setAmtotal(Boolean b) {
		this.amtotal = b;
	}

	public Boolean getPmtotal() {
		return pmtotal;
	}

	public void setPmtotal(Boolean b) {
		this.pmtotal = b;
	}

	public Boolean getDaily() {
		return daily;
	}

	public void setDaily(Boolean b) {
		this.daily = b;
	}

	public Boolean getTips() {
		return tips;
	}

	public void setTips(Boolean b) {
		this.tips = b;
	}

	public Boolean getCashbox() {
		return cashbox;
	}

	public void setCashbox(Boolean b) {
		this.cashbox = b;
	}

	/**
	 * Ensures everything is reset
	 */
	public void release() {
		day = 0;
		month = 0;
		year = 0;
		startCalendar = null;
		endCalendar = null;
		calendar = null;
		showPreviousNextLinks = null;
		decoratorObject = null;
		todayCalendar = new GregorianCalendar();
		date = null;
		endDate = null;
		startDate = null;
		decorator = null;
		requestURI = "";
		dayHeight = 0;
		dayWidth = 0;
		id = null;
		cssPrefix = null;
		weekStart = null;
		singleMonth = false;
		drop = new Boolean(false);
		cashbox = new Boolean(false);
		payouts = new Boolean(false);
		bartenders = new Boolean(false);
		daily = new Boolean(false);
		tips = new Boolean(false);
		amtotal = new Boolean(false);
		pmtotal = new Boolean(false);
	}
}
