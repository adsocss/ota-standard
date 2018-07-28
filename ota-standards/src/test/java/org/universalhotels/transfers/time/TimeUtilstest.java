/**
 * 
 */
package org.universalhotels.transfers.time;

import java.time.Duration;
import java.time.LocalDateTime;

import junit.framework.TestCase;
import static org.universalhotels.transfers.time.TimeUtils.*;

/**
 * @author adso
 *
 */
public class TimeUtilstest extends TestCase {

	/**
	 * Test method for {@link org.universalhotels.transfers.time.TimeUtils#toPreviousMinutePoint(java.time.LocalDateTime, java.time.Duration)}.
	 */
	public void testToPreviousMinutePointLocalDateTimeDuration() {
		LocalDateTime datetime = LocalDateTime.parse("2018-07-29T10:55:00");
		Duration rangeSize = Duration.ofMinutes(15);
		LocalDateTime adjusted = LocalDateTime.parse("2018-07-29T10:45:00");
		
		assertTrue(toPreviousMinutePoint(datetime, rangeSize).isEqual(adjusted));
		
		datetime = LocalDateTime.parse("2018-07-29T10:15:00");
		assertTrue(toPreviousMinutePoint(datetime, rangeSize).isEqual(datetime));
	}

	/**
	 * Test method for {@link org.universalhotels.transfers.time.TimeUtils#toNextMinutePoint(java.time.LocalDateTime, java.time.Duration)}.
	 */
	public void testToNextMinutePointLocalDateTimeDuration() {
		LocalDateTime datetime = LocalDateTime.parse("2018-07-29T10:55:00");
		Duration rangeSize = Duration.ofMinutes(15);
		LocalDateTime adjusted = LocalDateTime.parse("2018-07-29T11:00:00");
		
		assertTrue(toNextMinutePoint(datetime, rangeSize).isEqual(adjusted));
		
		datetime = LocalDateTime.parse("2018-07-29T10:15:00");
		assertTrue(toNextMinutePoint(datetime, rangeSize).isEqual(datetime));
	}

	/**
	 * Test method for {@link org.universalhotels.transfers.time.TimeUtils#toPreviousMinutePoint(java.time.LocalDateTime, java.time.Duration, boolean)}.
	 */
	public void testToPreviousMinutePointLocalDateTimeDurationBoolean() {
		boolean forcePrevious = true;

		LocalDateTime datetime = LocalDateTime.parse("2018-07-29T10:55:00");
		Duration rangeSize = Duration.ofMinutes(15);
		LocalDateTime adjusted = LocalDateTime.parse("2018-07-29T10:45:00");
		
		assertTrue(toPreviousMinutePoint(datetime, rangeSize, forcePrevious).isEqual(adjusted));
		
		datetime = LocalDateTime.parse("2018-07-29T10:15:00");
		assertFalse(toPreviousMinutePoint(datetime, rangeSize, forcePrevious).isEqual(datetime));
	}

	/**
	 * Test method for {@link org.universalhotels.transfers.time.TimeUtils#toNextMinutePoint(java.time.LocalDateTime, java.time.Duration, boolean)}.
	 */
	public void testToNextMinutePointLocalDateTimeDurationBoolean() {
		boolean forceNext = true;

		LocalDateTime datetime = LocalDateTime.parse("2018-07-29T10:55:00");
		Duration rangeSize = Duration.ofMinutes(15);
		LocalDateTime adjusted = LocalDateTime.parse("2018-07-29T11:00:00");
		
		assertTrue(toNextMinutePoint(datetime, rangeSize, forceNext).isEqual(adjusted));
		
		datetime = LocalDateTime.parse("2018-07-29T10:15:00");
		assertFalse(toNextMinutePoint(datetime, rangeSize, forceNext).isEqual(datetime));
	}

}
