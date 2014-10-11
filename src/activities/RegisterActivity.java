package activities;

import me.echeung.triage207.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterActivity extends Activity {

	private TextView mUsername;
	private TextView mPassword;
	private TextView mPasswordConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		mUsername = (TextView) findViewById(R.id.register_username);
		mPassword = (TextView) findViewById(R.id.register_password);
		mPasswordConfirm = (TextView) findViewById(R.id.register_password_confirm);

		mUsername.setText(getIntent().getStringExtra("USERNAME"));
		mPassword.setText(getIntent().getStringExtra("PASSWORD"));
	}

	public void register(View view) {

	}

	public void closeDialog(View view) {
		this.finish();
	}
}
