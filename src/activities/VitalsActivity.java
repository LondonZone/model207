package activities;

import global.AppState;
import me.echeung.triage207.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import classes.Patient;

public class VitalsActivity extends Activity {

	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vitals);

		String healthCard = getIntent().getStringExtra("PATIENT");
		patient = AppState.getPatients().get(healthCard);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.vitals, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_accept:

			return true;
		case R.id.action_discard:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
