package vn.edu.usth.ilovechildren.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import vn.edu.usth.ilovechildren.adapters.InfoAdapters;
import vn.edu.usth.ilovechildren.models.Data;
import vn.edu.usth.ilovechildren.models.Patient;
import vn.edu.usth.myapplication.R;

public class TestDBFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView t11, t21;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_db, container, false);

        t11 = view.findViewById(R.id.t11);
        t21 = view.findViewById(R.id.t21);
        recyclerView = view.findViewById(R.id.recyclerView);

        Patient patient = loadPatientData();

        if (patient != null) {
            t11.setText(patient.getPatient_id());
            t21.setText(patient.getName());

            List<Data> dailyDataList = patient.getDaily_data();

            InfoAdapters adapter = new InfoAdapters(dailyDataList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        } else {
            // If failed to load data, show a Toast
            Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private Patient loadPatientData() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("simple.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, Patient.class);
        } else {
            return null;
        }
    }
}
