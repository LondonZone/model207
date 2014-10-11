package comparators;

import java.util.Comparator;

import classes.Patient;

public class UrgencyComparator implements Comparator<Patient> {

	@Override
	public int compare(Patient lhs, Patient rhs) {
		return lhs.getUrgency().compareTo(rhs.getUrgency());
	}

}
