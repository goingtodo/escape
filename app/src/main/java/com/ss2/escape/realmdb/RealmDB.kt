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
        fun readStoryData(position : Int): StoryData? {
            var data = storyDataResult?.get(position);
            return data
        }
        fun readLevelStroyData(level : Int) : List<StoryData>?{
            var dataList : List<StoryData>
            /*when(level){
                0 -> {
                    //튜토리얼 DataList
                    dataList = storyDataResult!!.filter { it.p_No!!.startsWith("P-"+level) }
                    SLog.d(dataList.size.toString())
                }
                1 -> {
                    //파일1 DataList
                    dataList = storyDataResult!!.drop(24)
                    dataList = dataList!!.dropLast(62)
                    SLog.d(dataList.size.toString())
                }
                2 -> {
                    //파일2 DataList
                    dataList = storyDataResult!!.drop(96)
                }
                3 -> {
                    //파일3 DataList
                    dataList = storyDataResult!!.drop(159)
                }
            }*/
            dataList = storyDataResult!!.filter { it.p_No!!.startsWith("P-"+level) }
            SLog.d("DataListSize : " + dataList.size.toString())
            return dataList
        }

        fun readStoryCount(): Int {
            return storyDataResult!!.size
        }
    }
}