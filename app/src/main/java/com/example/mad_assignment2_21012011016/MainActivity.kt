package com.example.mad_assignment2_21012011016
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sourceCurrencySpinner: Spinner
    private lateinit var targetCurrencySpinner: Spinner
    private lateinit var amountEditText: EditText
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView

    private val exchangeRates = mapOf(
        "USD" to mapOf("USD" to 1.0, "EUR" to 0.85, "JPY" to 110.0, "INR" to 74.0, "GBP" to 0.75, "CAD" to 1.28, "AUD" to 1.36, "CNY" to 6.39),
        "EUR" to mapOf("USD" to 1.18, "EUR" to 1.0, "JPY" to 130.0, "INR" to 88.0, "GBP" to 0.91, "CAD" to 1.56, "AUD" to 1.66, "CNY" to 7.85),
        "JPY" to mapOf("USD" to 0.009, "EUR" to 0.0077, "JPY" to 1.0, "INR" to 0.74, "GBP" to 0.0068, "CAD" to 0.011, "AUD" to 0.011, "CNY" to 0.055),
        "INR" to mapOf("USD" to 0.014, "EUR" to 0.011, "JPY" to 1.35, "INR" to 1.0, "GBP" to 0.011, "CAD" to 0.019, "AUD" to 0.02, "CNY" to 0.095),
        "GBP" to mapOf("USD" to 1.34, "EUR" to 1.10, "JPY" to 146.48, "INR" to 95.35, "GBP" to 1.0, "CAD" to 1.71, "AUD" to 1.82, "CNY" to 8.62),
        "CAD" to mapOf("USD" to 0.78, "EUR" to 0.64, "JPY" to 85.22, "INR" to 53.77, "GBP" to 0.58, "CAD" to 1.0, "AUD" to 1.07, "CNY" to 5.08),
        "AUD" to mapOf("USD" to 0.73, "EUR" to 0.61, "JPY" to 80.88, "INR" to 50.73, "GBP" to 0.55, "CAD" to 0.93, "AUD" to 1.0, "CNY" to 4.76),
        "CNY" to mapOf("USD" to 0.16, "EUR" to 0.13, "JPY" to 17.62, "INR" to 10.52, "GBP" to 0.12, "CAD" to 0.20, "AUD" to 0.21, "CNY" to 1.0)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sourceCurrencySpinner = findViewById(R.id.sourceCurrencySpinner)
        targetCurrencySpinner = findViewById(R.id.targetCurrencySpinner)
        amountEditText = findViewById(R.id.amountEditText)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)

        val currencyAdapter = ArrayAdapter.createFromResource(this, R.array.currencies, android.R.layout.simple_spinner_item)
        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceCurrencySpinner.adapter = currencyAdapter
        targetCurrencySpinner.adapter = currencyAdapter

        convertButton.setOnClickListener {
            val sourceCurrency = sourceCurrencySpinner.selectedItem.toString()
            val targetCurrency = targetCurrencySpinner.selectedItem.toString()
            val amount = amountEditText.text.toString().toDoubleOrNull()

            if (amount != null) {
                val convertedAmount = amount * exchangeRates[sourceCurrency]?.get(targetCurrency)!!

                resultTextView.text = "Converted Amount: $convertedAmount $targetCurrency"
            } else {
                resultTextView.text = "Please enter a valid amount."
            }
        }
    }
}
