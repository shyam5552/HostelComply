package my.hostelcomply.app.electricianPanel;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import my.hostelcomply.app.R;
import my.hostelcomply.app.Student;
import my.hostelcomply.app.studentPanel.StudentComplaintDetails;

public class ElectricianPendingComplaintsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ComplaintAdapter adapter;
    private DatabaseReference complaintsRef;
    private List<StudentComplaintDetails> complaintsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electrician_pending_complaints, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_complaints);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        complaintsList = new ArrayList<>();
        adapter = new ComplaintAdapter(complaintsList);
        recyclerView.setAdapter(adapter);

        complaintsRef = FirebaseDatabase.getInstance().getReference("StudentelectricalComplaintDetails");
        fetchComplaints();

        return view;
    }

    private void fetchComplaints() {
        complaintsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaintsList.clear();
                for (DataSnapshot complaintSnapshot : dataSnapshot.getChildren()) {
                    StudentComplaintDetails complaint = complaintSnapshot.getValue(StudentComplaintDetails.class);
                    String studentId = complaint.getStudentId();

                    DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference("Students").child(studentId);
                    studentRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Student student = dataSnapshot.getValue(Student.class);
                            if (student != null) {
                                String studentName = student.getFname();
                                String phoneNumber = student.getMobileno();

                                complaint.setFname(studentName);
                                complaint.setPhonenumber(phoneNumber);

                                complaintsList.add(complaint);
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), "Failed to read student details.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to read complaints.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}