package com.example.randomuser.di

import com.example.randomuser.presentation.contacts.ContactsFragment
import com.example.randomuser.presentation.explore.ExploreFragment
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
interface InjectFragments {

    fun inject(exploreFragment: ExploreFragment)

    fun inject(contactsFragment: ContactsFragment)
}
