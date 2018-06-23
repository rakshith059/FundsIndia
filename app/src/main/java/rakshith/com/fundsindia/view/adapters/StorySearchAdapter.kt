package rakshith.com.fundsindia.view.adapters

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import rakshith.com.fundsindia.R
import rakshith.com.fundsindia.services.models.StoryResponse
import rakshith.com.fundsindia.services.models.StoryValueModel
import rakshith.com.fundsindia.utils.Constants
import rakshith.com.fundsindia.view.holders.SearchListViewHolder
import rakshith.com.fundsindia.view.ui.activities.StoryDetailActivity

/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class StorySearchAdapter(storyResponse: StoryResponse?) : RecyclerView.Adapter<SearchListViewHolder>() {
    var storyListItems = storyResponse?.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        var view: View? = LayoutInflater.from(parent.context)?.inflate(R.layout.story_item_row, parent, false)
        return SearchListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storyListItems?.size as Int
    }

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {
        var storyItem = storyListItems?.get(position)
        var mContext = holder.itemView.context

        holder?.tvTitle?.text = Html.fromHtml(storyItem?.title)
        holder?.tvDescription?.text = Html.fromHtml(storyItem?.description)

        holder?.itemView.setOnClickListener(View.OnClickListener {
            var detailIntent = Intent(mContext, StoryDetailActivity::class.java)
            detailIntent.putExtra(Constants.STORY_ITEM, storyItem)
            mContext.startActivity(detailIntent)
        })
    }
}