package com.ss2.escape

import android.app.Application
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import io.realm.Realm
import io.realm.kotlin.where
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.nio.charset.Charset


class EscapeApplication : Application() {
    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        DBInit()
        initData()
        readData()
        SLog.d("Start")
    }

    companion object {
        lateinit var INSTANCE: EscapeApplication
    }

    //RealmDB
    fun DBInit(){
        Realm.init(this)
        RealmDB.dataInit()
    }

    //데이터 추가
    fun insertData(item: StoryData) {
        val defaultRealm = Realm.getDefaultInstance()
        defaultRealm.executeTransaction {realmTransaction -> realmTransaction.deleteAll()}
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
            SLog.d("pno :" + data.p_No);
        }
    }

    fun initData() {

        // 스토리 파일 읽기
        val assetManager = resources.assets
        val inputStream = assetManager.open("story.txt")
        val read = inputStream.bufferedReader().use { it.readText() }

        var story: List<String>
        var storys = read.split("\n") as ArrayList<String>

        for(i in 0..storys.size-1 ){
            story = storys[i].split("|")

            val storyData = StoryData(story[0], story[1], story[2])
            insertData(storyData)
        }

        readData()
    }
}