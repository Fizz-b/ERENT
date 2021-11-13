package se.project.util;

public class DateUtils {
	// second to h:m:s
   public static String date(long l) {
	  
        long p1 = l % 60;
       long p2 = l / 60;
       long p3 = p2 % 60;
       p2 = p2 / 60;
       String s =Long.toString(p2)+":"+Long.toString(p3)+":"+Long.toString(p1);
      return s;
   }
} 
