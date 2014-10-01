package activities;

import global.AppState;
import me.echeung.triage207.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import classes.Nurse;
import classes.Physician;

public class LoginActivity extends Activity {

	private TextView mLoginMessage;
	private EditText mUsername;
	private EditText mPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mLoginMessage = (TextView) findViewById(R.id.login_message);
		mUsername = (EditText) findViewById(R.id.login_username);
		mPassword = (EditText) findViewById(R.id.login_password);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Prevents users from going back to the main activity if not logged in */
	@Override
	public void onBackPressed() {
	}

	/**
	 * Handles the "Log in" button in the login form.
	 *
	 * @param view
	 *            The "Log in" button.
	 */
	public void logIn(View view) {
		// Get the values from the form
		final String username = mUsername.getText().toString();
		final String password = mPassword.getText().toString();

		// Check that the username is valid
		if (AppState.hasNurse(username) || AppState.hasPhysician(username)) {
			// Sign in the Nurse or Physician if the password is correct
			if (AppState.hasNurse(username)) {
				Nurse nurse = AppState.getNurses().get(username);

				if (nurse.getPassword().equals(password)) {
					AppState.setLoggedIn(true);

					// Set the current user text in the main activity
					AppState.setCurrentUser(AppState.getNurses().get(username));

					this.finish();
				}
			} else if (AppState.hasPhysician(username)) {
				Physician physician = AppState.getPhysicians().get(username);

				if (physician.getPassword().equals(password)) {
					AppState.setLoggedIn(true);

					// Set the current user text in the main activity
					AppState.setCurrentUser(AppState.getPhysicians().get(
							username));

					this.finish();
				}
			} else {
				mLoginMessage.setText(R.string.login_error_pass);
			}
		} else {
			mLoginMessage.setText(R.string.login_error_user);
		}
	}
}
