package ir.danialchoopan.danialtube.screen.user

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.danialchoopan.danialtube.data.api.requests.user.UserAuthRequest
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading

@Composable
fun EditUserDataScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "",
                            modifier = Modifier.scale(-1f, 1f)
                        )
                    }
                },
                title = { Text("ویرایش اطلاعات کاربری") })
        },
        content = {

            //context
            val m_context = LocalContext.current

            var onGoingProgress by remember {
                mutableStateOf(false)
            }

            if (onGoingProgress) {
                DialogBoxLoading()
            }

            //text edits values

            var user_saved_data by remember {
                mutableStateOf(
                    m_context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
                )
            }

            var userEmailEditTextBox by remember {
                mutableStateOf(user_saved_data.getString("email", "").toString())
            }

            var userNameEditTextBox by remember {
                mutableStateOf(user_saved_data.getString("name", "").toString())
            }

            var oldPasswordEditTextBox by remember {
                mutableStateOf("")
            }

            var passwordEditTextBox by remember {
                mutableStateOf("")
            }

            var rePasswordEditTextBox by remember {
                mutableStateOf("")
            }

            var oldPasswordEditTextBoxError by remember {
                mutableStateOf(false)
            }

            var passwordEditTextBoxError by remember {
                mutableStateOf(false)
            }

            var rePasswordEditTextBoxError by remember {
                mutableStateOf(false)
            }

            var userEmailEditTextBoxError by remember {
                mutableStateOf(false)
            }

            var userNameEditTextBoxError by remember {
                mutableStateOf(false)
            }

            val userAuthRequest = UserAuthRequest(LocalContext.current)

            val verticalScroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScroll)
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {


                //edit
                OutlinedTextField(
                    value = userNameEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        userNameEditTextBox = it
                        userNameEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = "نام کاربری") },
                    isError = userNameEditTextBoxError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                )//end text edit

                //edit
                OutlinedTextField(
                    value = userEmailEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        userEmailEditTextBox = it
                        userEmailEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = "پست الکترونیک ") },
                    isError = userEmailEditTextBoxError,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {

                        }
                    ),
                )//end text edit

                //password
                OutlinedTextField(
                    value = oldPasswordEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        oldPasswordEditTextBox = it
                        oldPasswordEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = " رمز عبور فعلی") },
                    isError = oldPasswordEditTextBoxError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()

                )//end text edit

                //password
                OutlinedTextField(
                    value = passwordEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        passwordEditTextBox = it
                        passwordEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = " رمز عبور جدید") },
                    isError = passwordEditTextBoxError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                ) //end text edit


                //password re
                OutlinedTextField(
                    value = rePasswordEditTextBox,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        rePasswordEditTextBox = it
                        rePasswordEditTextBoxError = false
                    },
                    leadingIcon =
                    {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = ""
                        )
                    },
                    label = { Text(text = "تکرار رمز عبور جدید") },
                    isError = rePasswordEditTextBoxError,

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = PasswordVisualTransformation()
                )//end text edit
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(), onClick = {

                        passwordEditTextBoxError = passwordEditTextBox.isEmpty()
                        oldPasswordEditTextBoxError = oldPasswordEditTextBox.isEmpty()
                        rePasswordEditTextBoxError = rePasswordEditTextBox.isEmpty()
                        userEmailEditTextBoxError = userEmailEditTextBox.isEmpty()
                        userNameEditTextBoxError = userNameEditTextBox.isEmpty()

                        if (
                            userEmailEditTextBox.isNotEmpty() &&
                            oldPasswordEditTextBox.isNotEmpty() &&
                            passwordEditTextBox.isNotEmpty() &&
                            rePasswordEditTextBox.isNotEmpty()
                        ) {
                            if (passwordEditTextBox == rePasswordEditTextBox) {

                                onGoingProgress = true
                                userAuthRequest.userEditPasswordEdit(
                                    userNameEditTextBox,
                                    userEmailEditTextBox,
                                    oldPasswordEditTextBox,
                                    passwordEditTextBox,
                                    {
                                        navController.popBackStack()
                                        navController.navigate("home")
                                        onGoingProgress = false
                                        Toast.makeText(
                                            m_context,
                                            "ویرایش اطلاعات کاربری با موفقیت انجام شد ",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    },

                                    {
                                        onGoingProgress = false
                                        //failed
                                        Toast.makeText(
                                            m_context,
                                            "رمزعبور فعلی شما اشتباه است ",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    })


//                              //clear text boxs
                                oldPasswordEditTextBox = ""
                                passwordEditTextBox = ""
                                rePasswordEditTextBox = ""
                            } else {
                                Toast.makeText(
                                    m_context,
                                    "رمز عبور انتخابی شما با تکرار آن برار نیست ",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                m_context,
                                "لطفا ورودی های خود را برسی کنید - ورودی خالی مجاز نیست ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }) {
                    Text(text = "ویرایش", modifier = Modifier.padding(10.dp), fontSize = 19.sp)
                }

                Spacer(modifier = Modifier.height(250.dp))


            }
        }
    )
}