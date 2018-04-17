package com.hfswing.util;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormato extends Formatter {

	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer(1000);		
		buf.append(HFSUtil.formataDate("dd/MM/yyyy HH:mm:ss", new Date()) + " " + 
				rec.getLevel().toString() + ": " + formatMessage(rec) + "\n");		
		return buf.toString();
	}

}
