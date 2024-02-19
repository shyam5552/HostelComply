package my.hostelcomply.app;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageView);


        imageView.animate().alpha(0f).setDuration(0);



        imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */

        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this,MainMenu.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}