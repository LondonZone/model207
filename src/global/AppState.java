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

}
