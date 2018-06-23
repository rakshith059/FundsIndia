package rakshith.com.fundsindia.view.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import rakshith.com.fundsindia.R

/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class SearchListViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tvTitle = itemView?.findViewById<TextView>(R.id.story_item_tv_title)
    var tvDescription = itemView?.findViewById<TextView>(R.id.story_item_tv_description)
}