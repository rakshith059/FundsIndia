package rakshith.com.fundsindia.services.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class StoryProvider() : Parcelable {

    @SerializedName("name")
    @Expose
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryProvider> {
        override fun createFromParcel(parcel: Parcel): StoryProvider {
            return StoryProvider(parcel)
        }

        override fun newArray(size: Int): Array<StoryProvider?> {
            return arrayOfNulls(size)
        }
    }

}