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
		// Dan = (EditText) findViewById(R.id.Dan);
		// Dan.addTextChangedListener(this);
		// Erin = (EditText) findViewById(R.id.Erin);
		// Erin.addTextChangedListener(this);
		// Frank = (EditText) findViewById(R.id.Frank);
		// Frank.addTextChangedListener(this);
		// Gena = (EditText) findViewById(R.id.Gena);
		// Gena.addTextChangedListener(this);
		// Heather = (EditText) findViewById(R.id.Heather);
		// Heather.addTextChangedListener(this);
		// John = (EditText) findViewById(R.id.John);
		// John.addTextChangedListener(this);
		// Karen = (EditText) findViewById(R.id.Karen);
		// Karen.addTextChangedListener(this);
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
				Log.d(TAG, "numPpl entered in try/if: " + numPpl);
			} else {
				numPplTry = Integer.parseInt(numPpl.getText().toString());
				Log.d(TAG, "numPpl entered in try/else: " + numPpl);
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
		// for (int x = 0; x <= numPplTry; x++) {
		// String stringRId = "R.id." + names.get(x).toString();
		// int namePointer = Integer.parseInt(stringRId);
		// Log.d(TAG, "namePointer: " + namePointer + ": R.id.namePointer: "
		// + namePointer);
		// textWatcherNames.add((EditText) findViewById(namePointer));
		// Log.d(TAG, "Most recent textWatcherNames linked list entry: "
		// + textWatcherNames.get(x));
		// textWatcherNames.get(x).addTextChangedListener(this);
		// }
		View viewAlice = findViewById(R.id.Alice);
		View viewBob = findViewById(R.id.Bob);
		View viewCarol = findViewById(R.id.Carol);
		List<View> viewsList = Arrays.asList(viewAlice, viewBob, viewCarol);

		int x;
		for (int x; x < viewsList.size(); x++) {
			Animation anim;
			Log.d(TAG, "viewsList(x): " + viewsList.get(x));
			Log.d(TAG, "viewsList.get(x).getVisibility(): "
					+ viewsList.get(x).getVisibility());
			if (viewsList.get(x).getVisibility() == View.VISIBLE) {
				return;
			} else if (viewsList.get(x - 1).getVisibility() == View.VISIBLE) {
				viewsList.get(x).setVisibility(View.VISIBLE);
				anim = AnimationUtils.makeInAnimation(this, true);
			} else {
				anim = AnimationUtils.makeOutAnimation(this, true);
				viewsList.get(x).setVisibility(View.INVISIBLE);
			}
			viewsList.get(x).startAnimation(anim);
		}
	}
}
