package vn.edu.usth.ilovechildren.ui.home;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.widget.TextView;
import java.util.Calendar;
import vn.edu.usth.ilovechildren.R;

public class SleepFragment extends Fragment {
    private Handler handler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);

        TextView timeDisplay = view.findViewById(R.id.time_display);

        Runnable updateTime = new Runnable() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);


                String formattedTime = String.format("%02d:%02d:%02d", hour, minute, second);
                timeDisplay.setText(formattedTime);


                handler.postDelayed(this, 1000);
            }
        };
        handler.post(updateTime);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
