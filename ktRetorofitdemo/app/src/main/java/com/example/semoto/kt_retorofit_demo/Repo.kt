package com.example.semoto.kt_retorofit_demo

import android.os.Parcel
import android.os.Parcelable

data class Repo(val id: Int, val name: String, val url: String, val private: Boolean) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Repo> = object : Parcelable.Creator<Repo> {
            override fun createFromParcel(source: Parcel): Repo = Repo(source)
            override fun newArray(size: Int): Array<Repo?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(url)
        dest.writeInt((if (private) 1 else 0))
    }
}