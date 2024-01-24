package com.example.peil.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.peil.R
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.baseBlue

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(onBack = onBack::invoke) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HeaderCategory(text = R.string.account)
            ChangeNameItem(nameSetting = R.string.name, text = "Nymiko")
            ChangeAvatarItem(avatar = "")
            ChangeNameItem(nameSetting = R.string.email, text = "dimon.kabernik@gmail.com")
            Spacer(modifier = Modifier.weight(1F))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clickable { }
                    .padding(vertical = 16.dp),
                text = stringResource(id = R.string.exit),
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Divider(modifier = Modifier.padding(16.dp))
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.version_build),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.settings),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack::invoke) {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
private fun HeaderCategory(text: Int) {
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
        text = stringResource(id = text),
        fontWeight = FontWeight.Bold,
        color = baseBlue,
        fontSize = 20.sp
    )
}

@Composable
private fun ChangeNameItem(nameSetting: Int, text: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { }
            .padding(vertical = 8.dp)
    ) {
        NameSettingText(name = nameSetting)
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = text,
            color = GreyLight,
            fontSize = 16.sp
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ChangeAvatarItem(avatar: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NameSettingText(name = R.string.avatar)
        Spacer(modifier = Modifier.weight(1F))
        GlideImage(
            modifier = Modifier
                .size(40.dp)
                .background(GreyLight, CircleShape), model = avatar, contentDescription = null,
            loading = placeholder(R.drawable.ic_person),
            failure = placeholder(R.drawable.ic_person)
        )
    }
}

@Composable
private fun NameSettingText(name: Int) {
    Text(
        text = stringResource(id = name),
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreen({})
}