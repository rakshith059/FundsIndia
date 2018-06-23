package rakshith.com.fundsindia.services.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class StoryImage() : Parcelable {
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null

    constructor(parcel: Parcel) : this() {
        url = parcel.readString()
        height = parcel.readValue(Int::class.java.classLoader) as? Int
        width = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeValue(height)
        parcel.writeValue(width)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryImage> {
        override fun createFromParcel(parcel: Parcel): StoryImage {
            return StoryImage(parcel)
        }

        override fun newArray(size: Int): Array<StoryImage?> {
            return arrayOfNulls(size)
        }
    }

}