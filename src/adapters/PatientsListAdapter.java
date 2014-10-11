package adapters;

import java.util.List;

import me.echeung.triage207.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import classes.Patient;

public class PatientsListAdapter extends ArrayAdapter<Patient> {

	private final Context context;
	private List<Patient> patients;

	private TextView mNameView;
	private TextView mInfoView;

	public PatientsListAdapter(Context context, List<Patient> objects) {
		super(context, R.layout.patient_list_item, objects);
		this.context = context;
		this.patients = objects;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.patient_list_item, parent,
				false);

		Patient patient = patients.get(position);

		mNameView = (TextView) rowView.findViewById(R.id.list_item_name);
		mInfoView = (TextView) rowView.findViewById(R.id.list_item_info);

		// Show available machines and set the square's background colour
		// accordingly
		// int avail = lab.getAvail();
		// freeView.setText(String.valueOf(avail));
		//
		// if (avail == 0)
		// compsView.setBackgroundColor(context.getResources().getColor(
		// R.color.free_red));
		// else if (avail <= 5)
		// compsView.setBackgroundColor(context.getResources().getColor(
		// R.color.free_orange));
		// else
		// compsView.setBackgroundColor(context.getResources().getColor(
		// R.color.free_green));

		// Show patient name and info
		mNameView.setText(patient.getName());
		// statsView.setText(String.format(context.getString(R.string.stats),
		// lab.getTotal(), lab.getPercent()));

		return rowView;
	}
}
