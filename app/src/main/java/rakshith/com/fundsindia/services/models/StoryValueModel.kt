package rakshith.com.fundsindia.services.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class StoryValueModel() : Parcelable {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("datePublished")
    @Expose
    var datePublished: String? = null
    @SerializedName("provider")
    @Expose
    var provider: StoryProvider? = null
    @SerializedName("image")
    @Expose
    var image: StoryImage? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        url = parcel.readString()
        description = parcel.readString()
        datePublished = parcel.readString()
        provider = parcel.readParcelable(StoryProvider::class.java.classLoader)
        image = parcel.readParcelable(StoryImage::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(description)
        parcel.writeString(datePublished)
        parcel.writeParcelable(provider, flags)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryValueModel> {
        override fun createFromParcel(parcel: Parcel): StoryValueModel {
            return StoryValueModel(parcel)
        }

        override fun newArray(size: Int): Array<StoryValueModel?> {
            return arrayOfNulls(size)
        }
    }

}