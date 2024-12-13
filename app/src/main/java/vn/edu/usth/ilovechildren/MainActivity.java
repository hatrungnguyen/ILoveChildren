package vn.edu.usth.ilovechildren;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.Objects;
import vn.edu.usth.ilovechildren.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationBar = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigationBar, navController);

        navController.addOnDestinationChangedListener(
                (@NonNull NavController controller, @NonNull androidx.navigation.NavDestination destination, Bundle arguments) -> {
                    if (destination.getId() == R.id.navigation_test ||
                            destination.getId() == R.id.navigation_sleep ||
                            destination.getId() == R.id.navigation_step ||
                            destination.getId() == R.id.navigation_heart ||
                            destination.getId() == R.id.navigation_measurement) {
                        bottomNavigationBar.setVisibility(View.GONE);
                        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
                        toolbar.setVisibility(View.VISIBLE);
                    } else {
                        bottomNavigationBar.setVisibility(View.VISIBLE);
                        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
                        toolbar.setVisibility(View.GONE);
                    }
                });


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
