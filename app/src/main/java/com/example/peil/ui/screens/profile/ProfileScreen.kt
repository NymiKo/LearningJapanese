package com.example.peil.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.peil.R
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.view_components.progress.CustomProgress

@Composable
fun ProfileScreen(

) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarProfile()
        AvatarAndNickname(avatar = "", nickname = "Nymiko")
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.knowledge_language),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1F))
            Image(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(25.dp)
                    .clip(CircleShape)
                    .border(0.1.dp, GreyLightBD, CircleShape),
                painter = painterResource(id = R.drawable.flag_japan),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.japan).uppercase(),
                color = GreyLightBD,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            CustomProgress(
                modifier = Modifier.size(150.dp),
                progress = 0.5F,
                color = baseBlue,
                strokeWidth = 6.dp
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "50%",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarProfile() {
    TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.profile),
            fontWeight = FontWeight.Bold
        )
    })
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun AvatarAndNickname(avatar: String, nickname: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp)
                .clip(CircleShape)
                .border(1.dp, GreyLightBD, CircleShape),
            model = avatar,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = nickname,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}