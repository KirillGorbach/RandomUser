package com.example.randomuser.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.randomuser.R
import com.example.randomuser.databinding.ActivityMainBinding
import com.example.randomuser.presentation.contacts.ContactsFragment
import com.example.randomuser.presentation.explore.ExploreFragment
import kotlinx.serialization.ExperimentalSerializationApi


@ExperimentalSerializationApi
class MainActivity : AppCompatActivity() {


    private var isFirstActivityCreated = true
    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            isFirstActivityCreated = savedInstanceState.getBoolean(FIRST_SCREEN_KEY)
        }

        if (isFirstActivityCreated) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExploreFragment())
                .commit()
            isFirstActivityCreated = false
        }

        with(viewBinding) {
            bottomNavigation.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.explore_users -> replaceFragment(ExploreFragment())
                    R.id.contacts -> replaceFragment(ContactsFragment())
                }
                return@setOnItemSelectedListener true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FIRST_SCREEN_KEY, isFirstActivityCreated)
    }
    private companion object {
        const val FIRST_SCREEN_KEY = "first_screen_key"
    }
}