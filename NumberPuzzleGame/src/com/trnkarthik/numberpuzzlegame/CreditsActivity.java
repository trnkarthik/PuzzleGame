/*
			Assignment # 3
			File Name :CreditsActivity.java
			Full name of all students in our group:
			                   Raja Narasimha Karthik Tangirala. (UNC Charlotte ID : 800791204)
			                   Chakraprakash Venigella .(UNC Charlotte ID : 800781600)

 */


package com.trnkarthik.numberpuzzlegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class CreditsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				/*
				Intent backHome = new Intent(CreditsActivity.this,MainActivity.class);
				startActivity(backHome);
               */
				
				finish();
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_credits, menu);
        return true;
    }

    
}
