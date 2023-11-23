package uz.demo.keyboardhero.utils.filter

object InputFilterRegex {
    val DecimalInput by lazy { Regex("^(\\d*\\.?)+\$") }
    val PhoneInput by lazy { Regex("^\\d{0,15}\$") }
    val WithoutCyrillicInput by lazy { Regex("^[^ЁёА-я]*\$") }
    val NumbersAndLatin by lazy { Regex("^[a-zA-Z0-9]*\$") }

    fun decimalInputWithLimitation(whole: Int = 20, decimals: Int = 2): Regex {
        return Regex("^(\\d{0,$whole})?(\\.\\d{0,$decimals})?\$")
    }
}
