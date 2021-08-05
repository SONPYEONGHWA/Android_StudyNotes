package com.example.itscompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.itscompose.R

val NanumSpuare = FontFamily(
    Font(R.font.noto_sans_kr_medium, FontWeight.Normal),
    Font(R.font.noto_sans_kr_regular, FontWeight.Light)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = NanumSpuare,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)
