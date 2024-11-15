package vn.edu.usth.ilovechildren.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import vn.edu.usth.myapplication.R;

public class HomeFragment extends Fragment {
    private Button diagnosticButton;
    private ImageButton notificationIcon;
    private static final String TAG = "home";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        notificationIcon = view.findViewById(R.id.notificationIcon);
        diagnosticButton = view.findViewById(R.id.diagnosticButton);

        diagnosticButton.setOnClickListener(v -> {
            Log.d(TAG, "Diagnostic");
        });

        notificationIcon.setOnClickListener(v -> {
            Log.d(TAG, "Notifications");
        });

        return view;
    }
}
