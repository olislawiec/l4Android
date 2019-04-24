package com.chikeandroid.picassotutsplus

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SunsetPhoto(val url: String, var ocena: Int, val opis: String, val id: Int ) : Parcelable {


    companion object {
        fun getSunsetPhotos(): ArrayList<SunsetPhoto> {
            var listaZdjec = ArrayList<SunsetPhoto>()
            listaZdjec.add(SunsetPhoto("https://goo.gl/32YN2B", 0,"Opis1",1))
            listaZdjec.add(SunsetPhoto("https://goo.gl/Wqz4Ev", 0,"Opis2",2))
            return listaZdjec

//            return arrayOf(SunsetPhoto("https://goo.gl/32YN2B", 0,"Opis1",1),
//                    SunsetPhoto("https://goo.gl/Wqz4Ev",0,"Opis2",2),
//                    SunsetPhoto("https://goo.gl/U7XXdF",0,"Opis3",3),
//                    SunsetPhoto("https://goo.gl/ghVPFq",0,"Opis4",4),
//                    SunsetPhoto("https://goo.gl/qEaCWe",0,"Opis5",5),
//                    SunsetPhoto("https://goo.gl/vutGmM",0,"Opis6",6))
        }
    }
}
