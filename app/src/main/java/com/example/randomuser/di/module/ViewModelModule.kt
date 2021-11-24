package com.example.randomuser.di.module

import androidx.lifecycle.ViewModel
import com.example.randomuser.di.ViewModelKey
import com.example.randomuser.presentation.contacts.ContactsViewModel
import com.example.randomuser.presentation.explore.ExploreViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @Binds
    @[IntoMap ViewModelKey(ExploreViewModel::class)]
    fun bindExploreViewModel(exploreViewModel: ExploreViewModel): ViewModel

    @Binds
    @[IntoMap ViewModelKey(ContactsViewModel::class)]
    fun bindContactsViewModel(contactsViewModel: ContactsViewModel): ViewModel
}