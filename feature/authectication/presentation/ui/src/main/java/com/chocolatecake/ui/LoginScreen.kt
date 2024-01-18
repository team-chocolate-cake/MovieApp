package com.chocolatecake.ui

import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chocolatecake.ui.auth.R
import com.chocolatecake.viewmodel.LoginInterActionsListener
import com.chocolatecake.viewmodel.LoginUiState

@Composable
internal fun LoginScreenContent(
    state: LoginUiState,
    listener: LoginInterActionsListener,
) {
    var isKeyboardVisible by remember { mutableStateOf(false) }
    ObserveKeyboardAppearanceChange { isKeyboardVisible = it }

    Box(
        Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = com.chocolatecake.bases.R.color.background)
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_cover_photo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.79f)
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    color = colorResource(id = com.chocolatecake.bases.R.color.login_form_background),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(vertical = 32.dp, horizontal = 16.dp)
        ) {

            BodyText(text = stringResource(id = com.chocolatecake.bases.R.string.username))
            BasicTextField(
                value = state.userName,
                onValueChange = listener::onUsernameChanged,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = com.chocolatecake.bases.R.color.card),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight
                    )
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                maxLines = 1,
                textStyle = bodyTextStyle,
                cursorBrush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = com.chocolatecake.bases.R.color.on_background_60),
                        colorResource(id = com.chocolatecake.bases.R.color.on_background_60),
                    )
                ),
            )
            BodyText(
                text = stringResource(id = com.chocolatecake.bases.R.string.password),
                modifier = Modifier.padding(top = 16.dp)
            )
            BasicTextField(
                value = state.password,
                onValueChange = listener::onPasswordChanged,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = com.chocolatecake.bases.R.color.card),
                        shape = RoundedCornerShape(50.dp)
                    )
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight
                    )
                    .padding(vertical = 20.dp, horizontal = 16.dp),
                maxLines = 1,
                textStyle = bodyTextStyle,
                cursorBrush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = com.chocolatecake.bases.R.color.on_background_60),
                        colorResource(id = com.chocolatecake.bases.R.color.on_background_60),
                    )
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

        }
    }
}


val bodyTextStyle
    @Composable get() = TextStyle(
        color = colorResource(com.chocolatecake.bases.R.color.on_background_60),
        fontSize = 12.sp,
        fontFamily = FontFamily(
            Font(resId = com.chocolatecake.bases.R.font.montserrat_regular)
        ),
        fontWeight = FontWeight.Normal
    )

@Composable
private fun ObserveKeyboardAppearanceChange(onChange: (isKeyboardVisible: Boolean) -> Unit) {
    val view = LocalView.current

    DisposableEffect(Unit) {
        val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            view.getWindowVisibleDisplayFrame(rect)

            val screenHeight = view.rootView.height
            val keyboardHeight = screenHeight - rect.bottom

            val isKeyboardVisible = keyboardHeight > screenHeight * 0.15
            onChange(isKeyboardVisible)
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(globalLayoutListener)
        }
    }
}

@Composable
private fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    style: (TextStyle) -> TextStyle = { it },
) {
    val textStyle = style(bodyTextStyle)
    Text(text = text, style = textStyle, modifier = modifier)
}

@Preview(backgroundColor = 0xFF000000)
@Composable
private fun LoginPreview() {
    var state by remember { mutableStateOf(LoginUiState()) }

    LoginScreenContent(state = state, listener = object : LoginInterActionsListener {
        override fun onUsernameChanged(username: String) {
            state = state.copy(userName = username)
        }

        override fun onPasswordChanged(password: String) {
            state = state.copy(password = password)
        }

    })
}
