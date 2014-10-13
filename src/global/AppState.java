package global;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.FileHelper;
import activities.LoginActivity;
import android.app.Application;
import android.content.Intent;
import classes.Nurse;
import classes.Patient;
import classes.Physician;
import classes.User;

import comparators.AlphabeticalComparator;
import comparators.UrgencyComparator;

public class AppState extends Application {

	private static boolean loggedIn;
	private static User<?> currentUser;

	private static Map<String, Nurse> nurses;
	private static Map<String, Physician> physicians;
	private static Map<String, Patient> patients;

	// root/data/data/me.echeung.model207/files/
	private static final String NURSES_FILENAME = "/nurses.txt";
	private FileHelper<Nurse> nursesFile;

	private static final String PHYSICIANS_FILENAME = "/physicians.txt";
	private FileHelper<Physician> physiciansFile;

	private static final String PATIENTS_FILENAME = "/patients.txt";
	private FileHelper<Patient> patientsFile;

	private static int patientsSort;

	@Override
	public void onCreate() {
		AppState.loggedIn = false;

		AppState.nurses = new HashMap<String, Nurse>();
		AppState.physicians = new HashMap<String, Physician>();
		AppState.patients = new HashMap<String, Patient>();

		patientsSort = 0;

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

	public static List<Nurse> getNursesList() {
		return new ArrayList<Nurse>(nurses.values());
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

	public static List<Physician> getPhysiciansList() {
		return new ArrayList<Physician>(physicians.values());
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
		List<Patient> patientsList = new ArrayList<Patient>(patients.values());

		if (AppState.getPatientsListSort() == 0)
			Collections.sort(patientsList, new AlphabeticalComparator());
		else
			Collections.sort(patientsList, new UrgencyComparator());

		return patientsList;
	}

	public static int getPatientsListSort() {
		return patientsSort;
	}

	public static void setPatientsListSort(int sort) {
		AppState.patientsSort = sort;
	}

	public static Patient getPatient(String healthCard) {
		return patients.get(healthCard);
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
