package edu.metrostate.ics342.mediatracker.ui.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.theme.OnSurface
import edu.metrostate.ics342.mediatracker.theme.PrimaryContainer
import edu.metrostate.ics342.mediatracker.ui.profile.ProfileViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    /*Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(edu.metrostate.ics342.mediatracker.R.string.register_not_implemented),
            style = MaterialTheme.typography.bodyLarge
        )
    }*/
    //var displayName by remember { mutableStateOf("")}
    //var userName by remember { mutableStateOf("")}
    //var email by remember { mutableStateOf("")}
    //var password by remember { mutableStateOf("")}
    //var confirmPassword by remember { mutableStateOf("")}
    //var errorMessage by remember { mutableStateOf<String?>( value = null)}

    val displayName by viewModel.displayName.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val email       by viewModel.email.collectAsState()
    val password    by viewModel.password.collectAsState()
    val confirmPassword    by viewModel.confirmPassword.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val focusManager = LocalFocusManager.current
    //val scrollState = rememberScrollState();
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement   = Arrangement.Center,
        horizontalAlignment   = Alignment.CenterHorizontally
    ) {
        /*Icon(
            imageVector = ,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(48.dp)
        )*/
        Image(painterResource(edu.metrostate.ics342.mediatracker.R.drawable.smart_display),
            contentDescription = "Application Icon",
            modifier = Modifier.size(width = 72.dp, height = 72.dp)
                            .background(PrimaryContainer, RoundedCornerShape(16.dp))
                            .padding(18.dp)

             )

        Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_create_account), style = MaterialTheme.typography.headlineMedium,
            color = Color.Black)

        Spacer(Modifier.height(8.dp))

        Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_tag_line),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)

        Spacer(Modifier.height(40.dp))

        OutlinedTextField(
            value         = displayName,
            onValueChange = viewModel::setDisplayName,//{ displayName = it },
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.display_name_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        OutlinedTextField(
            value         = userName,
            onValueChange = viewModel::setUserName,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.username_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        OutlinedTextField(
            value         = email,
            onValueChange = viewModel::setEmail ,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.email_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )

        OutlinedTextField(
            value         = password,
            onValueChange = viewModel::setPassword,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.password_label)) },
            singleLine    = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(); viewModel.onSignUpClicked() }
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        //SecureTextField
        OutlinedTextField(
            value         = confirmPassword,
            onValueChange = viewModel::setConfirmPassword,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.confirm_password_label)) },
            singleLine    = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(); viewModel.onSignUpClicked() }
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )


        Spacer(Modifier.height(24.dp))

        Button(
            onClick  = {
                            focusManager.clearFocus();
                            viewModel.onSignUpClicked()
                            /*if (confirmPassword != password) {
                                Log.e("RegisterScreen", "ValidationError: passwords don't match! ", )

                            } else if (displayName.isNullOrBlank() || userName.isNullOrBlank() || email.isNullOrBlank() ||
                                       password.isNullOrBlank() || confirmPassword.isNullOrBlank()) {
                                Log.e("ValidationFail","One or more fields is null or empty")
                            } else {
                                Log.d("RegisterScreen","ValidationSuccess!")
                                focusManager.clearFocus(); viewModel.onSignUpClicked()
                            }*/
                       },
            enabled  = true, //TODO change to something meaningful
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            /*if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {*/
                Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.sign_up_button))
            //}
        }
        if (errorMessage != "") {
            Spacer(Modifier.height(8.dp))
            Text(
                errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_already_has_account))
            TextButton(onClick = onNavigateToLogin) {
                Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_log_in_button))
            }
        }

    }
}
