package com.example.currency


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches


@RunWith(AndroidJUnit4::class)
class TestLogin{

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(Login::class.java)

    private val username = "test"
    private val password = "password"

    @Test
    fun clickLoginButton_opensLoginUi() {
        onView(withId(R.id.editTextTextPersonName2)).perform(ViewActions.typeText(username))
        onView(withId(R.id.editTextTextPassword)).perform(ViewActions.typeText(password))

        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), ViewActions.click())

        Espresso.onView(withId(R.id.button)).check(matches(withText("Success")))
    }
}