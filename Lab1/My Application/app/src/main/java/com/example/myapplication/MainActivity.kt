package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private var exp: String = ""

    fun buttonClick(view: View) {
        val button = view as Button
        val text = button.text
        if (text == "." && (exp.isNotEmpty() && exp.last() in "+−×÷") || exp.isEmpty()){
            exp+="0"
        }
        if (text == "." && exp.isNotEmpty() && exp.last() == '.'){
            return
        }
        if (exp == "0" && text[0] in '0'..'9') {
            exp = ""
        }
        if (exp == "ERROR"){
            exp = ""
        }
        if (exp.isNotEmpty() && exp.last() in "+−×÷" && text in "+−×÷") {
            exp = exp.dropLast(1)
        }
        exp += text
        val equationText = findViewById<TextView>(R.id.equation_text_view)
        equationText.text = exp
        if (equationText.text.length <= 8){
            equationText.textSize = 80F
        } else if (equationText.text.length <= 13){
            equationText.textSize = 50F
        }else {
            equationText.textSize = 30F
        }
        val resultText = findViewById<TextView>(R.id.result_text_view)
        if (text !in "+−×÷"){
            resultText.text = calculate("$exp=")
        }else{
            resultText.text = ""
        }
    }

    fun setResult(view: View){
        val resultText = findViewById<TextView>(R.id.result_text_view)
        val equationText = findViewById<TextView>(R.id.equation_text_view)
        equationText.text = resultText.text
        exp = equationText.text as String

    }

    fun eraseLast(view: View){
        exp = exp.dropLast(1)
        val equationText = findViewById<TextView>(R.id.equation_text_view)
        equationText.text = exp
        val resultText = findViewById<TextView>(R.id.result_text_view)
        if (exp.isEmpty()) resultText.text = ""
        else resultText.text = calculate("$exp=")
    }

    fun calculate(expression: String): String{
        val numbers = ArrayList<Double>();
        var curNum = ""
        var Ops = ""
        try {
            for (i in expression.indices){
                if (expression[i] in '0'..'9' || expression[i] == '.'){
                    curNum += expression[i]
                }else {
                    if (Ops.isEmpty()){
                        numbers.add(curNum.toDouble())
                        curNum = ""
                    }else if (Ops.last() == '×'){

                        numbers.set(numbers.size-1, numbers[numbers.size-1] * curNum.toDouble())
                        curNum = ""
                        Ops = Ops.dropLast(1)
                    }else if (Ops.last() == '÷'){
                        numbers.set(numbers.size-1, numbers[numbers.size-1] / curNum.toDouble())
                        curNum = ""
                        Ops = Ops.dropLast(1)
                    }else{
                        numbers.add(curNum.toDouble())
                        curNum = ""
                    }

                    Ops += expression[i]
                    println(Ops)
                }

            }
            var ans = numbers[0]
            for (i in 1 until numbers.size){
                if (Ops[i-1] == '+'){
                    ans += numbers[i]
                }else{
                    ans -= numbers[i]
                }
            }
            println(ans)
            var ansStr = ans.toString()
            if (ansStr == "Infinity" || ansStr == "NaN") return "ERROR"
            if (ansStr.length >= 2 && ansStr.takeLast(2) == ".0"){
                return ansStr.dropLast(2)
            }
            return ansStr
        }catch (e: Exception){
            return "ERROR"
        }

    }
}