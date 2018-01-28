package me.leetcode.array;

/**
 * In LOL world, there is a hero called Teemo and his attacking can make his
 * enemy Ashe be in poisoned condition. Now, given the Teemo's attacking
 * ascending time series towards Ashe and the poisoning time duration per
 * Teemo's attacking, you need to output the total time that Ashe is in poisoned
 * condition.
 * 
 * You may assume that Teemo attacks at the very beginning of a specific time
 * point, and makes Ashe be in poisoned condition immediately.
 * 
 * @author Administrator
 *
 */
public class TeemoAttacking495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
	int allTime = duration;
	int interval = 0;
	if (timeSeries.length == 0) {
	    return 0;
	} else if (timeSeries.length == 1) {
	    return duration;
	} else {
	    for (int i = 1; i < timeSeries.length; i++) {
		interval = timeSeries[i] - timeSeries[i - 1];
		if (interval < duration) {
		    allTime += interval;
		} else {
		    allTime += duration;
		}
	    }
	    return allTime;
	}
    }
}
