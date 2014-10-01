package global;

import java.util.Map;

import activities.LoginActivity;
import android.app.Application;
import android.content.Intent;
import classes.Nurse;
import classes.Physician;
import classes.User;

public class AppState extends Application {

	private static boolean loggedIn;
	private static User currentUser;

	private static Map<String, Nurse> nurses;
	private static Map<String, Physician> physicians;

	@Override
	public void onCreate() {
		AppState.loggedIn = false;

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

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
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

}
