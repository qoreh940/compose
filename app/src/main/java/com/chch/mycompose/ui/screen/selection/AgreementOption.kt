package com.chch.mycompose.ui.screen.selection

data class AgreementOption(
    val id: Int,
    val name: String,
)

fun getAgreementOptions(): List<AgreementOption> {
    return listOf(
        AgreementOption(1, "첫 번째 약관 동의"),
        AgreementOption(2, "두 번째 약관 동의"),
        AgreementOption(3, "세 번째 약관 동의")
    )
}
