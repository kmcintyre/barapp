package com.nwice.barapp.calendartag.decorator;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.UserManager;
import com.nwice.barapp.model.BarappUser;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.ShiftWorker;
import com.nwice.barapp.util.BarappUtil;


public class PrintHelp {
	
	private static Logger log = Logger.getLogger(PrintHelp.class);

	public static String printBartenders(Cashout co, boolean tips) {
		StringBuffer sb = new StringBuffer("");
		try {
ShiftWorker[] shiftWorkers = (ShiftWorker[])co.getShift().getShiftWorkers().toArray(new ShiftWorker[co.getShift().getShiftWorkers().size()]);

			for ( int i = 0; i < shiftWorkers.length; i++ ) {
				sb.append(printSW(shiftWorkers[i], tips));
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	public static String printSW(ShiftWorker swo, boolean tips)  {
		StringBuffer sb = new StringBuffer("");
		try {
			BarappUser uo = new UserManager().getUserById(swo.getBarappUser().getBarappUserId());
			if ( tips ) {
				sb.append( "$" + BarappUtil.doubleToString( swo.getTips()) + " ");
			}
			sb.append(uo.getFirstname() + " " + uo.getLastname().substring(0,1) + ".");
			sb.append("<br>");
		} catch (Exception e) {}
		return sb.toString();
	}

}
