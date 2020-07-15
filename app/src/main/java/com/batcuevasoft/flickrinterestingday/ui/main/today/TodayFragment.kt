package com.batcuevasoft.flickrinterestingday.ui.main.today

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraph
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.batcuevasoft.flickrinterestingday.R
import com.batcuevasoft.flickrinterestingday.data.model.FlickrPicture
import com.batcuevasoft.flickrinterestingday.ui.BaseFragment
import com.batcuevasoft.flickrinterestingday.ui.adapter.FlickrLoadStateAdapter
import com.batcuevasoft.flickrinterestingday.ui.extensions.gone
import com.batcuevasoft.flickrinterestingday.ui.extensions.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_picture.view.*
import kotlinx.android.synthetic.main.today_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TodayFragment : BaseFragment<TodayViewModel>(R.layout.today_fragment, TodayViewModel::class) {

    private val adapter: PagedPictureAdapter by lazy {
        PagedPictureAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(picturesRecycler) {
            adapter = this@TodayFragment.adapter.withLoadStateHeaderAndFooter(
                header = FlickrLoadStateAdapter(viewModel::refreshPictures),
                footer = FlickrLoadStateAdapter(viewModel::refreshPictures)
            )
            val layoutMangr = LinearLayoutManager(requireContext())
            layoutManager = layoutMangr

            postponeEnterTransition()
            afterMeasure { startPostponedEnterTransition() }
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.refreshPictures()
        }
    }

    @ExperimentalCoroutinesApi
    override fun attachObservers() {
        viewModel.todayPictures.observe(viewLifecycleOwner, Observer { pictureList ->
            hideLoading()
            swipeRefresh.isRefreshing = false
            // I still dont know why Google is now adding threading to View level... after so many years
            // recommending not to do it.
            lifecycleScope.launch(Dispatchers.IO) {
                adapter.submitData(pictureList)
            }
        })

        viewModel.todayEvents.observe(viewLifecycleOwner, Observer {
            when(it.consume()) {
                TodayViewEvent.SHOW_LOADING -> showLoading()
                TodayViewEvent.HIDE_LOADING -> hideLoading()
                TodayViewEvent.DEFAULT_ERROR -> showError(getString(R.string.default_error))
                TodayViewEvent.GET_PICTURES_ERROR -> showError(getString(R.string.get_pictures_error))
                null -> Unit
            }
        })
    }

    private fun showError(text: String, retry: (() -> Unit)? = null) {
        Snackbar.make(todayFragmentRootLayout, text, Snackbar.LENGTH_INDEFINITE).apply {
            retry?.let { retryAction ->
                setAction(getString(R.string.retry)) { retryAction() }
            }
            setActionTextColor(ContextCompat.getColor(requireContext(), R.color.colorAccent))
            show()
        }
    }

    private fun hideLoading() {
        swipeRefresh.isRefreshing = false
        loadingLayout.gone()
    }

    private fun showLoading() {
        loadingLayout.visible()
    }

}

inline fun <T : View> T.afterMeasure(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}