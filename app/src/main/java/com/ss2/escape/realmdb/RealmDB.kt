package com.ss2.escape.realmdb

import com.ss2.escape.model.SecretData
import com.ss2.escape.model.StoryData
import com.ss2.escape.util.SLog
import io.realm.Realm
import io.realm.kotlin.where

//DB데이터 저장 클래스
class RealmDB{
    companion object {
        private val realmDB = Realm.getDefaultInstance()
        private var storyDataResult: MutableList<StoryData>? = null
        private var secretDataResult: MutableList<SecretData>? = null

        //앱 시작시 데이터 불러오기
        fun dataInit(){
            storyDataResult = realmDB.where<StoryData>().sort("p_No").findAllAsync().toMutableList()
            secretDataResult = realmDB.where<SecretData>().sort("s_no").findAllAsync().toMutableList()
        }

        //Position에 해당하는 Data Read
        fun readStoryData(arg : String): StoryData? {
            var data = storyDataResult!!.filter { it.p_No!!.startsWith(arg) };
            return data[0]
        }

        fun readLevelStroyData(level : Int) : List<StoryData>?{
            var dataList : List<StoryData>
            dataList = storyDataResult!!.filter { it.p_No!!.startsWith("P-"+level) }
            SLog.d("DataListSize : " + dataList.size.toString())
            return dataList
        }

        fun readStoryCount(): Int {
            return storyDataResult!!.size
        }
    }
}