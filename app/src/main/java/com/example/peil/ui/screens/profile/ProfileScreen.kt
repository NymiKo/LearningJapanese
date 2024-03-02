package com.example.peil.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.peil.R
import com.example.peil.ui.theme.ActiveButtonGrey
import com.example.peil.ui.theme.GreyLightBD
import com.example.peil.ui.theme.White
import com.example.peil.ui.theme.baseBlue
import com.example.peil.ui.view_components.progress.CustomProgress


@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    updateProfile: Boolean,
    onSettingsScreen: () -> Unit
) {
    if (updateProfile) {
        viewModel.getProfile()
    }
    val profile by viewModel.profile.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBarProfile(onSettingsScreen = onSettingsScreen::invoke)
        AvatarAndNickname(
            avatar = profile?.avatar ?: "",
            nickname = profile?.nickname ?: ""
        )
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
                progress = profile?.progress ?: 0.0F,
                color = baseBlue,
                strokeWidth = 6.dp,
                trackColor = GreyLightBD
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "${((profile?.progress ?: 0.0F) * 100).toInt()}%",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarProfile(onSettingsScreen: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile),
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = onSettingsScreen::invoke) {
                Icon(imageVector = Icons.Default.Tune, contentDescription = null)
            }
        }
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun AvatarAndNickname(avatar: String, nickname: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            GlideImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, GreyLightBD, RoundedCornerShape(16.dp))
                    .background(ActiveButtonGrey, RoundedCornerShape(16.dp)),
                model = avatar,
                contentDescription = null,
                loading = placeholder(R.drawable.ic_person),
                failure = placeholder(R.drawable.ic_person),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = nickname,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(hiltViewModel(), false, {})
}