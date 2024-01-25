package com.example.peil.ui.screens.settings

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.peil.R
import com.example.peil.ui.theme.GreyLight
import com.example.peil.ui.theme.baseBlue
import com.example.peil.util.getFileName
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onChangeNicknameScreen: (nickname: String) -> Unit,
    onBack: () -> Unit
) {
    val profile = viewModel.profile.observeAsState()

    Scaffold(
        topBar = { TopAppBar(onBack = onBack::invoke) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            HeaderCategory(text = R.string.account)
            ChangeNameItem(
                nameSetting = R.string.name,
                text = profile.value?.nickname ?: "",
                onChangeNicknameScreen = onChangeNicknameScreen::invoke
            )
            ChangeAvatarItem(
                avatar = profile.value?.avatar ?: "",
                loadAvatar = { file -> viewModel.loadAvatar(file) })
            ChangeNameItem(
                nameSetting = R.string.email,
                text = profile.value?.email ?: "",
                onChangeNicknameScreen = {})
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
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = GreyLight
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
private fun ChangeNameItem(nameSetting: Int, text: String, onChangeNicknameScreen: (nickname: String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onChangeNicknameScreen(text) }
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
private fun ChangeAvatarItem(avatar: String, loadAvatar: (file: File) -> Unit) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { launcher.launch("image/*") }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NameSettingText(name = R.string.avatar)
        Spacer(modifier = Modifier.weight(1F))
        GlideImage(
            modifier = Modifier
                .size(40.dp)
                .background(GreyLight, CircleShape)
                .clip(CircleShape), model = imageUri ?: avatar, contentDescription = null,
            loading = placeholder(R.drawable.ic_person),
            failure = placeholder(R.drawable.ic_person),
            contentScale = ContentScale.Crop
        )
    }

    imageUri?.let { uri ->
        val parcelFileDescriptor =
            LocalContext.current.contentResolver.openFileDescriptor(uri, "r", null)
        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        val file =
            File(
                LocalContext.current.cacheDir,
                LocalContext.current.contentResolver.getFileName(fileUri = uri)
            )
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        loadAvatar(file)
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
    SettingsScreen(hiltViewModel(), {}, {})
}