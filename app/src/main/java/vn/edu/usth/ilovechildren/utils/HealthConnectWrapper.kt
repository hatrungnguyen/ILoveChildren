package vn.edu.usth.ilovechildren.utils

import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.WeightRecord
import androidx.health.connect.client.response.InsertRecordsResponse
import androidx.health.connect.client.units.Mass
import java.time.Instant
import java.time.ZoneOffset
import java.util.concurrent.CompletableFuture
import kotlinx.coroutines.*

object HealthConnectWrapper {
    @OptIn(DelicateCoroutinesApi::class)
    @JvmStatic
    fun insertWeightRecord(
        healthConnectClient: HealthConnectClient,
        weightInKg: Double,
        timeInstant: Instant,
        zoneOffset: ZoneOffset
    ): CompletableFuture<InsertRecordsResponse> {
        val future = CompletableFuture<InsertRecordsResponse>()
        val weightRecord = WeightRecord(
            weight = Mass.kilograms(weightInKg),
            time = timeInstant,
            zoneOffset = zoneOffset
        )
        val records = listOf(weightRecord)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = healthConnectClient.insertRecords(records)
                future.complete(response)
            } catch (e: Exception) {
                future.completeExceptionally(e)
            }
        }

        return future
    }
}
