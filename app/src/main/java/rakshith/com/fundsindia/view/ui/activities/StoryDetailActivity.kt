package rakshith.com.fundsindia.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_story_detail.*
import rakshith.com.fundsindia.R
import rakshith.com.fundsindia.services.models.StoryValueModel
import rakshith.com.fundsindia.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class StoryDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_detail)

        var storyItem = intent.extras.getParcelable<StoryValueModel>(Constants.STORY_ITEM)

        Picasso.with(this)
                .load(storyItem?.image?.url)
                .into(activity_story_detail_iv_icon)

        activity_story_detail_tv_title?.text = Html.fromHtml(storyItem?.title)
        activity_story_detail_tv_date_published?.text = storyItem?.datePublished
        activity_story_detail_tv_description?.text = Html.fromHtml(storyItem?.description)

        activity_story_detail_tv_provider?.text = Html.fromHtml(storyItem?.provider?.name)
        activity_story_detail_tv_link?.text = storyItem?.url

        activity_story_detail_tv_link?.setOnClickListener(View.OnClickListener {
            val webActivityIntent = Intent(applicationContext, WebViewActivity::class.java)
            webActivityIntent.putExtra(WebViewActivity.EXTRA_URL, storyItem?.url)
            startActivity(webActivityIntent)
        })
    }
}
