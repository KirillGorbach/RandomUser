package com.example.randomuser.presentation.contacts

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentContactsBinding
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.presentation.main.adapter.UsersAdapter
import com.example.randomuser.presentation.main.appComponent
import com.example.randomuser.presentation.util.extentions.showToastWithErrorMessage
import com.example.randomuser.presentation.util.factory.ViewModelFactory
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject


@ExperimentalSerializationApi
class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private val viewBinding by viewBinding(FragmentContactsBinding::bind)
    private val viewModel: ContactsViewModel by viewModels { viewModelFactory }

    private val savedUsersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        UsersAdapter({}, ::removeSavedUser)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {

            savedList.run {
                adapter = savedUsersAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }


            viewModel.userData.observe(viewLifecycleOwner) { users ->
                savedUsersAdapter.setData(users)
            }

            viewModel.errorTextIdLiveData.observe(viewLifecycleOwner) {
                requireContext().showToastWithErrorMessage(it)
            }
        }
    }

    private  fun removeSavedUser(userEntity: UserEntity) {
        viewModel.removeSavedUser(userEntity)
    }

}