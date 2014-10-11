package global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import activities.LoginActivity;
import android.app.Application;
import android.content.Intent;
import classes.Nurse;
import classes.Patient;
import classes.Physician;
import classes.User;

public class AppState extends Application {

	private static boolean loggedIn;
	private static User<?> currentUser;

	private static Map<String, Nurse> nurses;
	private static Map<String, Physician> physicians;
	private static Map<String, Patient> patients;

	@Override
	public void onCreate() {
		AppState.loggedIn = false;

		AppState.nurses = new HashMap<String, Nurse>();
		AppState.physicians = new HashMap<String, Physician>();
		AppState.patients = new HashMap<String, Patient>();

		// Redirect to login activity if not logged in
		if (!isLoggedIn()) {
			Intent login = new Intent(getBaseContext(), LoginActivity.class);
			login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(login);
		}
	}

	public static boolean isLoggedIn() {
		return loggedIn;
	}

	public static void setLoggedIn(boolean loggedIn) {
		AppState.loggedIn = loggedIn;
	}

	public static User<?> getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User<?> currentUser) {
		AppState.currentUser = currentUser;
	}

	public static Map<String, Nurse> getNurses() {
		return nurses;
	}

	public static void setNurses(Map<String, Nurse> nurses) {
		AppState.nurses = nurses;
	}

	public static void addNurse(Nurse nurse) {
		AppState.nurses.put(nurse.getUsername(), nurse);
	}

	public static void removeNurse(Nurse nurse) {
		AppState.nurses.remove(nurse.getUsername());
	}

	public static boolean hasNurse(String username) {
		return AppState.nurses.containsKey(username);
	}

	public static Map<String, Physician> getPhysicians() {
		return physicians;
	}

	public static void setPhysicians(Map<String, Physician> physicians) {
		AppState.physicians = physicians;
	}

	public static void addPhysician(Physician physician) {
		AppState.physicians.put(physician.getUsername(), physician);
	}

	public static void removePhysician(Physician physician) {
		AppState.physicians.remove(physician.getUsername());
	}

	public static boolean hasPhysician(String username) {
		return AppState.physicians.containsKey(username);
	}

	public static Map<String, Patient> getPatients() {
		return patients;
	}

	public static List<Patient> getPatientsList() {
		return new ArrayList<Patient>(patients.values());
	}

	public static void setPatients(Map<String, Patient> patients) {
		AppState.patients = patients;
	}

	public static void addPatient(Patient patient) {
		AppState.patients.put(patient.getHealthCard(), patient);
	}

	public static void removePatient(Patient patient) {
		AppState.patients.remove(patient.getHealthCard());
	}

}
