/*
			Assignment # 3
			File Name :MainActivity.java
			Full name of all students in our group:
			                   Raja Narasimha Karthik Tangirala. (UNC Charlotte ID : 800791204)
			                   Chakraprakash Venigella .(UNC Charlotte ID : 800781600)

 */
package com.trnkarthik.numberpuzzlegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//credits button
		Button credits = (Button) findViewById(R.id.creditsinmain);
		credits.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent tocredits = new Intent(MainActivity.this, CreditsActivity.class);
				startActivity(tocredits);			
			}
		});    
		
		
		//exit button
		Button exitbutton = (Button) findViewById(R.id.exitinmain);
		exitbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
				
		//3puzzle
		Button threepuzzle = (Button)findViewById(R.id.puzzle3);
		threepuzzle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent tothreepuzzle = new Intent(MainActivity.this, ThreePuzzleActivity.class);
				startActivity(tothreepuzzle);
			}
		});		
		
		
		//4puzzle
		
		Button fourpuzzle = (Button)findViewById(R.id.puzzle4);
		fourpuzzle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent tofourpuzzle = new Intent(MainActivity.this, FourPuzzleActivity.class);
				startActivity(tofourpuzzle);
			}
		});
		
		
	}


	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


}
