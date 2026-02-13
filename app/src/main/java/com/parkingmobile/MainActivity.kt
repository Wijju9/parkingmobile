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
import androidx.compose.foundation.layout.weight
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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Power
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
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
                ParkingFlowApp()
            }
        }
    }
}

private enum class AppScreen {
    Splash, SliderOne, SliderTwo, SliderThree, Login, Dashboard, AddOption, Payment, Charging
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ParkingFlowApp() {
    val screens = AppScreen.entries
    val pagerState = rememberPagerState(pageCount = { screens.size })

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navItems = listOf(Icons.Outlined.Power, Icons.Outlined.DirectionsCar, Icons.Outlined.CreditCard, Icons.Outlined.EvStation)
                var selectedIndex by remember { mutableIntStateOf(0) }
                navItems.forEachIndexed { index, icon ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = { Icon(icon, contentDescription = null) }
                    )
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(18.dp)
        ) { page ->
            when (screens[page]) {
                AppScreen.Splash -> SplashScreen()
                AppScreen.SliderOne -> SliderScreen("Hassle-Free Parking", "Seamlessly find, reserve and pay for parking.", 0)
                AppScreen.SliderTwo -> SliderScreen("Charging Solution", "Effortless charging solutions at your fingertips.", 1)
                AppScreen.SliderThree -> SliderScreen("Auto Payments", "Automatic cashless payments for seamless parking.", 2)
                AppScreen.Login -> LoginScreen()
                AppScreen.Dashboard -> DashboardScreen()
                AppScreen.AddOption -> AddOptionScreen()
                AppScreen.Payment -> PaymentScreen()
                AppScreen.Charging -> ChargingScreen()
            }
        }
    }
}

@Composable
private fun SplashScreen() {
    Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(24.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Outlined.Power, contentDescription = null, tint = Color.Black)
                }
                Spacer(Modifier.height(12.dp))
                Text("ParkVolt", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, MaterialTheme.colorScheme.surfaceContainerHigh)
                        )
                    )
            )
        }
    }
}

@Composable
private fun SliderScreen(title: String, subtitle: String, activeDot: Int) {
    Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            )

            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF121A2A))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(title, color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text(subtitle, color = Color(0xFFC6CEDD), textAlign = TextAlign.Center)
                    Spacer(Modifier.height(14.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        repeat(3) { index ->
                            Box(
                                modifier = Modifier
                                    .height(6.dp)
                                    .width(if (index == activeDot) 24.dp else 12.dp)
                                    .background(
                                        if (index == activeDot) MaterialTheme.colorScheme.primary else Color(0xFF8A93A6),
                                        RoundedCornerShape(12.dp)
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginScreen() {
    Card(shape = RoundedCornerShape(28.dp), modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Welcome Back", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(6.dp))
            Text("Login to continue parking and charging", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("Email") }, leadingIcon = { Icon(Icons.Outlined.Person, null) })
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth(), label = { Text("Password") }, leadingIcon = { Icon(Icons.Outlined.Lock, null) })
            Spacer(Modifier.height(18.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Login") }
        }
    }
}

@Composable
private fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ScreenBlock("Effortless Parking & Charging", "Battery 73% • Remaining 83km", Icons.Outlined.DirectionsCar)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            StatCard("Battery", "73%")
            StatCard("Remaining", "83km")
        }
        ScreenBlock("Nearest Station", "Oslo city parking garage, Stenersgata 162", Icons.Outlined.LocationOn)
    }
}

@Composable
private fun AddOptionScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ScreenBlock("Hassle-Free Parking", "Mercedes-Benz coupe", Icons.Outlined.DirectionsCar)
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            TypeChip("Car", true)
            TypeChip("Bike", false)
            TypeChip("Truck", false)
        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("Add Car")
        }
    }
}

@Composable
private fun PaymentScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ScreenBlock("Your Balance", "$2,745.79", Icons.Outlined.Payments)
        ScreenBlock("Charging Cost", "$54.00", Icons.Outlined.CreditCard)
        ScreenBlock("Payment Method", "Mastercard •••• 8435", Icons.Outlined.CreditCard)
    }
}

@Composable
private fun ChargingScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ScreenBlock("Charging 71%", "Mercedes-Benz coupe • Price: $54.00", Icons.Outlined.BatteryChargingFull)
        Card(shape = RoundedCornerShape(24.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column { Text("Charging Rate", style = MaterialTheme.typography.labelLarge); Text("20 kw", fontWeight = FontWeight.Bold) }
                Column { Text("Time Elapsed", style = MaterialTheme.typography.labelLarge); Text("16 mins", fontWeight = FontWeight.Bold) }
            }
        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Stop Charging") }
    }
}

@Composable
private fun TypeChip(label: String, selected: Boolean) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainerLow
        )
    ) {
        Text(
            label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            color = if (selected) Color.Black else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun StatCard(title: String, value: String) {
    Card(modifier = Modifier.weight(1f), shape = RoundedCornerShape(20.dp)) {
        Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(title, style = MaterialTheme.typography.labelLarge)
            Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun ScreenBlock(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    ParkingMobileTheme {
        ParkingFlowApp()
    }
}
