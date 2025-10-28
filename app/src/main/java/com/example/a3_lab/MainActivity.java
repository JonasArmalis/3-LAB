package com.example.a3_lab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentText = "0";
    private Double firstOperand = null;
    private String pendingOperator = null;
    private boolean justCalculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        int[] digitIds = { R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9 };
        for (int id : digitIds) findViewById(id).setOnClickListener(v -> appendDigit(((Button)v).getText().toString()));

        findViewById(R.id.btnDot).setOnClickListener(v -> appendDot());
        findViewById(R.id.btnPlus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.btnMul).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setOperator("/"));
        findViewById(R.id.btnEq).setOnClickListener(v -> calculate());
        findViewById(R.id.btnC).setOnClickListener(v -> clearAll());
        findViewById(R.id.btnCE).setOnClickListener(v -> { currentText = "0"; updateDisplay(); });
        findViewById(R.id.btnBack).setOnClickListener(v -> backspace());
        findViewById(R.id.btnSign).setOnClickListener(v -> toggleSign());
        findViewById(R.id.btnSqrt).setOnClickListener(v -> sqrtOp());

        updateDisplay();
    }

    private void appendDigit(String d) {
        if (justCalculated) { currentText = "0"; justCalculated = false; }
        if (currentText.equals("0")) currentText = d;
        else currentText += d;
        updateDisplay();
    }

    private void appendDot() {
        if (justCalculated) { currentText = "0"; justCalculated = false; }
        if (!currentText.contains(".")) currentText += ".";
        updateDisplay();
    }

    private void setOperator(String op) {
        if (pendingOperator != null && !justCalculated) calculate();
        firstOperand = parse(currentText);
        pendingOperator = op;
        currentText = "0";
        justCalculated = false;
        updateDisplay();
    }

    private void calculate() {
        if (pendingOperator == null || firstOperand == null) { justCalculated = true; return; }
        double a = firstOperand;
        double b = parse(currentText);
        String r;
        switch (pendingOperator) {
            case "+": r = format(a + b); break;
            case "-": r = format(a - b); break;
            case "*": r = format(a * b); break;
            case "/":
                if (b == 0.0) { showError(); return; }
                r = format(a / b); break;
            default: r = currentText;
        }
        currentText = r;
        firstOperand = null;
        pendingOperator = null;
        justCalculated = true;
        updateDisplay();
    }

    private void clearAll() {
        currentText = "0";
        firstOperand = null;
        pendingOperator = null;
        justCalculated = false;
        updateDisplay();
    }

    private void backspace() {
        if ("Error".equals(currentText)) {
            currentText = "0";
            justCalculated = false;
            updateDisplay();
            return;
        }
        if (justCalculated) {
            justCalculated = false;
        }
        if (currentText.length() <= 1 || (currentText.length() == 2 && currentText.startsWith("-"))) {
            currentText = "0";
        } else {
            currentText = currentText.substring(0, currentText.length() - 1);
        }
        updateDisplay();
    }


    private void toggleSign() {
        if (currentText.equals("0")) return;
        if (currentText.startsWith("-")) currentText = currentText.substring(1);
        else currentText = "-" + currentText;
        updateDisplay();
    }

    private void sqrtOp() {
        double v = parse(currentText);
        if (v < 0) { showError(); return; }
        currentText = format(Math.sqrt(v));
        justCalculated = true;
        updateDisplay();
    }

    private double parse(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0.0; }
    }

    private String format(double v) {
        String s = String.valueOf(v);
        if (s.endsWith(".0")) s = s.substring(0, s.length()-2);
        return s;
    }

    private void showError() {
        currentText = "Error";
        firstOperand = null;
        pendingOperator = null;
        justCalculated = true;
        tvDisplay.setText(currentText);
    }

    private void updateDisplay() {
        tvDisplay.setText(currentText);
    }
}
