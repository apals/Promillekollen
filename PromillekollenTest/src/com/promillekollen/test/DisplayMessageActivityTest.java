package com.promillekollen.test;

import com.promillekollen.DisplayMessageActivity;
import junit.framework.TestCase;

public class DisplayMessageActivityTest extends TestCase {

	private DisplayMessageActivity activity;
	
	public DisplayMessageActivityTest() {
	}

	protected void setUp() throws Exception {
		super.setUp();
		activity = new DisplayMessageActivity();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testCalculateBAC() {
		assertEquals(0.0, activity.calculateBAC(0, 1, 0));
	}
	
	public void testCalculateAlcoholAmount() {
		assertEquals(0.0, activity.calculateAlcoholAmount(0, 0, 0, 0));
	}

}
