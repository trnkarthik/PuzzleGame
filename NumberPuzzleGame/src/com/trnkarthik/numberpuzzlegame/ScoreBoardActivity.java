/*
			Assignment # 3
			File Name :ScoreBoardActivity.java
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
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScoreBoardActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_board);


		Intent inscore =getIntent();
		Bundle extras = inscore.getExtras();

		int completedgames=0,totalgames=0,minmoves=0,maxmoves=0,totalmoves=0,forfeitedgames=0;


		completedgames=extras.getInt("completedgames");

		forfeitedgames=extras.getInt("forfeitedgames");

		totalgames=forfeitedgames+completedgames;

		minmoves=extras.getInt("minmoves");

		maxmoves=extras.getInt("maxmoves");

		totalmoves = extras.getInt("totalmoves");

		Button backtomain = (Button)findViewById(R.id.scoreBackButton);
		backtomain.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		int completedpercent,incompletepercent;
		if(totalgames!=0)
		{
			completedpercent = (completedgames/totalgames)*100;
			incompletepercent = ((forfeitedgames)/totalgames)*100;
		}
		else
		{
			completedpercent = 0;
			incompletepercent = 0;
		}
		ProgressBar pb1 = (ProgressBar) findViewById(R.id.progressBar1);
		pb1.setProgress(completedpercent);

		ProgressBar pb2= (ProgressBar) findViewById(R.id.progressBar2);
		pb2.setProgress(incompletepercent);

		TextView numCompletedGames = (TextView)findViewById(R.id.CompletedGames_num);
		numCompletedGames.setText(completedgames + "/" + totalgames);

		TextView numforfeitedgames = (TextView)findViewById(R.id.forfeitedGames_num);
		numforfeitedgames.setText((forfeitedgames) + "/" + totalgames);

		TextView minimummoves = (TextView) findViewById(R.id.minMoves_num);
		minimummoves.setText(minmoves+"");

		TextView maximummoves = (TextView) findViewById(R.id.maxmoves_num);
		maximummoves.setText(maxmoves+"");

		TextView averagemoves = (TextView) findViewById(R.id.avgmoves_num);
		averagemoves.setText((totalmoves/totalgames)+"");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_score_board, menu);
		return true;
	}

}
