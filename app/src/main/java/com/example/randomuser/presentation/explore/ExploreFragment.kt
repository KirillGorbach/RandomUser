package com.example.randomuser.presentation.explore

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentExploreBinding
import com.example.randomuser.presentation.main.adapter.UsersAdapter
import com.example.randomuser.presentation.main.appComponent
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.presentation.util.extentions.showToastWithErrorMessage
import com.example.randomuser.presentation.util.factory.ViewModelFactory
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class ExploreFragment : Fragment(R.layout.fragment_explore) {


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewBinding by viewBinding(FragmentExploreBinding::bind)
    private val viewModel: ExploreViewModel by viewModels { viewModelFactory }

    private val usersAdapter by lazy(LazyThreadSafetyMode.NONE) {
        UsersAdapter(::saveUser, ::removeSavedUser)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {

            exploreList.run {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }


            viewModel.userData.observe(viewLifecycleOwner) { users ->
                usersAdapter.setData(users)
            }

            viewModel.errorTextIdLiveData.observe(viewLifecycleOwner) {
                requireContext().showToastWithErrorMessage(it)
            }

        }
    }


    private fun saveUser(userEntity: UserEntity) {
        viewModel.insertSavedUser(userEntity)
    }

    private  fun removeSavedUser(userEntity: UserEntity) {
        viewModel.removeSavedUser(userEntity)
    }


    private var listSavedState: Parcelable? = null
    override fun onPause() {
        super.onPause()
        listSavedState = viewBinding.exploreList.layoutManager?.onSaveInstanceState()!!
    }

    override fun onResume() {
        super.onResume()
        viewBinding.exploreList.layoutManager?.onRestoreInstanceState(listSavedState)
    }

}