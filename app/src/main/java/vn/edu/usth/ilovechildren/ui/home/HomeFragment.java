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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import vn.edu.usth.myapplication.R;


public class HomeFragment extends Fragment {
    private Button diagnosticButton;
    private ImageButton notificationIcon;
    private static final String TAG = "home";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        notificationIcon = view.findViewById(R.id.notificationIcon);
        diagnosticButton = view.findViewById(R.id.diagnosticButton);

        diagnosticButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_test);
            }

        });


        notificationIcon.setOnClickListener(v ->
        {
            Log.d(TAG, "Notifications");
        });

        view.findViewById(R.id.steps).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_steps);
            }
        });

        view.findViewById(R.id.heart_pressure).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_heart);
            }
        });

        view.findViewById(R.id.sleepCard).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_sleep);
            }
        });


        return view;
    }


}
