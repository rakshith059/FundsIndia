package rakshith.com.fundsindia.services.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import rakshith.com.fundsindia.services.models.StoryValueModel

/**
 * Created FundsIndia by rakshith on 6/23/18.
 */

class StoryResponse() : Parcelable {
    @SerializedName("_type")
    @Expose
    var type: String? = null
    @SerializedName("didUMean")
    @Expose
    var didUMean: String? = null
    @SerializedName("totalCount")
    @Expose
    var totalCount: Int? = null
    @SerializedName("relatedSearch")
    @Expose
    var relatedSearch: List<String>? = null
    @SerializedName("value")
    @Expose
    var value: List<StoryValueModel>? = null

    constructor(parcel: Parcel) : this() {
        type = parcel.readString()
        didUMean = parcel.readString()
        totalCount = parcel.readValue(Int::class.java.classLoader) as? Int
        relatedSearch = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(didUMean)
        parcel.writeValue(totalCount)
        parcel.writeStringList(relatedSearch)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryResponse> {
        override fun createFromParcel(parcel: Parcel): StoryResponse {
            return StoryResponse(parcel)
        }

        override fun newArray(size: Int): Array<StoryResponse?> {
            return arrayOfNulls(size)
        }
    }

}