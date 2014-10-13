package activities;

import global.AppState;
import me.echeung.triage207.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import classes.Nurse;
import classes.Patient;

public class PatientActivity extends Activity {

	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient);

		String healthCard = getIntent().getStringExtra("PATIENT");
		patient = AppState.getPatients().get(healthCard);

		// Display name
		((TextView) findViewById(R.id.name)).setText(patient.getName());

		// Display date of birth
		((TextView) findViewById(R.id.dob)).setText(patient.getDob());

		// Display health card number
		((TextView) findViewById(R.id.healthCard)).setText(patient
				.getHealthCard());

		// Display arrival time
		((TextView) findViewById(R.id.arrival)).setText(patient
				.getArrivalTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.patient, menu);

		if (AppState.getCurrentUser() instanceof Nurse) {
			// Hide Physician-related actions if Nurse logged in
			menu.findItem(R.id.action_prescription).setVisible(false);
			((Button) findViewById(R.id.add_prescription))
					.setVisibility(View.GONE);
		} else {
			// Hide Nurse-related actions if Physician logged in
			menu.findItem(R.id.action_vitals).setVisible(false);
			menu.findItem(R.id.action_symptoms).setVisible(false);
			menu.findItem(R.id.action_doctor).setVisible(false);

			((Button) findViewById(R.id.add_vitals)).setVisibility(View.GONE);
			((Button) findViewById(R.id.add_symptoms)).setVisibility(View.GONE);
			((Button) findViewById(R.id.add_time)).setVisibility(View.GONE);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
