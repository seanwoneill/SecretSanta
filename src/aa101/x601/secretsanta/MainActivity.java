package aa101.x601.secretsanta;

import java.util.Arrays;
import java.util.List;

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
			} else if (numPplTry == Integer.parseInt("")) {
				numPplTry = 1;
			} else {
				numPplTry = Integer.parseInt(numPpl.getText().toString());
			}
		} catch (Exception e) {
			Log.e(TAG, "Number of participants user entry: Not a valid number",
					e);
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
				continue;
			} else if (x >= numPplTry
					&& viewsList.get(x).getVisibility() == View.VISIBLE) {
				viewsList.get(x).setVisibility(View.GONE);
				numNames.get(x).setVisibility(View.GONE);
			} else if (x < numPplTry
					&& viewsList.get(x).getVisibility() != View.VISIBLE) {
				viewsList.get(x).setVisibility(View.VISIBLE);
				numNames.get(x).setVisibility(View.VISIBLE);
			} else {
				viewsList.get(x).setVisibility(View.GONE);
				numNames.get(x).setVisibility(View.GONE);
			}
		}

		// Animate entry/exit of "finish" button
		vader = findViewById(R.id.enterButton);
		if (numPplTry < 10 && numPplTry > 0) {
			Boolean lastFieldFilled = viewsList.get(numPplTry - 1).getText()
					.toString().trim().length() != 0;
			if (lastFieldFilled && vader.getVisibility() != View.VISIBLE) {
				vader.setVisibility(View.VISIBLE);
				vaderAnim = AnimationUtils.makeInAnimation(this, true);
				vader.startAnimation(vaderAnim);
			} else if (!lastFieldFilled
					&& vader.getVisibility() == View.VISIBLE) {
				vaderAnim = AnimationUtils.makeOutAnimation(this, true);
				vader.setVisibility(View.INVISIBLE);
				vader.startAnimation(vaderAnim);
			}
		}
	}

	// After entering contacts name phone number, send out secret santas.
	public void enterContactsInfo(View v) {
		String pickedName;
		Log.d(TAG, "Outside for loop");
		Intent intentAlice = new Intent(Intent.ACTION_VIEW);
		Intent intentBob = new Intent(Intent.ACTION_VIEW);
		Intent intentCarol = new Intent(Intent.ACTION_VIEW);
		Intent intentDan = new Intent(Intent.ACTION_VIEW);
		Intent intentErin = new Intent(Intent.ACTION_VIEW);
		Intent intentFrank = new Intent(Intent.ACTION_VIEW);
		Intent intentGena = new Intent(Intent.ACTION_VIEW);
		Intent intentHeather = new Intent(Intent.ACTION_VIEW);
		Intent intentJohn = new Intent(Intent.ACTION_VIEW);
		Intent intentKaren = new Intent(Intent.ACTION_VIEW);

		intentAlice.setData(Uri.fromParts("sms", numNames.get(0).getText()
				.toString(), null));
		intentBob.setData(Uri.fromParts("sms", numNames.get(1).getText()
				.toString(), null));
		intentCarol.setData(Uri.fromParts("sms", numNames.get(2).getText()
				.toString(), null));
		intentDan.setData(Uri.fromParts("sms", numNames.get(3).getText()
				.toString(), null));
		intentErin.setData(Uri.fromParts("sms", numNames.get(4).getText()
				.toString(), null));
		intentFrank.setData(Uri.fromParts("sms", numNames.get(5).getText()
				.toString(), null));
		intentGena.setData(Uri.fromParts("sms", numNames.get(6).getText()
				.toString(), null));
		intentHeather.setData(Uri.fromParts("sms", numNames.get(7).getText()
				.toString(), null));
		intentJohn.setData(Uri.fromParts("sms", numNames.get(8).getText()
				.toString(), null));
		intentKaren.setData(Uri.fromParts("sms", numNames.get(9).getText()
				.toString(), null));

		intentAlice.putExtra("sms_body", "Happy Holidays "
				+ viewsList.get(0).getText().toString()
				+ "!\nYour Secret Santa pick is "
				+ viewsList.get(1).getText().toString());
		Log.d(TAG, "Outside try /catch statement");
		startActivity(intentAlice);
		if (1 < numPplTry) {
			intentBob.putExtra("sms_body", "Happy Holidays "
					+ viewsList.get(1).getText().toString()
					+ "!\nYour Secret Santa pick is "
					+ viewsList.get(2).getText().toString());
			startActivity(intentBob);
		}
		if (2 < numPplTry) {
			intentCarol.putExtra("sms_body",
					"Happy Holidays " + viewsList.get(2).getText().toString()
							+ "!\nYour Secret Santa pick is "
							+ viewsList.get(3).getText().toString());
			startActivity(intentCarol);
		}
		if (3 < numPplTry) {
			intentDan.putExtra("sms_body", "Happy Holidays "
					+ viewsList.get(3).getText().toString()
					+ "!\nYour Secret Santa pick is "
					+ viewsList.get(4).getText().toString());
			startActivity(intentDan);
		}
		if (4 < numPplTry) {
			intentErin.putExtra("sms_body", "Happy Holidays "
					+ viewsList.get(4).getText().toString()
					+ "!\nYour Secret Santa pick is "
					+ viewsList.get(5).getText().toString());
			startActivity(intentErin);
		}
		if (5 < numPplTry) {
			intentFrank.putExtra("sms_body",
					"Happy Holidays " + viewsList.get(5).getText().toString()
							+ "!\nYour Secret Santa pick is "
							+ viewsList.get(6).getText().toString());
			startActivity(intentFrank);
		}
		if (6 < numPplTry) {
			intentGena.putExtra("sms_body", "Happy Holidays "
					+ viewsList.get(6).getText().toString()
					+ "!\nYour Secret Santa pick is "
					+ viewsList.get(7).getText().toString());
			startActivity(intentGena);
		}
		if (7 < numPplTry) {
			intentHeather.putExtra("sms_body", "Happy Holidays "
					+ viewsList.get(7).getText().toString()
					+ "!\nYour Secret Santa pick is "
					+ viewsList.get(8).getText().toString());
			startActivity(intentHeather);
		}
		if (8 < numPplTry) {
			intentJohn.putExtra("sms_body", "Happy Holidays "
					+ viewsList.get(8).getText().toString()
					+ "!\nYour Secret Santa pick is "
					+ viewsList.get(9).getText().toString());
			startActivity(intentJohn);
		}
		if (9 < numPplTry) {
			intentKaren.putExtra("sms_body",
					"Happy Holidays " + viewsList.get(9).getText().toString()
							+ "!\nYour Secret Santa pick is "
							+ viewsList.get(0).getText().toString());
			startActivity(intentKaren);
		}
	}
}
