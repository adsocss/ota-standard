package org.universalhotels.booking.ota.standards;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Pruebas git
 *
 */
public class App {
    public static void main( String[] args ) {
        
    	LocalDateTime ts = LocalDateTime.parse("2018-07-29T10:55:00");
    	System.out.println("Base DateTime: " + ts);
    	
    	Duration rangeSize = Duration.ofMinutes(15);
    	System.out.println("Down : " + toMinutePoint(ts, rangeSize, true, true));
    	System.out.println("Up : " + toMinutePoint(ts, rangeSize, false, true));
    	
    }
    
    
    private static LocalDateTime toMinutePoint(final LocalDateTime datetime, Duration rangeSize, boolean down, boolean keep) {
    	LocalDateTime base = datetime.minusMinutes(datetime.getMinute());
    	long rangeMinutes = rangeSize.getSeconds() / 60;
    	
    	List<LocalDateTime> points = Stream.iterate(base, dt -> dt.plusMinutes(rangeMinutes))
    			.limit((60 + rangeMinutes) / rangeMinutes)
        		.filter(dt ->  dt.isEqual(datetime) || dt.isAfter(datetime))
        		.collect(Collectors.toList())
    			;

    	if (keep) {
    		Optional<LocalDateTime> equal = points.stream().filter(dt -> dt.equals(datetime)).findFirst();
    		if (equal.isPresent()) {
    			return datetime;
    		}
    	}
    	
    	if (down) {
    		return points.stream().findFirst().map(dt -> dt.minusMinutes(rangeMinutes)).get();
    	} else {
    		return points.stream().findFirst().get();
    	}
    }
    
    
    
}
