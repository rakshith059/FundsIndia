package rakshith.com.fundsindia.view.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebChromeClient
import kotlinx.android.synthetic.main.activity_web_view.*
import rakshith.com.fundsindia.R

class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        if (savedInstanceState != null) {
            activity_web_view?.restoreState(savedInstanceState)
        } else {
            activity_web_view?.loadUrl(intent.getStringExtra(EXTRA_URL))
        }

        assert(supportActionBar != null)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        activity_web_view?.getSettings()?.setJavaScriptEnabled(true)
        activity_web_view?.getSettings()?.setSupportZoom(false)
        activity_web_view?.webChromeClient = object : WebChromeClient() {
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    public override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        activity_web_view?.saveState(null)
    }

    public override fun onStop() {
        super.onStop()
        activity_web_view?.stopLoading()
    }

    companion object {
        var EXTRA_URL: String? = null
    }
}

