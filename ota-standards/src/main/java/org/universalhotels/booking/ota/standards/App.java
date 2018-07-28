package org.universalhotels.booking.ota.standards;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Pruebas git
 *
 */
public class App {
    public static void main( String[] args ) {
        
    	LocalDateTime ts = LocalDateTime.parse("2018-07-29T10:55:00");
    	System.out.println("Base DateTime: " + ts);
    	
    	Duration rangeSize = Duration.ofMinutes(10);
    	System.out.println("Down : " + toMinutePoint(ts, rangeSize, true));
    	System.out.println("Up : " + toMinutePoint(ts, rangeSize, false));

    	
    }
    
    
    private static LocalDateTime toMinutePoint(final LocalDateTime datetime, Duration rangeSize, boolean down) {
    	LocalDateTime base = datetime.minusMinutes(datetime.getMinute());
    	long rangeMinutes = rangeSize.getSeconds() / 60;
    	
    	Stream<LocalDateTime> points = Stream.iterate(base, dt -> dt.plusMinutes(rangeMinutes))
    			.limit((60 + rangeMinutes) / rangeMinutes)
        		.filter(dt -> dt.isAfter(datetime))
    			;

    	if (down) {
    		return points.findFirst().map(dt -> dt.minusMinutes(rangeMinutes)).get();
    	} else {
    		return points.findFirst().map(dt -> dt.plusMinutes(rangeMinutes)).get();
    	}
    }
    
    
    
}
