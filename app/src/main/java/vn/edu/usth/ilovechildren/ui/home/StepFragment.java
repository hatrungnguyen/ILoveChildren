package vn.edu.usth.ilovechildren.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import vn.edu.usth.myapplication.R;

public class StepFragment extends Fragment {

    private BarChart barChart;
    private TextView stepsData;
    private CalendarView calendarView;
    private ArrayList<BarEntry> barEntries;
    private ArrayList<String> dates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step, container, false);

        barChart = view.findViewById(R.id.chart1);
        stepsData = view.findViewById(R.id.data);
        calendarView = view.findViewById(R.id.calendar);

        String jsonData = loadJSONFromAsset();
        setupChartData(jsonData);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            String selectedDate = String.format(Locale.getDefault(), "%02d/%02d", dayOfMonth, month + 1);

            boolean found = false;
            for (int i = 0; i < dates.size(); i++) {
                if (dates.get(i).equals(selectedDate)) {
                    stepsData.setText(String.format("%s steps", (int) barEntries.get(i).getY()));
                    highlightBar(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                stepsData.setText("No data for this date");
            }
        });

        // Trigger calendar change listener to display today's data
        triggerCurrentDate();

        return view;
    }

    private void triggerCurrentDate() {
        long currentTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);

        calendarView.setDate(currentTime, false, true);

        String currentDate = String.format(Locale.getDefault(), "%02d/%02d", currentDay, currentMonth + 1);

        boolean found = false;
        for (int i = 0; i < dates.size(); i++) {
            if (dates.get(i).equals(currentDate)) {
                stepsData.setText(String.format("%s steps", (int) barEntries.get(i).getY()));
                highlightBar(i);
                found = true;
                break;
            }
        }

        if (!found) {
            stepsData.setText("No data for this date");
        }
    }

    private void setupChartData(String jsonData) {
        try {
            barEntries = new ArrayList<>();
            dates = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray dailyData = jsonObject.getJSONArray("daily_data");

            for (int i = 0; i < dailyData.length(); i++) {
                JSONObject data = dailyData.getJSONObject(i);
                long timestamp = data.getLong("date");
                int steps = data.getInt("steps");

                String date = new SimpleDateFormat("dd/MM", Locale.getDefault())
                        .format(new Date(timestamp * 1000));
                dates.add(date);

                barEntries.add(new BarEntry(i, steps));
            }

            BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
            barDataSet.setColor(getResources().getColor(android.R.color.holo_blue_dark));
            barDataSet.setDrawValues(false);
            barDataSet.setLabel(String.valueOf(false));
            barChart.getDescription().setEnabled(false);
            barChart.getLegend().setEnabled(false);
            barChart.getXAxis().setDrawLabels(false);
            barChart.getXAxis().setDrawAxisLine(false);
            barChart.getXAxis().setDrawGridLines(false);
            barChart.getAxisRight().setEnabled(false);

            BarData barData = new BarData(barDataSet);
            barChart.setData(barData);
            barChart.invalidate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void highlightBar(int index) {
        barChart.highlightValue(index, 0);
        barChart.invalidate();
    }

    private String loadJSONFromAsset() {
        try {
            InputStream is = getContext().getAssets().open("simple.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
