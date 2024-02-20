package my.hostelcomply.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Electricianloginphone extends AppCompatActivity {
    EditText num;
    Button sendotp,signinemail;
    TextView signup;
    FirebaseAuth Fauth;
    String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricianloginphone);
        num = (EditText)findViewById(R.id.number);
        sendotp = (Button)findViewById(R.id.otp);
        signinemail=(Button)findViewById(R.id.btnEmail);
        signup = (TextView)findViewById(R.id.acsignup);

        Fauth = FirebaseAuth.getInstance();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                number=num.getText().toString().trim();
                String Phonenum = "+91"+number;
                Intent b = new Intent(Electricianloginphone.this,Electriciansendotp.class);


                b.putExtra("Phonenum",Phonenum);
                startActivity(b);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Electricianloginphone.this,ElectricianRegistration.class));
                finish();
            }
        });
        signinemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Electricianloginphone.this,Electricianlogin.class));
                finish();
            }
        });
    }
}