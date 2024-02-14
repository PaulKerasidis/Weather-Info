package com.paulkera.weatherinfo

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.paulkera.weatherinfo.presentation.theme.WeatherInfoTheme
import com.paulkera.weatherinfo.presentation.ui.composables.WeatherCard
import com.paulkera.weatherinfo.presentation.ui.composables.WeatherForecast
import com.paulkera.weatherinfo.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.reLoadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION

            )
        )
        setContent {
            WeatherInfoTheme {

                val systemUiController = rememberSystemUiController()
                systemUiController.isSystemBarsVisible = false

                val weatherInfo by viewModel.state.collectAsStateWithLifecycle()

                val brightDay = Brush.verticalGradient(
                    colors = listOf(Color(0xFF08244F), Color(0xFF0B42AB))
                )

                val night = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2F5AF4), Color(0xFF0FA2AB)),
                )

                Box(
                    modifier = Modifier.fillMaxSize().background(if(weatherInfo.isDay == 0) night else brightDay )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        WeatherCard(
                            weatherViewModel = viewModel,
                            backgroundColor = Color(0xFF2F5AF4)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        WeatherForecast(state = weatherInfo)
                    }
                    if (weatherInfo.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    weatherInfo.error?.let { error ->
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = error,
                                color = Color.White,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                            )
                            Button(
                                onClick = {
                                            viewModel.reLoadWeatherInfo()
                                },
                            ) {
                                Text(
                                    text = "Retry",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


