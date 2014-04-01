/**
 * @author Andreas Pålsson
 * This activity is only used for displaying the user's blood alcohol content. It retrieves the user's entered
 * information from MainActivity and calculates the user's blood alcohol content.
 */

package com.promillekollen;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class DisplayMessageActivity extends Activity {
	
	/* A gender constant. The mean value of the two are used in this program. */
	private static final double genderConstant = (0.55+0.68)/2;
	private int numberOfBeers = 0;
	private int numberOfShots = 0;
	private int numberOfDrinks = 0;
	private int numberOfWineGlasses = 0;
	private int weight = 70;
	private int numberOfHours = 1;
	
	/* Get the connection to MainActivity */
	private Intent intent;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_message);
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
		
		
		intent = getIntent();
				
		retrieveUserInput();
	
				
		/* Calculate the total amount of alcohol in liquid ounces */
		double alcoholAmount = calculateAlcoholAmount(numberOfBeers, numberOfShots, numberOfDrinks, numberOfWineGlasses);		
		
		/* Calculate the weight in pounds instead of kilograms */
		double W = weight/0.45359237;
		
		/* Calculate the blood alcohol content */
		double BAC = calculateBAC(alcoholAmount, W, numberOfHours); 
				
		/* If nothing or unexpected user input */
		if(alcoholAmount <= 0)
			BAC = 0;
		
		/* Convert to per thousand instead of percent */
		BAC = 10*BAC;
		
		/* Round off to 2 decimals */
		BAC = (double) Math.round(BAC * 100) / 100;	
		
		/* Display the result */
		TextView textView = (TextView) findViewById(R.id.promilleText);
		textView.setTextSize(12);
		textView.setText("Din promillehalt är " + BAC);
		
	}
	
	/**
	 * Calculates the user's blood alcohol content
	 * @param alcoholAmount The amount of alcohol the user has drunk in liquid ounces
	 * @param weight	The weight of the user in pounds
	 * @param genderConstant A constant normally varying depending on gender. In this program a mean value is used. 
	 * @param numberOfHours The amount of hours the user has been drinking for
	 * @return The user's blood alcohol content
	 */
	public double calculateBAC(double alcoholAmount, double weight, int numberOfHours) {
		return (alcoholAmount * 5.14/(weight * genderConstant)) - 0.015*numberOfHours;
	}
	
	/**
	 * @return The amount of alcohol the user has drunk in liquid ounces
	 */
	public double calculateAlcoholAmount(int numberOfBeers, int numberOfShots, int numberOfDrinks, int numberOfWineGlasses) {
		double a = numberOfBeers*11.1586275*0.052;
		double b = numberOfShots*1.35256091*0.4;
		double c = numberOfDrinks*1.35256091*0.4;
		double d = numberOfWineGlasses*5.07210341*0.12;
		return a+b+c+d;	
	}
	
	
	/**
	 * Collects the user's entered information from MainActivity
	 */
	public void retrieveUserInput() {
		if(isInteger(intent.getStringExtra(MainActivity.NUMBER_OF_BEERS)))
			 numberOfBeers = Integer.parseInt(intent.getStringExtra(MainActivity.NUMBER_OF_BEERS));
	
		if(isInteger(intent.getStringExtra(MainActivity.NUMBER_OF_SHOTS)))
			numberOfShots = Integer.parseInt(intent.getStringExtra(MainActivity.NUMBER_OF_SHOTS));
		
		if(isInteger(intent.getStringExtra(MainActivity.NUMBER_OF_DRINKS)))
			numberOfDrinks = Integer.parseInt(intent.getStringExtra(MainActivity.NUMBER_OF_DRINKS));
		
		if(isInteger(intent.getStringExtra(MainActivity.NUMBER_OF_WINE_GLASSES)))
			numberOfWineGlasses = Integer.parseInt(intent.getStringExtra(MainActivity.NUMBER_OF_WINE_GLASSES));
		
		if(isInteger(intent.getStringExtra(MainActivity.WEIGHT)))
			weight = Integer.parseInt(intent.getStringExtra(MainActivity.WEIGHT));
		
		if(isInteger(intent.getStringExtra(MainActivity.NUMBER_OF_HOURS)))
			numberOfHours = Integer.parseInt(intent.getStringExtra(MainActivity.NUMBER_OF_HOURS));
	}
	
	public void killActivity(View view)
	{
		this.finish();
	}
	
	public boolean isInteger(String s) {
		return s.matches("^-?([1-9][0-9]*|0)");
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
