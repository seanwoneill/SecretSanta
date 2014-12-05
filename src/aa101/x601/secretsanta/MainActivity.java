package aa101.x601.secretsanta;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher {

	private static final String TAG = MainActivity.class.getSimpleName();
	EditText numPpl, Alice, Bob, Carol, Dan, Erin, Frank, Gena, Heather, John,
			Karen;
	View vader;
	Animation vaderAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		numPpl = (EditText) findViewById(R.id.numPpl);
		numPpl.addTextChangedListener(this);
		Alice = (EditText) findViewById(R.id.Alice);
		Alice.addTextChangedListener(this);
		Bob = (EditText) findViewById(R.id.Bob);
		Bob.addTextChangedListener(this);
		Carol = (EditText) findViewById(R.id.Carol);
		Carol.addTextChangedListener(this);
		Dan = (EditText) findViewById(R.id.Dan);
		Dan.addTextChangedListener(this);
		Erin = (EditText) findViewById(R.id.Erin);
		Erin.addTextChangedListener(this);
		Frank = (EditText) findViewById(R.id.Frank);
		Frank.addTextChangedListener(this);
		Gena = (EditText) findViewById(R.id.Gena);
		Gena.addTextChangedListener(this);
		Heather = (EditText) findViewById(R.id.Heather);
		Heather.addTextChangedListener(this);
		John = (EditText) findViewById(R.id.John);
		John.addTextChangedListener(this);
		Karen = (EditText) findViewById(R.id.Karen);
		Karen.addTextChangedListener(this);
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

		// Use try/catch to ensure numPpl input from user <= 10 and integer
		// Force to 10 if > 10
		int numPplTry = 0;
		try {
			numPplTry = Integer.parseInt(numPpl.getText().toString());
			if (numPplTry > 10) {
				numPplTry = 10;
				Toast.makeText(
						getApplicationContext(),
						"There cannot be more than 10 participants.\nNumber of participants has defaulted to 10.",
						Toast.LENGTH_LONG).show();
				Log.d(TAG, "numPpl entered in try/if: " + numPpl);
			} else {
				numPplTry = Integer.parseInt(numPpl.getText().toString());
				Log.d(TAG, "numPpl entered in try/else: " + numPpl);
			}
		} catch (Exception e) {
			Log.e(TAG, "Number of participants user entry: Not a valid number",
					e);
		}

		List<EditText> viewsList = Arrays.asList(Alice, Bob, Carol, Dan, Erin,
				Frank, Gena, Heather, John, Karen);

		// Iterate over list of EditText fields to set which are visible
		// based on user input of number of participants (numPplTry).
		for (int x = 1; x < viewsList.size(); x++) {
			if (x < numPplTry
					&& viewsList.get(x).getVisibility() == View.VISIBLE) {
				Log.e(TAG, "1: valid range & visible");
				continue;
			} else if (x >= numPplTry
					&& viewsList.get(x).getVisibility() == View.VISIBLE) {
				Log.e(TAG, "2: invalid range & visible");
				viewsList.get(x).setVisibility(View.GONE);
			} else if (x < numPplTry
					&& viewsList.get(x).getVisibility() != View.VISIBLE) {
				Log.e(TAG, "3: valid range & not visible");
				Log.d(TAG, "set visible; x:numPplTry: " + x + " , " + numPplTry);
				viewsList.get(x).setVisibility(View.VISIBLE);
			} else {
				Log.e(TAG, "4: default; set GONE");
				viewsList.get(x).setVisibility(View.GONE);
			}
		}

		Log.d(TAG, "Before first 'if'; numPplTry: " + numPplTry);
		vader = findViewById(R.id.enterButton);
		if (numPplTry < 10 && numPplTry > 0) {
			Boolean lastFieldFilled = viewsList.get(numPplTry - 1).getText()
					.toString().trim().length() != 0;
			if (lastFieldFilled
					&& viewsList.get(numPplTry).getVisibility() != View.VISIBLE) {
				Log.e(TAG, "animation - visible");
				vader.setVisibility(View.VISIBLE);
				vaderAnim = AnimationUtils.makeInAnimation(this, true);
				vader.startAnimation(vaderAnim);
			} else if (lastFieldFilled
					&& viewsList.get(numPplTry).getVisibility() == View.VISIBLE) {
				Log.e(TAG, "animation - invisible");
				vaderAnim = AnimationUtils.makeOutAnimation(this, true);
				vader.setVisibility(View.INVISIBLE);
				vader.startAnimation(vaderAnim);
			} else {
				vader.setVisibility(View.INVISIBLE);
			}

		}
	}

	// After entering contacts name and starting new activity, enter contact
	// info (phone number or email address) and send out secret santas.
	public void enterContactsInfo() {
	}
}
