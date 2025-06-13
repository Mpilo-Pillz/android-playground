package dev.fikasentech.connect3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button playAgainButton;
    TextView winnerTextView;
    int activePlayer = 0; // 0 for red, 1 for yellow, 2 for empty
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameActive = true;
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] != 2 || !gameActive) {
            return; // If the counter is already placed, do nothing
        }

        gameState[tappedCounter] = activePlayer; // Update the game state with the active player's move
        counter.setTranslationY(-1500);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.red);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 0;
        }


        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for (int[] winningPosition : winningPositions) {
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                // A player has won
//                String winner = (gameState[winningPosition[0]] == 0) ? "Red" : "Yellow";
                gameActive = false;
                String winner = (activePlayer == 1) ? "Red" : "Yellow";
                // Show a message or perform an action for the winner
                // For example, you can show a Toast or update a TextView
                Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                winnerTextView = findViewById(R.id.winnerTextView);
                playAgainButton = findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setText(winner + " has won!");

            }
        }
    }

    public void playAgain(View view) {
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.connectGrid);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageResource(0); // Clear the image
//            counter.setImageDrawable(null); // Clear the image
        }
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2; // Reset the game state
        }
         activePlayer = 0; // 0 for red, 1 for yellow, 2 for empty
//        int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
         gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}