package ir.danialchoopan.danialtube.ui.componets
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowUp
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//
//@Composable
//fun PasswordOutlineField(
//    value: MutableState<String>,
//    onValueChange: (String) -> Unit,
//    label: String,
//    placeholder: String,
//    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//    isPasswordVisible: Boolean = false,
//    onPasswordVisibilityToggle: () -> Unit = {},
//) {
////    val visualTransformation =
////        if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
//    OutlinedTextField(
//        value = value,
//        onValueChange = {
//
//        },
//        label = { Text(label) },
//        placeholder = { Text(placeholder) },
//        visualTransformation = visualTransformation,
//        keyboardOptions = keyboardOptions,
//        trailingIcon = {
//            IconButton(onClick = onPasswordVisibilityToggle) {
//                Icon(
//                    imageVector = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
//                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
//                )
//            }
//        },
//    )
//
//}