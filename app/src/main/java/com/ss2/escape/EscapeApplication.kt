package com.ss2.escape

import android.app.Application
import com.ss2.escape.model.SecretData
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
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
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        initData()

        RealmDB.dataInit()
    }

    fun deleteData(){
        val defaultRealm = Realm.getDefaultInstance()
        defaultRealm.executeTransaction {realmTransaction -> realmTransaction.deleteAll()}
    }

    //데이터 추가
    fun insertData(items: List<StoryData>, secrets: List<SecretData>) {
        val defaultRealm = Realm.getDefaultInstance()
        defaultRealm.executeTransaction {realmTransaction ->
            for(item in items) {
                realmTransaction.createObject(StoryData::class.java, item.p_No).apply {
                    mainStory = item.mainStory
                    directive = item.directive
                }
            }
            for(secret in secrets) {
                realmTransaction.createObject(SecretData::class.java, secret.s_no).apply {
                    s_answer = secret.s_answer
                    s_need_card_cnt = secret.s_need_card_cnt
                    s_hints = secret.s_hints
                }
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

        val realmResult2 = defaultRealm.where<SecretData>().sort("s_no").findAllAsync().toMutableList()
        var data2: SecretData?

        for(r in realmResult2) {
            SLog.d("s_no: " + r.s_no + " s_hint" + r.s_hints);
        }
    }

    fun initData() {
        // 스토리 파일 읽기
        val assetManager = resources.assets
        val inputStream = assetManager.open("story.txt")
        val read = inputStream.bufferedReader().use { it.readText() }

        var story: List<String>
        var storys = read.split("\n") as ArrayList<String>

        var storyList : MutableList<StoryData> = arrayListOf()
        for(i in 0..storys.size-1 ){
            story = storys[i].split("|")

            val storyData = StoryData(story[0], story[1], story[2])

            storyList.add(storyData)
        }

        var input = assetManager.open("secret.txt");
        val read2 = input.bufferedReader().use { it.readText()}
        var lines = read2.split("\n")

        var secretList: MutableList<SecretData> = arrayListOf()
        for(line in lines) {
            val list = line.split("|") as ArrayList<String>
            val array = list.subList(2, list.size -1).toList() as ArrayList<String>
            val hints = RealmList<String>()
            hints.addAll(array.toArray(arrayOfNulls<String>(array.size)))
            val data = SecretData(list.get(0), list.get(1), list.get(2), hints)
            secretList.add(data)
        }

        deleteData()
        insertData(storyList, secretList)
        readData()
    }
}