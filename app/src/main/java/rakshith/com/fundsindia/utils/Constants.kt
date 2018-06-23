package rakshith.com.fundsindia.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager



/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class Constants {
    companion object {
        val storySearchUrl = "https://contextualwebsearch-websearch-v1.p.mashape.com/api/Search/NewsSearchAPI"
        val AUTOCORRECT_PARAM_KEY = "autocorrect"
        val COUNT_PARAM_KEY = "count"
        val SEARCH_PARAM_KEY: String = "q"

        val HEADER_X_MASHAPE_KEY = "X-Mashape-Key"
        val HEADER_X_MASHAPE_VALUE = "fvHlKdY1h3mshGmqwXBB5Wo4DJuyp1M6tUujsnHuIrSLm2BO40"
        val PARAM_TRUE: String = "true"
        val DELAY_SECONDS: Long = 1000
        val STORY_ITEM: String = "STORY_ITEM"

        fun isNetworkAvailable(context: Context): Boolean {
            try {
                val cm = context.getSystemService(Context
                        .CONNECTIVITY_SERVICE) as ConnectivityManager
                return cm.activeNetworkInfo.isConnectedOrConnecting
            } catch (e: Exception) {
                return false
            }

        }
    }
}