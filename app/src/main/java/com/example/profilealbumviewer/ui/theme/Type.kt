package com.example.profilealbumviewer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.profilealbumviewer.R

val Jakarta = FontFamily(
    Font(R.font.plusjakartasans_regular, weight = FontWeight.Normal)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.SemiBold,
        fontSize = Dimens().TextSize18,
        lineHeight = Dimens().TextHeight28
    ),

    titleMedium = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens().TextSize16,
        lineHeight = Dimens().TextHeight24
    ),

    titleSmall = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens().TextSize14,
        lineHeight = Dimens().TextHeight20
    ),

    bodyLarge = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.SemiBold,
        fontSize = Dimens().TextSize16,
        lineHeight = Dimens().TextHeight24
    ),

    bodyMedium = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens().TextSize16,
        lineHeight = Dimens().TextHeight20
    ),

    bodySmall = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens().TextSize12,
        lineHeight = Dimens().TextHeight16
    ),

    labelLarge = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.SemiBold,
        fontSize = Dimens().TextSize14,
        lineHeight = Dimens().TextHeight20
    ),

    labelMedium = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens().TextSize12,
        lineHeight = Dimens().TextHeight16
    ),

    labelSmall = TextStyle(
        fontFamily = Jakarta,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens().TextSize11,
        lineHeight = Dimens().TextHeight14
    )
)