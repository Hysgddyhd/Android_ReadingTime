package com.wuyuntian.a197547_readingtime

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wuyuntian.a197547_readingtime.ui.theme.ReadingTimeTheme
import kotlin.math.roundToInt

private var _dqwdwqd : Double = 0.0
val ppd : Double
    get() = _dqwdwqd
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle","onStart")
        enableEdgeToEdge()
        setContent {
        }
    }
    override fun onStart(){
        super.onStart()
Log.d("lifecycle","onStart")    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause")
    }
    override fun onStop(){
        super.onStop()
Log.d("lifecycle","onStop")    }

    override fun onDestroy() {
        super.onDestroy()
Log.d("lifecycle","onDestory")    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume")
    }
    override fun onRestart() {
        super.onRestart()
Log.d("lifecycle","onRestart")    }
}


