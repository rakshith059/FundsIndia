package rakshith.com.fundsindia.services

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import rakshith.com.fundsindia.services.models.StoryResponse
import rakshith.com.fundsindia.utils.Constants
import rakshith.com.volleylib.utils.NetworkVolleyRequest

/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

open class StoryApiService {
    private var gson: Gson? = null

    companion object {
        val TAG = StoryApiService::class.java.simpleName
        
        var storyApiService: StoryApiService? = null

        @Synchronized
        fun getInstance(): StoryApiService {
            if (storyApiService == null)
                storyApiService = StoryApiService()
            return storyApiService as StoryApiService
        }
    }

    fun getSearchResult(mSearchTerm: String): LiveData<StoryResponse> {
        var searchData: MutableLiveData<StoryResponse> = MutableLiveData()

        var gsonBuilder: GsonBuilder = GsonBuilder()
        gsonBuilder.setDateFormat("M/d/yy hh:mm a")
        gson = gsonBuilder?.create()

        var headerMap = HashMap<String, String>()
        headerMap.put(Constants.HEADER_X_MASHAPE_KEY, Constants.HEADER_X_MASHAPE_VALUE)
        var paramsMap = HashMap<String, String>()
        paramsMap.put(Constants.AUTOCORRECT_PARAM_KEY, Constants.PARAM_TRUE)
        paramsMap.put(Constants.COUNT_PARAM_KEY, "50")
        paramsMap.put(Constants.SEARCH_PARAM_KEY, mSearchTerm)

        NetworkVolleyRequest(NetworkVolleyRequest.RequestMethod.GET,
                Constants.storySearchUrl,
                String::class.java,
                headerMap,
                paramsMap,
                object : NetworkVolleyRequest.Callback<Any> {
                    override fun onError(errorCode: Int, errorMessage: String) {
                        Log.d(TAG, "error code is $errorCode and error message is $errorMessage")
                    }

                    override fun onSuccess(response: Any) {
                        var storySearchResponse: StoryResponse? = gson?.fromJson(response.toString(), StoryResponse::class.java)

                        searchData.value = storySearchResponse
                    }
                }, NetworkVolleyRequest.ContentType.JSON)
                .execute()

        return searchData
    }
}