package com.iamkurtgoz.presentation.features.login

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iamkurtgoz.cryptocurrencyapi.presentation.R
import com.iamkurtgoz.presentation.core.components.KButtonBackground
import com.iamkurtgoz.presentation.core.components.KTextFieldBackground
import com.iamkurtgoz.presentation.navigation.ScreenState
import com.iamkurtgoz.presentation.theme.Blue
import com.iamkurtgoz.presentation.theme.ColorTextPrimary
import com.iamkurtgoz.presentation.theme.dimens
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val viewState = viewModel.state.value
    val dialogState = viewModel.dialogState.value

    LaunchedEffect(key1 = "") {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is LoginViewModel.Effect.RouteToBack -> {
                    navController
                        .previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(ScreenState.RouteToBackStackAfterLogin, true)
                    navController.popBackStack()
                }
            }
        }
    }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(modifier = Modifier.padding(horizontal = MaterialTheme.dimens.DP_16)) {
                    Column(
                        modifier = Modifier
                            .padding(all = MaterialTheme.dimens.DP_16),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_lock),
                            contentDescription = "ic_lock",
                            colorFilter = ColorFilter.tint(Blue)
                        )

                        Text(
                            text = stringResource(id = R.string.login),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(top = MaterialTheme.dimens.DP_8),
                            color = ColorTextPrimary(),
                            textAlign = TextAlign.Center
                        )

                        KTextFieldBackground(
                            text = viewModel.emailAddress,
                            placeholder = stringResource(id = R.string.emailAddress),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                capitalization = KeyboardCapitalization.None,
                                autoCorrect = false,
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            )
                        )

                        KTextFieldBackground(
                            text = viewModel.password,
                            placeholder = stringResource(id = R.string.password),
                            rootModifier = Modifier
                                .padding(top = MaterialTheme.dimens.DP_16),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                capitalization = KeyboardCapitalization.None,
                                autoCorrect = false,
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            visualTransformation = PasswordVisualTransformation()
                        )

                        KButtonBackground(
                            text = stringResource(id = R.string.login),
                            rootModifier = Modifier
                                .padding(top = MaterialTheme.dimens.DP_16)
                        ) {
                            viewModel.dispatch(LoginViewModel.Action.Login)
                        }
                    }
                }
            }
        }
    )
}
