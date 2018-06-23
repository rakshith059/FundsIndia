package rakshith.com.fundsindia.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View


/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class RecyclerItemDecorator(internal var padding: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = padding
        }
        outRect.left = padding
        outRect.right = padding
        outRect.bottom = padding
    }
}