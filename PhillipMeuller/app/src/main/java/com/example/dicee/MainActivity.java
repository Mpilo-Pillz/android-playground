package com.example.dicee;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button rollButton;
        rollButton = findViewById(R.id.rollButton);

        final ImageView leftDice = findViewById(R.id.imageLeftDice);
        final ImageView rightDice = findViewById(R.id.imageRightDice);

        final int[] diceArray = {
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Dicee", "Le Button has been pressed");

                Random randomNumberGenerator = new Random();

                int number = randomNumberGenerator.nextInt(6);

                Log.d("DICEE", "onClick: the rando is " + number);

                leftDice.setImageResource(diceArray[number]);

                number = randomNumberGenerator.nextInt(6);
                rightDice.setImageResource(diceArray[number]);

            }
        });

    }
}