package com.example.a3_lab;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

/**
 * UI / Integration tests for Calculator using Espresso.
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void typingDigits_buildsCorrectNumber() {
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btn3)).perform(click());

        onView(withId(R.id.tvDisplay))
                .check(matches(withText("23")));
    }

    @Test
    public void addition_twoPlusThree_showsFive() {
        // 2 + 3 = 5
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnPlus)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnEq)).perform(click());

        onView(withId(R.id.tvDisplay))
                .check(matches(withText("5")));
    }

    @Test
    public void clearButton_resetsToZero() {
        // 9 C -> 0
        onView(withId(R.id.btn9)).perform(click());
        onView(withId(R.id.btnC)).perform(click());

        onView(withId(R.id.tvDisplay))
                .check(matches(withText("0")));
    }

    @Test
    public void divisionByZero_showsError() {
        // 8 / 0 = -> "Error"
        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnDiv)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnEq)).perform(click());

        onView(withId(R.id.tvDisplay))
                .check(matches(withText("Error")));
    }

    @Test
    public void sqrtOfNegativeNumber_showsError() {
        onView(withId(R.id.btn9)).perform(click());
        onView(withId(R.id.btnSign)).perform(click());
        onView(withId(R.id.btnSqrt)).perform(click());

        onView(withId(R.id.tvDisplay))
                .check(matches(withText("Error")));
    }

    @Test
    public void backspace_removesLastDigit() {
        // Enter 123, press backspace -> "12"
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnBack)).perform(click());

        onView(withId(R.id.tvDisplay))
                .check(matches(withText("12")));
    }
}
