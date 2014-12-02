package aa101.x601.secretsanta;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher {

	private static final String TAG = MainActivity.class.getSimpleName();
	EditText numPpl;
	List<String> names = Arrays.asList("Alice", "Bob", "Carol", "Dan", "Erin",
			"Frank", "Gena", "Heather");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		numPpl = (EditText) findViewById(R.id.numPpl);
		numPpl.addTextChangedListener(this);
	}

	// @Override
	public void onTextChanged() {
		// NO-OP; Necessary to implement afterTextChanged()
	}

	// @Override
	public void beforeTextChanged() {
		// NO-OP; Necessary to implement afterTextChanged()
	}

	// @Override
	public void afterTextChanged() {

		// Use try/catch to ensure numPpl input from user <= 20 and integer
		// Force to 20 if > 20
		Integer numPplInt = 0;
		Integer numPplTry = 0;
		try {
			numPplTry.parseInt(numPpl.getText().toString());
			if (numPplTry > 20) {
				numPplInt = 20;
				Toast.makeText(
						getApplicationContext(),
						"There cannot be more than 20 participants.\nNumber of participants has defaulted to 20.",
						Toast.LENGTH_LONG);
			} else {
				numPplInt = numPplTry.parseInt(numPpl.getText().toString());
			}
		} catch (Exception e) {
			Log.e(TAG, "Number of participants user entry: Not a valid number",
					e);
		}

		// For loop to assign to each list element a necessary input field
		for (int x = 0; x <= numPplInt; x++) {

		}

	}
}
