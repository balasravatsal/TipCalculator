package com.vatsal.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var editBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercentage: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editBaseAmount = findViewById(R.id.editBaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        tvTipPercentage = findViewById(R.id.tvTipPercentage)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)

        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//                Log.i(TAG, "onProgressChange $p1")
                tvTipPercentage.text = "$p1%"
                computeTipAndTotal()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        editBaseAmount.addTextChangedListener (object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
            }



        })

    }
    private fun computeTipAndTotal() {
//        0. to handle the worst case = backSpace all
        if(editBaseAmount.text.isEmpty()){
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }

//        1. get the base and % of tip
        val baseAmount = editBaseAmount.text.toString().toDouble()
        val tipPercent = seekBarTip.progress

//        2. calculate tip and total amount
        val tipAmount = (tipPercent * baseAmount)/100
        val totalBill = tipAmount + baseAmount

//        3. update in ui
        tvTipAmount.text = "%.2f".format(tipAmount)
        tvTotalAmount.text = "%.2f".format(totalBill)
    }
}