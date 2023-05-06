package ir.danialchoopan.utils

import ir.danialchoopan.danialtube.data.api.RequestEndPoints

fun LoadImageFormURLFixutils(img:String):String{
    return RequestEndPoints.storageLoad +img.replace(
        "\\",
        "/"
    )
}
