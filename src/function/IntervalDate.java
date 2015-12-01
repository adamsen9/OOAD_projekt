package function;

import java.time.LocalDate;

public class IntervalDate implements Comparable<IntervalDate>{
	LocalDate date;
	boolean isStart;
	
	public IntervalDate(LocalDate date, boolean isStart){
		this.date = date;
		this.isStart = isStart;
	}
	
	@Override
	public int compareTo(IntervalDate cDate){
		int compVal = date.compareTo(cDate.date);
		if (compVal == 0){
			if (isStart && !cDate.isStart)
				compVal = 1;
			if (!isStart && cDate.isStart)
				compVal = -1;
		}
		return compVal;
	}
}