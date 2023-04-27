package ir.danialchoopan.danialtube.screen.user

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.danialchoopan.danialtube.ui.componets.DialogBoxLoading
import ir.danialchoopan.danialtube.viewmodels.HomeScreenViewModel

@Composable
fun ValidatePhoneNumberScreen(
    m_context: Context,
    homeScreenViewModel: HomeScreenViewModel
){
    //text box

    var validationCode by remember {
        mutableStateOf("")
    }

    //other variables

    //progressDialog
    var onGoingProgress by remember {
        mutableStateOf(false)
    }
    if (onGoingProgress) {
        DialogBoxLoading()
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "تایید شماره همراه ", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "کد برای شما به شماره : ارسال شده", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = validationCode,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                validationCode = it
            },
            label = { Text(text = "کد ارسالی") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { }
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(), onClick = {
                if (validationCode.isNotEmpty()){
                    onGoingProgress=true

                    //after result
                    onGoingProgress=false
                }else{

                }
            }) {
            Text(text = "ارسال", modifier = Modifier.padding(10.dp))
        }
        Spacer(modifier = Modifier.height(15.dp))
        //کد برای شما ارسال نشد دوباره کلیک کنید
        Text(
            text = "اعتبار کد : ", fontSize = 16.sp,
            modifier = Modifier
                .padding(15.dp)
                .clickable {

                }, color = Color.Blue
        )

    }
}