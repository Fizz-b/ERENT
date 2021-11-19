package se.project.util;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;


public class DateUtils {

  // second to h:m:s
  public static final DecimalFormat formatter = new DecimalFormat("###,###,### d");
  

  //String format = decimalFormat.format(123456789.123);
  public static final DateTimeFormatter format = DateTimeFormatter.ofPattern(
	      "yyyy-MM-dd HH:mm:ss");  // create date util

  public static String date(long l) {

    long p1 = l % 60;
    long p2 = l / 60;
    long p3 = p2 % 60;
    p2 = p2 / 60;
    String s = Long.toString(p2) + ":" + Long.toString(p3) + ":" + Long.toString(p1);
    return s;
  }
} 
