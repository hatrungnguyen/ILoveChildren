package vn.edu.usth.ilovechildren.ui.settings;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Objects;

import vn.edu.usth.ilovechildren.adapters.InfoAdapters;
import vn.edu.usth.myapplication.R;

public class SettingsFragment extends Fragment {

    private static final String TAG = "settings";
    private TextView accountTextView, syncTextView, measurementUnitTextView, notificationsTextView, accessoryTextView, permissionsTextView, aboutAppsTextView, helpTextView, signOutTextView;
    private SwitchCompat syncSwitch;

    private static final int ACTION_APP_PERMISSION = 100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        accountTextView = view.findViewById(R.id.cv1t1);
        syncTextView = view.findViewById(R.id.cv1t4);
        syncSwitch = view.findViewById(R.id.cv1t5);
        measurementUnitTextView = view.findViewById(R.id.cv2t1);
        notificationsTextView = view.findViewById(R.id.cv2t3);
        accessoryTextView = view.findViewById(R.id.cv2t5);
        permissionsTextView = view.findViewById(R.id.cv3t1);
        aboutAppsTextView = view.findViewById(R.id.cv3t3);
        helpTextView = view.findViewById(R.id.cv3t5);
        signOutTextView = view.findViewById(R.id.cv4t1);

        accountTextView.setOnClickListener(v -> showToast("Account Clicked"));
        syncTextView.setOnClickListener(v -> showToast("Sync with Cloud Clicked"));
        measurementUnitTextView.setOnClickListener(v -> {
            Log.d(TAG, "Measure");
            openMeasurement();
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationsTextView.setOnClickListener(v -> openoti());
        }
        accessoryTextView.setOnClickListener(v -> showToast("Accessory Clicked"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            permissionsTextView.setOnClickListener(v -> openPermissionSettings());
        }

        aboutAppsTextView.setOnClickListener(v -> showToast("About Apps Clicked"));
        helpTextView.setOnClickListener(v -> showToast("Help Clicked"));
        signOutTextView.setOnClickListener(v -> showToast("Sign Out Clicked"));

        syncSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Sync Enabled" : "Sync Disabled";
            showToast(message);
        });

        return view;
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void openoti() {
        Intent intent;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    .putExtra(Settings.EXTRA_APP_PACKAGE, requireActivity().getPackageName());
        } else {
            intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                    .putExtra("app_package", requireActivity().getPackageName())
                    .putExtra("app_uid", requireActivity().getApplicationInfo().uid);
        }

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "Disallow!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    private void openPermissionSettings() {

        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);

    }

    private void openMeasurement() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.action_measurement);
    }

}


