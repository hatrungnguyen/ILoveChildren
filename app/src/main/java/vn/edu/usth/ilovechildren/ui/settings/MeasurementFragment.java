package vn.edu.usth.ilovechildren.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.ilovechildren.R;

public class MeasurementFragment extends Fragment {

    private TextView weightTextView, heightTextView, distanceTextView;
    private Spinner weightSpinner, heightSpinner, distanceSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measurement, container, false);

        weightTextView = view.findViewById(R.id.cv2t1);
        heightTextView = view.findViewById(R.id.cv2t3);
        distanceTextView = view.findViewById(R.id.cv2t5);

        weightSpinner = view.findViewById(R.id.cv2t11);
        heightSpinner = view.findViewById(R.id.cv2t31);
        distanceSpinner = view.findViewById(R.id.cv2t51);

        // Setting adapters for each Spinner
        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.weight_units, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightSpinner.setAdapter(weightAdapter);

        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.height_units, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightSpinner.setAdapter(heightAdapter);

        ArrayAdapter<CharSequence> distanceAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.distance_units, android.R.layout.simple_spinner_item);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpinner.setAdapter(distanceAdapter);

        // Handle clicks for each TextView
        weightTextView.setOnClickListener(v -> {
            weightTextView.setVisibility(View.VISIBLE);
            weightSpinner.setVisibility(View.VISIBLE);
        });

        heightTextView.setOnClickListener(v -> {
            heightTextView.setVisibility(View.VISIBLE);
            heightSpinner.setVisibility(View.VISIBLE);
        });

        distanceTextView.setOnClickListener(v -> {
            distanceTextView.setVisibility(View.VISIBLE);
            distanceSpinner.setVisibility(View.VISIBLE);
        });

        weightSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parentView, View selectedItemView, int position, long id) {
                weightSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parentView) {
            }
        });

        heightSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parentView, View selectedItemView, int position, long id) {
                heightSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parentView) {
            }
        });

        distanceSpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parentView, View selectedItemView, int position, long id) {
                distanceSpinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parentView) {
            }
        });

        return view;
    }
}
