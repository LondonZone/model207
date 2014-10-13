package activities;

import global.AppState;
import me.echeung.triage207.R;
import adapters.PatientsListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import classes.Physician;
import classes.User;

public class MainActivity extends Activity {

	private PatientsListAdapter adapter;

	private TextView mCurrentUserType;
	private TextView mCurrentUser;
	private ListView mPatientsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCurrentUserType = (TextView) findViewById(R.id.logged_in_as);
		mCurrentUser = (TextView) findViewById(R.id.user);
		mPatientsList = (ListView) findViewById(R.id.patients_list);

		updateUserText();
		updateAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (AppState.getCurrentUser() instanceof Physician) {
			menu.findItem(R.id.action_new).setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;
		case R.id.action_new:
			startActivity(new Intent(this, NewPatientActivity.class));
			return true;
		case R.id.action_save:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		invalidateOptionsMenu();

		updateUserText();

		// Re-populate the patients list when the activity resumes
		updateAdapter();

		// Save file
		// savePatients();
	}

	public void updateAdapter() {
		if (adapter == null) {
			// Initialize adapter for the list of patients
			adapter = new PatientsListAdapter(this, AppState.getPatientsList());
			mPatientsList.setAdapter(adapter);

			mPatientsList.setEmptyView(findViewById(R.id.empty_list));

			mPatientsList.setClickable(true);
			mPatientsList
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							Intent patientActivity = new Intent(
									getBaseContext(), PatientActivity.class);
							patientActivity.putExtra("PATIENT", AppState
									.getPatientsList().get(position)
									.getHealthCard());
							startActivity(patientActivity);
						}

					});
		} else {
			// Update with new list
			adapter.setPatients(AppState.getPatientsList());
			adapter.notifyDataSetChanged();
		}
	}

	public void updateUserText() {
		User<?> currentUser = AppState.getCurrentUser();

		if (currentUser != null) {
			mCurrentUserType.setText(String.format(
					"%s ",
					currentUser instanceof Physician ? this
							.getString(R.string.physician) : this
							.getString(R.string.nurse)));

			mCurrentUser.setText(currentUser.getUsername());
		}
	}

	public void signOut(View view) {
		AppState.setLoggedIn(false);
		AppState.setCurrentUser(null);
		startActivity(new Intent(this, LoginActivity.class));
	}

	public void addPatient(View view) {
		startActivity(new Intent(this, NewPatientActivity.class));
	}
}
