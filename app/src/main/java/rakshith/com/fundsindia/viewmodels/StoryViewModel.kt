package rakshith.com.fundsindia.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import rakshith.com.fundsindia.services.StoryApiService
import rakshith.com.fundsindia.services.models.StoryResponse
import rakshith.com.fundsindia.utils.Constants

/**
 * Created FundsIndia by rakshith on 6/23/18.
 */


class StoryViewModel(application: Application) : AndroidViewModel(application) {
    private var storyListObservable: LiveData<StoryResponse>? = null

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getStoryListObservable(): LiveData<StoryResponse>? {
        return storyListObservable
    }

    fun searchStory(mSearchTerm: String?) {
        storyListObservable = StoryApiService?.getInstance().getSearchResult(mSearchTerm as String)
    }
}