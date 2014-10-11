package classes;

import global.AppState;

public class Physician extends User<Physician> {

	public Physician(String username, String password) {
		super(username, password);
	}

	/**
	 * Records a Patient's Prescriptions.
	 *
	 * @param patient
	 *            A Patient with Prescriptions being recorded.
	 * @param name
	 *            The name of the Prescriptions object.
	 * @param instructions
	 *            The Prescriptions object's instructions.
	 */
	public void recordPrescription(Patient patient, String name,
			String instructions) {
		patient.addPrescription(new Prescription(name, instructions));
	}

	/**
	 * Parses the contents of fields and instantiates this Physician. It is then
	 * added to the ER database and returned.
	 *
	 * @param fields
	 *            An array of contents used to instantiate this Physician.
	 * @return The instantiated Physician.
	 */
	@Override
	public Physician scan(String[] fields) {
		Physician physician = new Physician(fields[0], fields[1]);
		AppState.addPhysician(physician);
		return physician;
	}

	/**
	 * Returns the String representation of this Physician, in CSV format.
	 *
	 * @return The String representation of this Physician, in CSV format.
	 */
	@Override
	public String toString() {
		return "p, " + super.toString();
	}
}
