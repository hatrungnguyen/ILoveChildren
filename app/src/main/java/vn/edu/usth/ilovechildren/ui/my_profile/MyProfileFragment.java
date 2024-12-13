package vn.edu.usth.ilovechildren.ui.my_profile;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.health.connect.client.HealthConnectClient;
import androidx.health.connect.client.permission.HealthPermission;
import androidx.health.connect.client.records.WeightRecord;
import androidx.health.connect.client.PermissionController;

import java.util.Set;

import vn.edu.usth.ilovechildren.R;
import vn.edu.usth.ilovechildren.utils.HealthConnectUtils;

public class MyProfileFragment extends Fragment {

    private EditText weightInputEditText;
    private HealthConnectClient healthConnectClient;
    private PermissionController permissionController;

    // Permissions as a String array
    private final String[] requiredPermissions = new String[]{
            HealthPermission.WRITE_WEIGHT,
            HealthPermission.READ_WEIGHT
    };

    private ActivityResultLauncher<String[]> permissionRequestLauncher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        weightInputEditText = rootView.findViewById(R.id.weight_input_edit_text);

        // Button to save weight data
        rootView.findViewById(R.id.save_weight_button).setOnClickListener(v -> saveWeightData());

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize HealthConnectClient
        healthConnectClient = HealthConnectClient.getOrCreate(requireContext());

        // Register permission request launcher
        permissionRequestLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    // Log kết quả yêu cầu quyền
                    Log.d("HealthConnect", "Permission request result: " + result);

                    boolean allGranted = true;
                    for (boolean granted : result.values()) {
                        if (!granted) {
                            allGranted = false;
                            break;
                        }
                    }
                    if (!allGranted) {
                        Log.d("HealthConnect", "Some permissions were not granted.");
                        Toast.makeText(requireContext(), "Permissions are required to save data", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("HealthConnect", "All required permissions granted.");
                    }
                }
        );
    }

    private void saveWeightData() {
        String weightInput = weightInputEditText.getText().toString();

        if (TextUtils.isEmpty(weightInput)) {
            Toast.makeText(requireContext(), "Please enter a weight", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double weight = Double.parseDouble(weightInput);

            // Log quyền yêu cầu
            Log.d("HealthConnect", "Checking for WRITE_WEIGHT permission...");

            // Kiểm tra quyền đã được cấp
            permissionController.getGrantedPermissions()
                    .addListener(() -> {
                        Set<String> grantedPermissions = permissionController.getGrantedPermissions().get();

                        // Log các quyền đã được cấp
                        Log.d("HealthConnect", "Granted Permissions: " + grantedPermissions);

                        // Kiểm tra nếu quyền WRITE_WEIGHT đã được cấp
                        if (grantedPermissions.contains(HealthPermission.WRITE_WEIGHT)) {
                            Log.d("HealthConnect", "WRITE_WEIGHT permission granted.");
                            // Quyền đã được cấp, thực hiện thao tác với dữ liệu sức khỏe
                            HealthConnectUtils.writeWeightInput(healthConnectClient, requireContext(), weight);
                        } else {
                            Log.d("HealthConnect", "WRITE_WEIGHT permission NOT granted.");
                            // Yêu cầu quyền
                            permissionRequestLauncher.launch(requiredPermissions);
                        }
                    }, Runnable::run);

        } catch (NumberFormatException e) {
            Log.e("HealthConnect", "Invalid weight input", e);
            Toast.makeText(requireContext(), "Invalid weight input", Toast.LENGTH_SHORT).show();
        }
    }
}
