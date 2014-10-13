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

public class MainActivity extends Activity {

	private PatientsListAdapter adapter;

	private TextView mCurrentUser;
	private ListView mPatientsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCurrentUser = (TextView) findViewById(R.id.user);
		mPatientsList = (ListView) findViewById(R.id.patients_list);

		if (AppState.getCurrentUser() != null)
			mCurrentUser.setText(AppState.getCurrentUser().getUsername());

		updateAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void updateAdapter() {
		// Collections.sort(patients, new AlphabeticalComparator());

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

	public void signOut(View view) {
		AppState.setLoggedIn(false);
		AppState.setCurrentUser(null);
		startActivity(new Intent(this, LoginActivity.class));
	}

	public void addPatient(View view) {
		startActivity(new Intent(this, NewPatientActivity.class));
	}
}
