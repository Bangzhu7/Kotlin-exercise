// MainActivity.kt

package com.example.productbannerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productbannerapp.ui.theme.ProductBannerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductBannerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductBanner() // This is our main screen function
                }
            }
        }
    }
}

@Composable
fun ProductBanner() {
    // Box is a layout that lets you stack elements on top of each other. Perfect for a background image.
    Box(
        modifier = Modifier.fillMaxSize(), // Make the box take up the whole screen
        contentAlignment = Alignment.Center // Center all its children by default
    ) {
        // 1. The Background Image
        Image(
            // Use the painterResource to find our image in the 'drawable' folder.
            // Replace 'R.drawable.smartwatch_bg' with your actual image file name.
            painter = painterResource(id = R.drawable.auroring),
            contentDescription = "Background image of the Outlier Pro Smartwatch",
            modifier = Modifier.fillMaxSize(), // Make the image fill the entire box
            contentScale = ContentScale.Crop // Crop the image to fit without stretching
        )

        // 2. The Text Content, arranged vertically in a Column
        Column(
            // Center the text horizontally within the column
            horizontalAlignment = Alignment.CenterHorizontally,
            // Arrange the text vertically in the center of the screen
            verticalArrangement = Arrangement.Center
        ) {
            // Product Name
            Text(
                text = "ouro ring",
                fontSize = 48.sp, // Large font size
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // Add a small space between the product name and company name
            Spacer(modifier = Modifier.height(8.dp))

            // Company Name
            Text(
                text = "by ouro",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )

            // Add a larger space before the contact info
            Spacer(modifier = Modifier.height(32.dp))

            // Contact Info
            Text(
                text = "Contact: info@auro.fi",
                fontSize = 16.sp,
                color = Color.LightGray // A slightly different color to de-emphasize
            )
        }
    }
}

// The Preview function lets you see your design in Android Studio without running the app.
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductBannerAppTheme {
        ProductBanner()
    }
}