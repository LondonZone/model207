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

public class MainActivity extends Activity {

	private PatientsListAdapter adapter;
	private ListView mPatientsList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPatientsList = (ListView) findViewById(R.id.patients_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
							patientActivity.putExtra("id",
							String.valueOf(position));
							startActivity(patientActivity);
				}

			});
		} else {
			// Update with new list
			adapter.setPatients(AppState.getPatientsList());
			adapter.notifyDataSetChanged();
		}
	}
}
