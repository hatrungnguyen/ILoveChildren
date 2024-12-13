package vn.edu.usth.ilovechildren.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.health.connect.client.HealthConnectClient;
import vn.edu.usth.ilovechildren.utils.HealthConnectWrapper;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class HealthConnectUtils {

    public static void writeWeightInput(@NonNull HealthConnectClient healthConnectClient,
                                        @NonNull Context context,
                                        double weightInput) {
        new Thread(() -> {
            try {
                // Prepare time data
                ZonedDateTime now = ZonedDateTime.now().withNano(0);
                Instant timeInstant = now.toInstant();
                ZoneOffset zoneOffset = now.getOffset();

                CompletableFuture<?> future = HealthConnectWrapper.insertWeightRecord(
                        healthConnectClient,
                        weightInput,
                        timeInstant,
                        zoneOffset
                );

                future.whenComplete((response, exception) -> {
                    if (exception != null) {
                        showToast(context, "Error: " + exception.getMessage());
                        Log.e("TAG", Objects.requireNonNull(exception.getMessage()));
                    } else {
                        showToast(context, "Successfully inserted records");
                    }
                });
            } catch (Exception e) {
                showToast(context, "Error: " + e.getMessage());
            }
        }).start();
    }

    private static void showToast(@NonNull Context context, @NonNull String message) {
        new Handler(Looper.getMainLooper()).post(() ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show());
    }
}
