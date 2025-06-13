package dev.fikasentech.higherorlower;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText userGuessInput;
    Button guessButton;
    int number = (int) Math.floor(Math.random() * 20);

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

        userGuessInput = findViewById(R.id.userGuessedNumber);
        guessButton = findViewById(R.id.guessButton);
    }

    public void onGuessButtonClick(View view) {
        if (number > Integer.parseInt(userGuessInput.getText().toString())) {
            Toast.makeText(this, "Too Low!", Toast.LENGTH_SHORT).show();
        } else if (number < Integer.parseInt(userGuessInput.getText().toString())) {
            Toast.makeText(this, "Too High!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You guessed it!", Toast.LENGTH_SHORT).show();
        }
    }
}