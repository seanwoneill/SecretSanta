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
			Karen, infoAlice, infoBob, infoCarol, infoDan, infoErin, infoFrank,
			infoGena, infoHeather, infoJohn, infoKaren, numAlice, numBob,
			numCarol, numDan, numErin, numFrank, numGena, numHeather, numJohn,
			numKaren;
	List<EditText> viewsList = Arrays.asList(Alice, Bob, Carol, Dan, Erin,
			Frank, Gena, Heather, John, Karen);
	int numPplTry;
	View vader;
	Animation vaderAnim;
	List<String> actualNames = Arrays.asList("Alice", "Bob", "Carol", "Dan",
			"Erin", "Frank", "Gena", "Heather", "John", "Karen");
	List<EditText> infoNames = Arrays.asList(infoAlice, infoBob, infoCarol,
			infoDan, infoErin, infoFrank, infoGena, infoHeather, infoJohn,
			infoKaren);

	List<EditText> numNames = Arrays.asList(numAlice, numBob, numCarol, numDan,
			numErin, numFrank, numGena, numHeather, numJohn, numKaren);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// EditText pointers for activity_main.xml
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

		// EditText pointers for contact_info.xml
		infoAlice = (EditText) findViewById(R.id.name0);
		numAlice = (EditText) findViewById(R.id.number0);
		infoBob = (EditText) findViewById(R.id.name1);
		numBob = (EditText) findViewById(R.id.number1);
		infoCarol = (EditText) findViewById(R.id.name2);
		numCarol = (EditText) findViewById(R.id.number2);
		infoDan = (EditText) findViewById(R.id.name3);
		numDan = (EditText) findViewById(R.id.number3);
		infoErin = (EditText) findViewById(R.id.name4);
		numErin = (EditText) findViewById(R.id.number4);
		infoFrank = (EditText) findViewById(R.id.name5);
		numFrank = (EditText) findViewById(R.id.number5);
		infoGena = (EditText) findViewById(R.id.name6);
		numGena = (EditText) findViewById(R.id.number6);
		infoHeather = (EditText) findViewById(R.id.name7);
		numHeather = (EditText) findViewById(R.id.number7);
		infoJohn = (EditText) findViewById(R.id.name8);
		numJohn = (EditText) findViewById(R.id.number8);
		infoKaren = (EditText) findViewById(R.id.name9);
		numKaren = (EditText) findViewById(R.id.number9);
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
		// int numPplTry = 0;
		try {
			numPplTry = Integer.parseInt(numPpl.getText().toString());
			if (numPplTry > 10) {
				numPplTry = 10;
				Toast.makeText(
						getApplicationContext(),
						"There cannot be more than 10 participants.\nNumber of participants has defaulted to 10.",
						Toast.LENGTH_LONG).show();
				// Log.d(TAG, "numPpl entered in try/if: " + numPplTry);
			} else {
				numPplTry = Integer.parseInt(numPpl.getText().toString());
				// Log.d(TAG, "numPpl entered in try/else: " + numPplTry);
			}
		} catch (Exception e) {
			Log.e(TAG, "Number of participants user entry: Not a valid number",
					e);
		}

		viewsList = Arrays.asList(Alice, Bob, Carol, Dan, Erin, Frank, Gena,
				Heather, John, Karen);

		// Iterate over list of EditText fields to set which are visible
		// based on user input of number of participants (numPplTry).
		for (int x = 1; x < viewsList.size(); x++) {
			if (x < numPplTry
					&& viewsList.get(x).getVisibility() == View.VISIBLE) {
				// Log.e(TAG, "1: valid range & visible");
				continue;
			} else if (x >= numPplTry
					&& viewsList.get(x).getVisibility() == View.VISIBLE) {
				// Log.e(TAG, "2: invalid range & visible");
				viewsList.get(x).setVisibility(View.GONE);
			} else if (x < numPplTry
					&& viewsList.get(x).getVisibility() != View.VISIBLE) {
				// Log.e(TAG, "3: valid range & not visible");
				// Log.d(TAG, "set visible; x:numPplTry: " + x + " , " +
				// numPplTry);
				viewsList.get(x).setVisibility(View.VISIBLE);
			} else {
				// Log.e(TAG, "4: default; set GONE");
				viewsList.get(x).setVisibility(View.GONE);
			}
			// Log.d(TAG, "actualNames<" + x + ">: " + actualNames.get(x));
		}

		// Log.d(TAG, "Before first 'if'; numPplTry: " + numPplTry);
		vader = findViewById(R.id.enterButton);
		if (numPplTry < 10 && numPplTry > 0) {
			Boolean lastFieldFilled = viewsList.get(numPplTry - 1).getText()
					.toString().trim().length() != 0;
			if (lastFieldFilled && vader.getVisibility() != View.VISIBLE) {
				// Log.e(TAG, "animation - visible");
				vader.setVisibility(View.VISIBLE);
				vaderAnim = AnimationUtils.makeInAnimation(this, true);
				vader.startAnimation(vaderAnim);
			} else if (!lastFieldFilled
					&& vader.getVisibility() == View.VISIBLE) {
				// Log.e(TAG,
				// "animation - invisible; lastFieldFilled: "
				// + lastFieldFilled + " ,vader vis.:"
				// + vader.getVisibility());
				vaderAnim = AnimationUtils.makeOutAnimation(this, true);
				vader.setVisibility(View.INVISIBLE);
				vader.startAnimation(vaderAnim);
			}
		}
		
		//On click method code to set (in)visibility for EditText field in Contact_Info.xml
		List<EditText> infoNames = Arrays.asList(infoAlice, infoBob, infoCarol,
				infoDan, infoErin, infoFrank, infoGena, infoHeather, infoJohn,
				infoKaren);
		List<EditText> numNames = Arrays.asList(numAlice, numBob, numCarol,
				numDan, numErin, numFrank, numGena, numHeather, numJohn,
				numKaren);
		
		// Set visibility of all name fields in Contact Info .xml
		for (int a = 1; a < numPplTry && a < infoNames.size(); a++) {
//			Log.d(TAG, "infoNames<" + a + ">: "
//					+ infoNames.get(a).getText().toString());
//			Log.d(TAG, "infoNames<" + a + ">: "
//					+ infoNames.get(a).getText().toString());
			infoNames.get(a).setVisibility(View.VISIBLE);
			numNames.get(a).setVisibility(View.VISIBLE);
			}
	}

	// After entering contacts name and starting new activity, enter contact
	// info (phone number or email address) and send out secret santas.

	public void enterContactsInfo(View v) {


		Log.d(TAG, "Set content view: contact info");
		setContentView(R.layout.contact_info);

		/*
		 * for (int y = 0; y < actualNames.size() && y < numPplTry; y++) { //
		 * String fieldFilled = viewsList.get(y).getText().toString(); //
		 * Log.d(TAG, "fieldFilled: " + fieldFilled); // actualNames.set(y,
		 * fieldFilled); // Log.d(TAG, "actualNames(" + y + "): " +
		 * actualNames.get(y)); EditText nameField = infoNames.get(y); nameField
		 * = viewsList.get(y); }
		 */
	}
}
