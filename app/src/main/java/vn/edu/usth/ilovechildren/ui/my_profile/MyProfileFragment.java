package vn.edu.usth.ilovechildren.ui.my_profile;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.health.connect.client.HealthConnectClient;

import vn.edu.usth.ilovechildren.R;
import vn.edu.usth.ilovechildren.utils.HealthConnectUtils;

public class MyProfileFragment extends Fragment {

    private EditText weightInputEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        weightInputEditText = rootView.findViewById(R.id.weight_input_edit_text);  // Assuming you have an EditText for inputting weight

        // Example: button click to save weight data
        rootView.findViewById(R.id.save_weight_button).setOnClickListener(v -> {
            String weightInputText = weightInputEditText.getText().toString();

            if (!TextUtils.isEmpty(weightInputText)) {
                try {
                    double weight = Double.parseDouble(weightInputText);
                    // Call method to write the weight data
                    HealthConnectClient healthConnectClient = getHealthConnectClient();
                    HealthConnectUtils.writeWeightInput(healthConnectClient, getContext(), weight);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Invalid weight input", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Please enter a weight", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private HealthConnectClient getHealthConnectClient() {
        // Create an instance of HealthConnectClient
        return HealthConnectClient.getOrCreate(requireContext());
    }
}
