/**
 * 
 */
package org.universalhotels.transfers.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilidades de tratamiento de fechas y horas.
 * 
 * @author adso
 *
 */
public class TimeUtils {

    public static LocalDateTime toPreviousMinutePoint(final LocalDateTime datetime, Duration rangeSize) {
    	return toPreviousMinutePoint(datetime, rangeSize, false);
    }	

    public static LocalDateTime toNextMinutePoint(final LocalDateTime datetime, Duration rangeSize) {
    	return toNextMinutePoint(datetime, rangeSize, false);
    }	
	
    public static LocalDateTime toPreviousMinutePoint(final LocalDateTime datetime, Duration rangeSize, boolean forcePrevious) {
    	return toMinutePoint(datetime, rangeSize, true, !forcePrevious);
    }	

    public static LocalDateTime toNextMinutePoint(final LocalDateTime datetime, Duration rangeSize, boolean forceNext) {
    	return toMinutePoint(datetime, rangeSize, false, !forceNext);
    }	

    private static LocalDateTime toMinutePoint(final LocalDateTime datetime, Duration rangeSize, boolean down, boolean keepIfEqualExists) {
    	LocalDateTime base = datetime.minusMinutes(datetime.getMinute());
    	long rangeMinutes = rangeSize.getSeconds() / 60;
    	
    	List<LocalDateTime> points = Stream.iterate(base, dt -> dt.plusMinutes(rangeMinutes))
    			.limit((60 + rangeMinutes) / rangeMinutes)
        		.filter(dt ->  dt.isEqual(datetime) || dt.isAfter(datetime))
        		.collect(Collectors.toList())
    			;

    	if (keepIfEqualExists) {
    		Optional<LocalDateTime> equal = points.stream().filter(dt -> dt.equals(datetime)).findFirst();
    		if (equal.isPresent()) {
    			return datetime;
    		}
    	}
    	
    	if (down) {
    		return points.stream().findFirst().map(dt -> dt.minusMinutes(rangeMinutes)).get();
    	} else {
    		return points.stream().filter(dt -> dt.isAfter(datetime)).findFirst().get();
    	}
    }
}
