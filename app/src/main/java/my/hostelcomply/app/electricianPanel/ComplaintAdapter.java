package my.hostelcomply.app.electricianPanel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.hostelcomply.app.R;
import my.hostelcomply.app.studentPanel.StudentComplaintDetails;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private List<StudentComplaintDetails> complaintsList;

    public ComplaintAdapter(List<StudentComplaintDetails> complaintsList) {
        this.complaintsList = complaintsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentComplaintDetails complaint = complaintsList.get(position);
        holder.txtDescription.setText(complaint.getDesc());
        holder.txtProblemItems.setText(complaint.getProblemitem());
        holder.txtStudentName.setText(complaint.getFname());
        holder.txtPhoneNumber.setText(complaint.getPhonenumber());
    }

    @Override
    public int getItemCount() {
        return complaintsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescription, txtProblemItems, txtStudentName, txtPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescription = itemView.findViewById(R.id.text_description);
            txtProblemItems = itemView.findViewById(R.id.text_problem_items);
            txtStudentName = itemView.findViewById(R.id.text_student_name);
            txtPhoneNumber = itemView.findViewById(R.id.text_phone_number);
        }
    }
}