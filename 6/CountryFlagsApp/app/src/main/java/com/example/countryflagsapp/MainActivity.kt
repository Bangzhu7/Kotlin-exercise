// MainActivity.kt

package com.example.countryflagsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countryflagsapp.ui.theme.CountryFlagsAppTheme
import com.example.countryflagsapp.ui.theme.GeoFamily // Import our custom font

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryFlagsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CountryListScreen()
                }
            }
        }
    }
}

@Composable
fun CountryListScreen() {
    // Column stacks its children vertically.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Here we call our reusable CountryCard four times.
        CountryCard(countryName = "Japan", flagResId = R.drawable.flag_japan)
        Spacer(modifier = Modifier.height(16.dp))
        CountryCard(countryName = "Brazil", flagResId = R.drawable.flag_brazil)
        Spacer(modifier = Modifier.height(16.dp))
        CountryCard(countryName = "Canada", flagResId = R.drawable.flag_canada)
        Spacer(modifier = Modifier.height(16.dp))
        CountryCard(countryName = "Nigeria", flagResId = R.drawable.flag_nigeria)
    }
}

// Our reusable composable for a single country card.
@Composable
fun CountryCard(countryName: String, flagResId: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        // Custom shape for the card
        shape = RoundedCornerShape(24.dp),
        // Custom colors for the card
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Row arranges the flag and text horizontally
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = flagResId),
                contentDescription = "Flag of $countryName",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    // Clip the image into a circle
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = countryName,
                // Apply our custom font here!
                fontFamily = GeoFamily,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CountryFlagsAppTheme {
        CountryListScreen()
    }
}