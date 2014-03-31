/**
 * @author Andreas Pålsson
 * This is the main activity of Promillekollen. This activity is used to let the user enter all input needed
 * to calculate his or her blood alcohol content.
 */
package com.promillekollen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static final String NUMBER_OF_BEERS = "com.promillekollen.NUMBER_OF_BEERS";
	public static final String NUMBER_OF_SHOTS = "com.promillekollen.NUMBER_OF_SHOTS";
	public static final String NUMBER_OF_DRINKS = "com.promillekollen.NUMBER_OF_DRINKS";
	public static final String NUMBER_OF_WINE_GLASSES = "com.promillekollen.NUMBER_OF_WINE_GLASSES";
	public static final String WEIGHT = "com.promillekollen.WEIGHT";
	public static final String NUMBER_OF_HOURS = "com.promillekollen.NUMBER_OF_HOURS";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/**
	 * Collects the user's entered information, sends it to the next activity and starts the next activity
	 */
	public void sendUserInput(View view)
	{
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		
		EditText numberOfBeers = (EditText) findViewById(R.id.number_of_beers);
		EditText numberOfShots = (EditText) findViewById(R.id.number_of_shots);
		EditText numberOfDrinks = (EditText) findViewById(R.id.number_of_drinks);
		EditText numberOfWineGlasses = (EditText) findViewById(R.id.number_of_wine_glasses);
		EditText weight = (EditText) findViewById(R.id.weight);
		EditText numberOfHours = (EditText) findViewById(R.id.number_of_hours);
		
		
		String message = numberOfBeers.getText().toString();
		intent.putExtra(NUMBER_OF_BEERS, message);
		
		String message2 = numberOfShots.getText().toString();
		intent.putExtra(NUMBER_OF_SHOTS, message2);
		
		String message3 = numberOfDrinks.getText().toString();
		intent.putExtra(NUMBER_OF_DRINKS, message3);
		
		String message4 = numberOfWineGlasses.getText().toString();
		intent.putExtra(NUMBER_OF_WINE_GLASSES, message4);
		
		String message5 = weight.getText().toString();
		intent.putExtra(WEIGHT, message5);
		
		String message6 = numberOfHours.getText().toString();
		intent.putExtra(NUMBER_OF_HOURS, message6);
		
		startActivity(intent);
		
	}

}
