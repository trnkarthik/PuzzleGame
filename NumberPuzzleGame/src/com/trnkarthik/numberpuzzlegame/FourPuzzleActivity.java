
/*
			Assignment # 3
			File Name :FourPuzzleActivity.java
			Full name of all students in our group:
			                   Raja Narasimha Karthik Tangirala. (UNC Charlotte ID : 800791204)
			                   Chakraprakash Venigella .(UNC Charlotte ID : 800781600)

 */

package com.trnkarthik.numberpuzzlegame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FourPuzzleActivity extends Activity{

	public int initialelementsarray[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	public static int[] elementsarray;


	public static final int SWIPE_MIN_DISTANCE = 60;
	public static final int SWIPE_MIN_DISTANCE2 = 10;
	public static final int SWIPE_MAX_OFF_PATH = 1000;
	public static final int SWIPE_THRESHOLD_VELOCITY = 20;

	public static int currentblockvalue = 0;
	public static int currentblockid = 0;

	public Boolean movedleft=false,movedright=false,movedup=false,moveddown=false;


	public GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;


	public int[] imageViews= {R.id.imgview1, R.id.imgview2, R.id.imgview3,R.id.imgview4, 
			R.id.imgview5,R.id.imgview6, R.id.imgview7, R.id.imgview8,R.id.imgview9,R.id.imgview10,
			R.id.imgview11,R.id.imgview12, R.id.imgview13, R.id.imgview14,R.id.imgview15,R.id.imgview16};

	public int[] iconIds = new int[] {R.drawable.block1,R.drawable.block2,R.drawable.block3,R.drawable.block4,
			R.drawable.block5,R.drawable.block6,R.drawable.block7,R.drawable.block8,R.drawable.block9,
			R.drawable.block10,R.drawable.block11,R.drawable.block12,R.drawable.block13,R.drawable.block14,
			R.drawable.block15,R.drawable.empty};

	Boolean clickable =true;
	Boolean Touchable =true;
	int moves=0;

	int minmoves=0,maxmoves=0,totalmoves=0;
	int completedgames=0,forfeitedgames=0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four_puzzle);

		resetGame();

		//exit button
		Button exit = (Button)findViewById(R.id.exit_button);
		exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		//credits button
		Button credits =(Button)findViewById(R.id.credits_button);
		credits.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent callcredits = new Intent(FourPuzzleActivity.this, CreditsActivity.class);
				startActivity(callcredits);
			}
		});

		Button resetButton =(Button)findViewById(R.id.reset_button);
		resetButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(!isgameover())
				{
					AlertDialog alertDialog = new AlertDialog.Builder(FourPuzzleActivity.this).create();

					alertDialog.setTitle("Reset Game ?");

					alertDialog.setMessage("Are you sure you want to reset the game?This game will be forfeited");
					alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							resetGame();
							forfeitedgames++;
						}
					});


					alertDialog.setButton2("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

						}
					});
					alertDialog.show();

				}
				else
					resetGame();
			}
		});


		//scores button
		Button scoresbutton =(Button)findViewById(R.id.score_button);
		scoresbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent toscoreboard = new Intent(FourPuzzleActivity.this,ScoreBoardActivity.class);
				toscoreboard.putExtra("completedgames", completedgames);
				toscoreboard.putExtra("forfeitedgames", forfeitedgames);
				toscoreboard.putExtra("minmoves", minmoves);
				toscoreboard.putExtra("maxmoves", maxmoves);
				toscoreboard.putExtra("totalmoves", totalmoves);
				startActivity(toscoreboard); 

				Log.d("input test ", completedgames+" ");
				Log.d("input test ", forfeitedgames+" ");
				Log.d("input test ", minmoves+" ");
				Log.d("input test ", maxmoves+" ");

			}
		});




		//trying new stuff



		class MyGestureDetector extends SimpleOnGestureListener 
		{
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				try {					

					int deltax =(int) (e2.getX() - e1.getX());
					int deltay =(int) (e2.getY() - e1.getY());

					if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
						return false;

					if(Math.abs(deltay)<Math.abs(deltax))
					{
						//left or right
						if( deltax + SWIPE_MIN_DISTANCE<=0) {
							movedleft=true;
							Toast.makeText(FourPuzzleActivity.this, deltax + " \nLeft Swipe\n" + deltay, Toast.LENGTH_SHORT).show();
						}  
						else if ( deltax - SWIPE_MIN_DISTANCE>0)
						{
							movedright=true;
							//Toast.makeText(ThreePuzzleActivity.this, deltax + "\nRight Swipe\n" + deltay, Toast.LENGTH_SHORT).show();
						}      

					}
					else
					{
						//top or down
						if(deltay+SWIPE_MIN_DISTANCE<=0)
						{
							movedup=true;
							//Toast.makeText(ThreePuzzleActivity.this, deltax + "\nUp Swipe\n" + deltay, Toast.LENGTH_SHORT).show();
						}
						else
						{
							moveddown=true;
							//Toast.makeText(ThreePuzzleActivity.this, deltax + "\nDown Swipe\n" + deltay, Toast.LENGTH_SHORT).show();
						}
					}

					//things are to be done after swiping			



					if(Touchable ==true)
					{

						if(movedright==true)
						{
							if(isleftofempty(currentblockid, getempty(elementsarray)))
							{
								moveright(currentblockid);
								setUiWithArrayElements();
								checkgameover();
								moves++;
							}
							movedright=false;
						}

						if(movedleft==true)
						{
							if(isrightofempty(currentblockid, getempty(elementsarray)))
							{
								moveleft(currentblockid);
								setUiWithArrayElements();
								checkgameover();
								moves++;
							}
							movedleft=false;
						}

						if(movedup==true)
						{
							if(isbottomofempty(currentblockid, getempty(elementsarray)))
							{
								movetop(currentblockid);
								setUiWithArrayElements();
								checkgameover();
								moves++;
							}
							movedup=false;
						}

						if(moveddown==true)
						{
							if(istopofempty(currentblockid, getempty(elementsarray)))
							{
								movedown(currentblockid);
								setUiWithArrayElements();
								checkgameover();
								moves++;
							}
							moveddown=false;
						}

					}



				} catch (Exception e) {
					// nothing
				}
				return false;
			}

		}

		// Gesture detection
		gestureDetector = new GestureDetector(new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				
				Log.d("checking", "done!");
				int keyid = 55;
				ImageView iv = (ImageView) v;
				keyid =(int)Integer.parseInt(iv.getTag().toString());
				currentblockvalue=(keyid+1);
				currentblockid = getidfromelementsarray(currentblockvalue-1);

				return gestureDetector.onTouchEvent(event);
			}
		};



		//new stuff ends here


	}

	//oncreate ends here

	public void onClick(View v)
	{
		if(clickable == true)
		{
			int keyid=0,keyelement = 0;
			ImageView iv = (ImageView) v;
			for(int t=0;t<elementsarray.length;t++)
			{
				if(iv.getTag().equals(t))
				{
					keyid = t;
					break;
				}
			}


			if(keyid!=15)
			{
				keyelement = getidfromelementsarray(keyid);

				//Toast.makeText(getApplicationContext(), "keyid : " + keyelement + "\n empty at :" + getempty(elementsarray), Toast.LENGTH_LONG).show();

				int emptyelement=getempty(elementsarray);
				//checking left top bottom conditions
				if(isleftofempty(keyelement,emptyelement))
				{
					moveright(keyelement);
					setUiWithArrayElements();
					checkgameover();
					moves++;
				}

				if(isrightofempty(keyelement,emptyelement))
				{
					moveleft(keyelement);
					setUiWithArrayElements();
					checkgameover();
					moves++;
				}

				if(isbottomofempty(keyelement,emptyelement))
				{
					movetop(keyelement);
					setUiWithArrayElements();
					checkgameover();
					moves++;
				}

				if(istopofempty(keyelement,emptyelement))
				{
					movedown(keyelement);
					setUiWithArrayElements();
					checkgameover();
					moves++;
				}


			}
		}

		TextView numOfMoves = (TextView) findViewById(R.id.num_of_moves);
		numOfMoves.setText(moves+"");
	}




	public void resetGame(){

		
		totalmoves+=moves;
		if(moves>maxmoves)
		{
			maxmoves=moves;
		}
		if(moves<minmoves || minmoves ==0)
		{
			maxmoves=moves;
		}

		//initializing all the variables
		TextView tv = (TextView) findViewById(R.id.num_of_moves);
		tv.setText("0");
		
		Touchable = true;
		clickable =true;
		moves=0;

		//initialize the array
		elementsarray = (int[]) initialelementsarray.clone();
		//get the processed array 
		findsolvablepuzzlearray(elementsarray);

		findsolvablepuzzlearray(elementsarray);
		setUiWithArrayElements();

	}

	public void checkgameover()
	{
		if(isgameover())
		{
			Toast.makeText(getApplicationContext(), 
					"Congrats ! You are done with the puzzle.Click Reset to play new game !", 
					Toast.LENGTH_LONG).show();
			clickable =false;
			Touchable = false;
			completedgames++;
		}
	}

	public void findsolvablepuzzlearray(int[] orderedarray) {

		for(int i =0;i<=50;i++)
		{
			while(randomSwitch((int)((Math.random()*3)+1))==1)
			{
				continue;
			}

		}
	}

	public void setUiWithArrayElements()
	{
		for(int i=0; i<imageViews.length; i++)
		{    		
			ImageView iv = (ImageView) findViewById(imageViews[i]);
			iv.setImageResource(iconIds[elementsarray[i]]);
			iv.setTag(elementsarray[i]);
		}
	}

	public int getidfromelementsarray(int element)
	{
		int id = 0;
		for(int trn=0;trn<elementsarray.length;trn++)
		{
			if(elementsarray[trn]==element)
			{
				id = trn;
				break;
			}
		}
		return id;

	}

	public boolean isgameover()
	{

		for(int i=0;i<elementsarray.length;i++)
		{
			if(elementsarray[i]==i)
				continue;
			else
				return false;
		}
		return true;
	}

	//The common methods comes here


	public int randomSwitch(int randnum)
	{
		int done=0;
		switch(randnum)
		{
		case 1:
			if(canleft(getempty(elementsarray)))
			{
				moveleft(getempty(elementsarray));
				done=1;
			}
			break;
		case 2:
			if(canright(getempty(elementsarray)))
			{
				moveright(getempty(elementsarray));
				done=1;
			}
			break;
		case 3:
			if(canup(getempty(elementsarray)))
			{
				movetop(getempty(elementsarray));
				done=1;
			}
			break;
		case 4:
			if(candown(getempty(elementsarray)))
			{
				movedown(getempty(elementsarray));
				done=1;
			}
			break;
		}
		return done;

	}

	public int getempty(int[] somearray)
	{
		int k;
		for(k=0;k<somearray.length;k++)
		{
			if(somearray[k]==15)
			{
				break;
			}
		}
		return k;
	}

	public static boolean canleft(int id)
	{
		if(id%4==0)
			return false;
		else
			return true;	
	}

	public static boolean canright(int id)
	{
		if(id%4==3)
			return false;
		else
			return true;
	}
	public static boolean canup(int id)
	{
		if(id/4==0)
			return false;
		else
			return true;
	}
	public static boolean candown(int id)
	{
		if(id/4==3)
			return false;
		else
			return true;
	}

	public static void swap(int a,int b)
	{
		int temp = elementsarray[a];
		elementsarray[a]=elementsarray[b];
		elementsarray[b]=temp;
	}

	public static void moveleft(int id)
	{
		if(canleft(id))
		{
			swap(id,(id-1));
		}
	}


	public static void moveright(int id)
	{
		if(canright(id))
		{
			swap(id,(id+1));
		}
	}

	public static void movetop(int id)
	{
		if(canup(id))
		{
			swap(id, (id-4));
		}
	}

	public static void movedown(int id) {
		if(candown(id))
		{
			swap(id, (id+4));
		}
	}

	public static boolean isleftofempty(int key,int empty)
	{
		if(key/4 == empty/4)
		{
			if(empty-key==1)
				return true;
		}
		else
			return false;
		return false;
	}

	public static boolean isrightofempty(int key,int empty)
	{
		if(key/4 == empty/4)
		{
			if(key-empty==1)
				return true;
		}
		else
			return false;
		return false;
	}

	public static boolean istopofempty(int key,int empty)
	{
		if(key%4 == empty%4)
		{
			if(empty-key==4)
				return true;
		}
		else
			return false;
		return false;
	}

	public static boolean isbottomofempty(int key,int empty)
	{
		if(key%4 == empty%4)
		{
			if(key-empty==4)
				return true;
		}
		else
			return false;
		return false;
	}

	//common methods ends here

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}



}
