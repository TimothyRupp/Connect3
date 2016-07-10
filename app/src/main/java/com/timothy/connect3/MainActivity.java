package com.timothy.connect3;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //player0=yellow, player1=red
    int activePlayer=0;
    boolean gameIsActive = true;

    //2 indicates an unplayed cell
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView counter =(ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1980f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1980f).rotation(180).setDuration(350);

            for (int[] winningPostion : winningPositions) {

                if (gameState[winningPostion[0]] == gameState[winningPostion[1]] &&
                        gameState[winningPostion[1]]== gameState[winningPostion[2]] &&
                        gameState[winningPostion[0]] !=2 ) {
                    //Display winner
                    gameIsActive = false;
                    String winner = "Red";
                    if (gameState[winningPostion[0]] == 0) {
                        winner = "Yellow";
                    }
                    TextView winnerMsg =(TextView) findViewById(R.id.winnerMessage);
                    winnerMsg.setText(winner + " has won!");

                    LinearLayout layout =(LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    }else{
                        boolean gameIsOver =true;
                        for (int counterstate : gameState){
                            if (counterstate == 2) gameIsOver=false;
                            //System.out.println("Counterstate="+counterstate);
                            //System.out.println("gameIsOver ? "+gameIsOver);
                        }
                        if (gameIsOver) {
                            TextView winnerMsg =(TextView) findViewById(R.id.winnerMessage);
                            winnerMsg.setText("It's a draw!");

                            LinearLayout layout =(LinearLayout)findViewById(R.id.playAgainLayout);
                            layout.setVisibility(View.VISIBLE);
                            gameIsOver=false;
                        }
                    }
                }
            }//end if game win states check
    } //end dropIn method


    public void playAgain(View view){
        gameIsActive = true;
        LinearLayout layout =(LinearLayout)findViewById(R.id.playAgainLayout);
        if (layout != null) {
            layout.setVisibility(View.INVISIBLE);
        }
        //reset
        activePlayer=0;
        //2 indicates an unplayed cell
        for (int i =0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        GridLayout gridLayout =(GridLayout)findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
