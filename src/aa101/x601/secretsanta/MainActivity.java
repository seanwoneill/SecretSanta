package aa101.x601.secretsanta;

import java.util.Arrays;
import java.util.LinkedList;
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
	LinkedList<EditText> textWatcherNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		numPpl = (EditText) findViewById(R.id.numPpl);
		numPpl.addTextChangedListener(this);
	}

	// @Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// NO-OP; Necessary to implement afterTextChanged()
	}

	// @Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// NO-OP; Necessary to implement afterTextChanged()
	}

	// @Override
	public void afterTextChanged(Editable s) {

		// Use try/catch to ensure numPpl input from user <= 20 and integer
		// Force to 20 if > 20
		int numPplTry = 0;
		try {
			numPplTry = Integer.parseInt(numPpl.getText().toString());
			System.in.read();
			if (numPplTry > 20) {
				numPplTry = 20;
				Toast.makeText(
						getApplicationContext(),
						"There cannot be more than 20 participants.\nNumber of participants has defaulted to 20.",
						Toast.LENGTH_LONG).show();
			} else {
				numPplTry = Integer.parseInt(numPpl.getText().toString());
			}
		} catch (Exception e) {
			Log.e(TAG, "Number of participants user entry: Not a valid number",
					e);
		}

		/*
		 * Use 'for' loop to: (1) Get integer address of EditText elements
		 * stored in memory. (2) Use that address to store EditText pointers in
		 * a linked list. (3) Add text changed listeners for each EditText
		 * element stored in the linked list.
		 */
		for (int x = 0; x <= numPplTry; x++) {
			String stringRId = "R.id." + names.get(x).toString();
			int namePointer = Integer.parseInt(stringRId);
			Log.d(TAG, "namePointer: " + namePointer + ": R.id.namePointer: "
					+ namePointer);
			textWatcherNames.add((EditText) findViewById(namePointer));
			Log.d(TAG, "Most recent textWatcherNames linked list entry: "
					+ textWatcherNames.get(x));
			textWatcherNames.get(x).addTextChangedListener(this);
		}
		Log.d(TAG,
				"textWatcherNames finel list length: "
						+ textWatcherNames.size());
	}
}
