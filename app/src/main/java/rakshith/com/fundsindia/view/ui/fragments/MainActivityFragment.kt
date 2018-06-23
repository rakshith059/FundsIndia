package rakshith.com.fundsindia.view.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_main.*
import rakshith.com.fundsindia.R
import rakshith.com.fundsindia.utils.Constants
import rakshith.com.fundsindia.utils.RecyclerItemDecorator
import rakshith.com.fundsindia.view.adapters.StorySearchAdapter
import rakshith.com.fundsindia.viewmodels.StoryViewModel
import java.util.concurrent.TimeUnit

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    var searchAdapter: StorySearchAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var storyViewModel = ViewModelProviders.of(this).get(StoryViewModel::class.java)

        Observable.create(object : ObservableOnSubscribe<String> {
            override fun subscribe(emitter: ObservableEmitter<String>) {
                fragment_main_tet_search?.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        emitter.onNext(s.toString())
                    }
                })
            }
        }).debounce(Constants.DELAY_SECONDS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchTerm ->
                    if (Constants.isNetworkAvailable(activity?.applicationContext as Context)) {
                        storyViewModel.searchStory(searchTerm)
                        observeViewModel(storyViewModel)

                        fragment_main_progress_bar?.visibility = View.VISIBLE
                    } else {
                        Snackbar.make(fragment_main_container, resources.getString(R.string.no_internet), Snackbar.LENGTH_LONG).show()
                    }
                })

        fragment_main_rv_story_list?.addItemDecoration(RecyclerItemDecorator(16))
    }

    private fun observeViewModel(storyViewModel: StoryViewModel) {
        storyViewModel?.getStoryListObservable()?.observe(this, Observer { storyResponse ->
            run {
                if (storyResponse?.value?.size as Int > 0) {
                    fragment_main_rv_story_list?.visibility = View.VISIBLE
                    fragment_main_tv_no_data_found?.visibility = View.GONE
                    fragment_main_progress_bar?.visibility = View.GONE
                    searchAdapter = StorySearchAdapter(storyResponse)
                    fragment_main_rv_story_list?.adapter = searchAdapter
                } else {
                    fragment_main_progress_bar?.visibility = View.GONE
                    fragment_main_rv_story_list?.visibility = View.GONE
                    fragment_main_tv_no_data_found?.visibility = View.VISIBLE
                }
            }
        })
    }
}
