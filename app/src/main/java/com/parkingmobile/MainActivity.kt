package com.parkingmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BatteryChargingFull
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.EvStation
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material.icons.outlined.PinDrop
import androidx.compose.material.icons.outlined.Power
import androidx.compose.material.icons.outlined.Route
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parkingmobile.ui.theme.ParkingMobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkingMobileTheme {
                ParkingApp()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ParkingApp() {
    val pagerState = rememberPagerState(pageCount = { 8 })

    Scaffold(
        bottomBar = {
            NavigationBar {
                val icons = listOf(
                    Icons.Outlined.DirectionsCar,
                    Icons.Outlined.EvStation,
                    Icons.Outlined.PinDrop,
                    Icons.Outlined.CreditCard
                )
                var selected by remember { mutableIntStateOf(0) }
                icons.forEachIndexed { index, icon ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = { selected = index },
                        icon = { Icon(icon, contentDescription = null) }
                    )
                }
            }
        }
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(20.dp)
        ) { page ->
            when (page) {
                0 -> ChargingStatusScreen()
                1 -> OnboardingScreen("Hassle-Free Parking", "Seamlessly find, reserve, and pay for parking.")
                2 -> DashboardScreen()
                3 -> OnboardingScreen("Auto Payments", "Automatic payments for a seamless parking experience.")
                4 -> BrandScreen()
                5 -> PaymentScreen()
                6 -> OnboardingScreen("Charging Solution", "Effortless charging at your fingertips.")
                else -> VehicleScreen()
            }
        }
    }
}

@Composable
private fun ChargingStatusScreen() {
    ScreenCard("Charging 71%", "Charging rate 20 kW • 16 mins elapsed", Icons.Outlined.BatteryChargingFull)
}

@Composable
private fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        HeroCard("Effortless Parking & Charging")
        StatsCard()
        ScreenCard("Nearest Station", "City parking garage · 162 St", Icons.Outlined.Route)
    }
}

@Composable
private fun PaymentScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ScreenCard("Your Balance", "$2,745.79", Icons.Outlined.Paid)
        ScreenCard("Charging Cost", "$54.00", Icons.Outlined.CreditCard)
        AssistChip(onClick = {}, label = { Text("GPay ••••0528") })
    }
}

@Composable
private fun BrandScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.Power, null, tint = Color.Black)
            }
            Spacer(Modifier.height(8.dp))
            Text("ParkVolt", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
private fun VehicleScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ScreenCard("Hassle-Free Parking", "Mercedes-Benz coupe", Icons.Outlined.DirectionsCar)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            VehicleTypeChip("Car", true)
            VehicleTypeChip("Bike", false)
            VehicleTypeChip("Truck", false)
        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Add Car") }
    }
}

@Composable
private fun VehicleTypeChip(title: String, selected: Boolean) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            color = if (selected) Color.Black else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun OnboardingScreen(title: String, subtitle: String) {
    Card(
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(MaterialTheme.colorScheme.surfaceContainerLowest, MaterialTheme.colorScheme.surfaceContainerHigh)
                    )
                )
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(subtitle, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(18.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(3) { idx ->
                        Box(
                            modifier = Modifier
                                .height(6.dp)
                                .width(if (idx == 1) 26.dp else 14.dp)
                                .background(
                                    if (idx == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                                    RoundedCornerShape(16.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HeroCard(title: String) {
    Card(shape = RoundedCornerShape(24.dp)) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("Battery 73% • Remaining 83km")
        }
    }
}

@Composable
private fun StatsCard() {
    Card(shape = RoundedCornerShape(24.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Stat("Battery", "73%")
            Stat("Remaining", "83km")
        }
    }
}

@Composable
private fun Stat(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelLarge)
        Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ScreenCard(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Icon(icon, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(10.dp))
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(6.dp))
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Start)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParkingAppPreview() {
    ParkingMobileTheme {
        ParkingApp()
    }
}
