package my.hostelcomply.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import my.hostelcomply.app.studentPanel.StudentComplaintFragment;
import my.hostelcomply.app.studentPanel.StudentHomeFragment;
import my.hostelcomply.app.studentPanel.StudentPendingComplaintFragment;
import my.hostelcomply.app.studentPanel.StudentProfileFragment;

public class Panel_BottomNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_bottom_navigation);
        BottomNavigationView navigationView=findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.studentHome:
                fragment=new StudentHomeFragment();
                break;
            case R.id.PendingComplaints:
                fragment=new StudentPendingComplaintFragment();
                break;
            case R.id.Complaints:
                fragment=new StudentComplaintFragment();
                break;
            case R.id.studentProfile:
                fragment=new StudentProfileFragment();
                break;
        }
        return loadstudentfragment(fragment);
    }

    private boolean loadstudentfragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return  false;
    }
}