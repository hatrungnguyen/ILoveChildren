package vn.edu.usth.ilovechildren.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import vn.edu.usth.ilovechildren.models.Data;
import vn.edu.usth.ilovechildren.models.Patient;
import vn.edu.usth.ilovechildren.R;

public class InfoAdapters extends RecyclerView.Adapter<InfoAdapters.DailyDataViewHolder> {
    private Patient patient; // Thêm tham chiếu đến Patient
    private List<Data> dailyDataList;

    // Constructor
    public InfoAdapters(List<Data> dailyDataList) {
        this.patient = patient; // Nhận Patient từ ngoài
        this.dailyDataList = dailyDataList;
    }

    @NonNull
    @Override
    public DailyDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_testdb, parent, false);
        return new DailyDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyDataViewHolder holder, int position) {
        Data dailyData = dailyDataList.get(position);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(new java.util.Date(dailyData.getDate() * 1000));
        holder.dateTextView.setText(formattedDate);

        holder.sleepTextView.setText(dailyData.getSleep_hours() + " hours");
        holder.stepsTextView.setText(String.valueOf(dailyData.getSteps()));
        holder.heartRateTextView.setText(String.valueOf(dailyData.getHeart_rate()));
        holder.systolicTextView.setText(String.valueOf(dailyData.getSystolic_pressure()));
        holder.diastolicTextView.setText(String.valueOf(dailyData.getDiastolic_pressure()));
    }

    @Override
    public int getItemCount() {
        return dailyDataList.size();
    }

    public static class DailyDataViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, sleepTextView, stepsTextView, heartRateTextView, systolicTextView, diastolicTextView;

        public DailyDataViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.t31);
            sleepTextView = itemView.findViewById(R.id.t41);
            stepsTextView = itemView.findViewById(R.id.t51);
            heartRateTextView = itemView.findViewById(R.id.t61);
            systolicTextView = itemView.findViewById(R.id.t71);
            diastolicTextView = itemView.findViewById(R.id.t81);
        }
    }
}
