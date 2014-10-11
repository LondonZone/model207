package activities;

import me.echeung.triage207.R;
import android.app.Activity;
import android.os.Bundle;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		getIntent().getStringExtra("USERNAME");
		getIntent().getStringExtra("PASSWORD");
	}
}
