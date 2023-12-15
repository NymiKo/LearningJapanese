package com.example.peil.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.peil.R
import com.example.peil.ui.theme.Blue
import com.example.peil.ui.theme.GreenLight
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.Purple

@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(0.1f to Blue, 1.0f to Purple))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(150.dp)
                    .width(150.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.icon_welcome_screen),
                contentDescription = null
            )
        }
        BottomCard()
    }
}

@Composable
fun BottomCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxHeight(0.25f)
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            CardContent()
        }
    }
}

@Composable
fun CardContent(modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp),
        text = stringResource(id = R.string.learn_japanese),
        textAlign = TextAlign.Center,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenLight
        ),
        content = {
            Text(
                text = stringResource(id = R.string.start_learning),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        },
        onClick = { }
    )
    AlreadyHaveAccountText()
}

@Composable
fun AlreadyHaveAccountText() {
    val annotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = GreyLight
            )
        ) {
            append(stringResource(id = R.string.already_an_account) + " ")
        }
        withStyle(
            SpanStyle(
                textDecoration = TextDecoration.Underline,
                color = GreyLight
            )
        ) {
            append(stringResource(id = R.string.sign_in))
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        text = annotatedString,
        onClick = { },
        style = TextStyle.Default.merge(
            TextStyle(textAlign = TextAlign.Center)
        )
    )
}

@Composable
@Preview()
private fun WelcomeScreenPreview() {
    WelcomeScreen()
}