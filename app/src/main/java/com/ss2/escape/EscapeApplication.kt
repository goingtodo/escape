package com.ss2.escape

import android.app.Application
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import io.realm.Realm
import io.realm.kotlin.where


class EscapeApplication : Application() {
    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        DBInit()
        SLog.d("Start")
    }

    companion object {
        lateinit var INSTANCE: EscapeApplication
    }

    //RealmDB
    fun DBInit(){
        Realm.init(this)

        initData()

        RealmDB.dataInit()
    }

    fun deleteData(){
        val defaultRealm = Realm.getDefaultInstance()
        defaultRealm.executeTransaction {realmTransaction -> realmTransaction.deleteAll()}
    }
    //데이터 추가
    fun insertData(item: StoryData) {
        val defaultRealm = Realm.getDefaultInstance()
        defaultRealm.executeTransaction {realmTransaction ->
            realmTransaction.createObject(StoryData::class.java, item.p_No).apply {
                mainStory = item.mainStory
                directive = item.directive
            }
        }
    }
    //데이터 확인
    fun readData(){
        val defaultRealm = Realm.getDefaultInstance()
        val realmResult = defaultRealm.where<StoryData>().sort("p_No").findAllAsync().toMutableList()
        var data: StoryData?

        for (i in 0.. realmResult.size-1) {
            data = realmResult[i];
            SLog.d("pno :" + data.p_No + ", " +data.mainStory);
        }
    }

    fun initData() {

        // 스토리 파일 읽기
        val assetManager = resources.assets
        val inputStream = assetManager.open("story.txt")
        val read = inputStream.bufferedReader().use { it.readText() }

        var story: List<String>
        var storys = read.split("\n") as ArrayList<String>

        deleteData()
        for(i in 0..storys.size-1 ){
            story = storys[i].split("|")

            val storyData = StoryData(story[0], story[1], story[2])
            insertData(storyData)
        }

        readData()
    }
}