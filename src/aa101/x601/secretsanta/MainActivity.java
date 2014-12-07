package aa101.x601.secretsanta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
			Karen, numAlice, numBob, numCarol, numDan, numErin, numFrank,
			numGena, numHeather, numJohn, numKaren;
	List<EditText> viewsList = Arrays.asList(Alice, Bob, Carol, Dan, Erin,
			Frank, Gena, Heather, John, Karen);
	int numPplTry;
	View vader;
	Animation vaderAnim;
	List<EditText> numNames = Arrays.asList(numAlice, numBob, numCarol, numDan,
			numErin, numFrank, numGena, numHeather, numJohn, numKaren);

	// List<String> actualNames = Arrays.asList("Alice", "Bob", "Carol", "Dan",
	// "Erin", "Frank", "Gena", "Heather", "John", "Karen");
	// List<Double> actualNums = Arrays.asList(0., 1., 2., 3., 4., 5., 6., 7.,
	// 8.,
	// 9.);
	// List<String> popNames;
	// List<String> pickedNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// EditText pointers for activity_main.xml
		numPpl = (EditText) findViewById(R.id.numPpl);
		numPpl.addTextChangedListener(this);
		Alice = (EditText) findViewById(R.id.name0);
		Alice.addTextChangedListener(this);
		Bob = (EditText) findViewById(R.id.name1);
		Bob.addTextChangedListener(this);
		Carol = (EditText) findViewById(R.id.name2);
		Carol.addTextChangedListener(this);
		Dan = (EditText) findViewById(R.id.name3);
		Dan.addTextChangedListener(this);
		Erin = (EditText) findViewById(R.id.name4);
		Erin.addTextChangedListener(this);
		Frank = (EditText) findViewById(R.id.name5);
		Frank.addTextChangedListener(this);
		Gena = (EditText) findViewById(R.id.name6);
		Gena.addTextChangedListener(this);
		Heather = (EditText) findViewById(R.id.name7);
		Heather.addTextChangedListener(this);
		John = (EditText) findViewById(R.id.name8);
		John.addTextChangedListener(this);
		Karen = (EditText) findViewById(R.id.name9);
		Karen.addTextChangedListener(this);

		numAlice = (EditText) findViewById(R.id.number0);
		numBob = (EditText) findViewById(R.id.number1);
		numCarol = (EditText) findViewById(R.id.number2);
		numDan = (EditText) findViewById(R.id.number3);
		numErin = (EditText) findViewById(R.id.number4);
		numFrank = (EditText) findViewById(R.id.number5);
		numGena = (EditText) findViewById(R.id.number6);
		numHeather = (EditText) findViewById(R.id.number7);
		numJohn = (EditText) findViewById(R.id.number8);
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
		int numPplTry = 1;
		try {
			numPplTry = Integer.parseInt(numPpl.getText().toString());
			if (numPplTry > 10) {
				numPplTry = 10;
				Toast.makeText(
						getApplicationContext(),
						"There cannot be more than 10 participants.\nNumber of participants has defaulted to 10.",
						Toast.LENGTH_LONG).show();
				// Log.d(TAG, "numPpl entered in try/if: " + numPplTry);
			} else if (numPplTry == Integer.parseInt("")) {
				numPplTry = 1;
			} else {
				numPplTry = Integer.parseInt(numPpl.getText().toString());
				// Log.d(TAG, "numPpl entered in try/else: " + numPplTry);
			}
		} catch (Exception e) {
			// Log.e(TAG,
			// "Number of participants user entry: Not a valid number",
			// e);
		}

		viewsList = Arrays.asList(Alice, Bob, Carol, Dan, Erin, Frank, Gena,
				Heather, John, Karen);
		numNames = Arrays.asList(numAlice, numBob, numCarol, numDan, numErin,
				numFrank, numGena, numHeather, numJohn, numKaren);

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
				numNames.get(x).setVisibility(View.GONE);
			} else if (x < numPplTry
					&& viewsList.get(x).getVisibility() != View.VISIBLE) {
				// Log.e(TAG, "3: valid range & not visible");
				// Log.d(TAG, "set visible; x:numPplTry: " + x + " , " +
				// numPplTry);
				viewsList.get(x).setVisibility(View.VISIBLE);
				numNames.get(x).setVisibility(View.VISIBLE);
			} else {
				// Log.e(TAG, "4: default; set GONE");
				viewsList.get(x).setVisibility(View.GONE);
				numNames.get(x).setVisibility(View.GONE);
			}
			// Log.d(TAG, "actualNames<" + x + ">: " + actualNames.get(x));
		}

		// Animate entry/exit of "finish" button
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
	}

	// After entering contacts name and starting new activity, enter contact
	// info (phone number or email address) and send out secret santas.

	public void enterContactsInfo(View v) {
		//
		// Log.d(TAG, "Set content view: contact info");
		//
		// // List<String> pickNames = Arrays.asList();
		// Log.d(TAG, "List copy numPpl check: " + numPplTry);
		// List<String> popNames = copyList(viewsList, numPplTry);
		// List<String> pickedNames = copyList(viewsList, numPplTry);
		// Log.d(TAG,
		// "size - pop; picked: " + popNames.size() + ","
		// + pickedNames.size());
		//
		// for (int y = 0; y < numPplTry; y++) {
		// Log.d(TAG, "Inside for loop");
		// Random randGen = new Random();
		// int currentRand = randGen.nextInt(numPplTry);
		// Log.d(TAG, "currentRand: " + currentRand);
		// }

		String pickedName;
		for (int i = 0; i < numPplTry; i++) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(Uri.fromParts("sms", numNames.get(i).getText()
					.toString(), null));
			String name = viewsList.get(i).getText().toString();
			if (i < (numPplTry - 1)) {
				pickedName = viewsList.get(i + 1).getText().toString();
			} else {
				pickedName = viewsList.get(0).getText().toString();
			}

			String textBody = "Happy Holidays " + name
					+ "!\nYou're Secret Santa pick is"+pickedName;
			intent.putExtra("sms_body", textBody);
			try {
				startActivity(intent);
			} catch (Exception ex) {
				Log.e(TAG, "Could not send message", ex);
				Toast.makeText(getApplicationContext(),
						"Error - text could not be sent.", Toast.LENGTH_LONG)
						.show();
			}
		}
	}
	//
	// public List<String> copyList(List<EditText> list, int numPplTry) {
	// List<String> copied = Arrays.asList();
	// for (int b = 0; b < numPplTry; b++) {
	// copied.add(list.get(b).getText().toString());
	// }
	// return copied;
	//
	// }
}
