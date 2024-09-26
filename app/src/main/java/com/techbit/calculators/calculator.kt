package com.techbit.calculators

import java.util.Stack
import kotlin.math.pow
import kotlin.math.sqrt


object EvaluateExpression {
    fun evaluate(expression: String): String {
        try {
            val expressionArray =infixToPostfix(expression)
            val value = evaluatePostfix(expressionArray)
            if (value%1 == 0.0){
                return value.toInt().toString()
            }
            return  value.toString()
        }
        catch (a:Exception) {
             return ""
        }
    }


    private fun evaluatePostfix(postfix: List<String>): Double {
        val stack = Stack<Double>()

        for (token in postfix) {
            if (token.matches("\\d+(\\.\\d*)?".toRegex())) {
                stack.push(token.toDouble())
            } else {
                val operand2 = stack.pop()
                val operand1 = stack.pop()
                val result = when (token) {
                    "+" -> operand1 + operand2
                    "-" -> operand1 - operand2
                    "*" -> operand1 * operand2
                    "/" -> operand1 / operand2
                    "^" -> operand1.pow(operand2)
                    "sqrt" -> sqrt(operand1)
                    else -> throw IllegalArgumentException("Invalid operator")
                }
                stack.push(result)
            }
        }

        return stack.pop()
    }
    // to postfix
    private fun infixToPostfix(infix: String): List<String> {
        val stack = Stack<Char>()
        val postfix = mutableListOf<String>()
        var number = ""

        for (ch in infix) {
            when (ch) {
                in '0'..'9' -> number += ch
                '.' -> number += ch
                else -> {
                    if (number.isNotEmpty()) {
                        postfix.add(number)
                        number = ""
                    }
                    when (ch) {
                        '(' -> stack.push(ch)
                        ')' -> {
                            while (stack.isNotEmpty() && stack.peek() != '(') {
                                postfix.add(stack.pop().toString())
                            }
                            stack.pop()
                        }
                        '+', '-', '*', '/', '^' -> {
                            while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                                postfix.add(stack.pop().toString())
                            }
                            stack.push(ch)
                        }
                    }
                }
            }
        }

        if (number.isNotEmpty()) {
            postfix.add(number)
        }

        while (stack.isNotEmpty()) {
            postfix.add(stack.pop().toString())
        }

        return postfix
    }

    private fun precedence(ch: Char): Int {
        return when (ch) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^' -> 3
            else -> -1
        }
    }

}