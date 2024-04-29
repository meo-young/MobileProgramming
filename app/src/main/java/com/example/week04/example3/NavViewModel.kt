package com.example.week04.example3

import androidx.lifecycle.ViewModel

class NavViewModel2 : ViewModel(){
    val userid = "greenjoa"
    val userpasswd = "1234"

    var userID:String? = null // 공유하기위해서 저장구조 선언
    var userPasswd:String? = null

    fun checkInfo(id:String, passwd:String):Boolean{
        return userid == id && userpasswd == passwd
    }

    fun setUserInfo(id:String, passwd:String){
        userID = id
        userPasswd = passwd
    }
}
