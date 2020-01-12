package com.ss2.escape

import android.app.Application
import android.os.Bundle
import android.os.Debug
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ss2.escape.model.StoryData
import com.ss2.escape.realmdb.RealmDB
import com.ss2.escape.util.SLog
import io.realm.Realm
import io.realm.kotlin.where
import java.io.File
import android.R
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.ByteArrayOutputStream
import java.io.IOException


class EscapeApplication : Application() {
    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
        DBInit()
       // initData()
        loadData()
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
            SLog.d(data.p_No);
        }
    }

    fun loadData() {

        var data: String? = null
        val inputStream = resources.openRawResource(R.raw.textfile)
        val byteArrayOutputStream = ByteArrayOutputStream()

        var i: Int
        try {
            i = inputStream.read()
            while (i != -1) {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }

            data = String(byteArrayOutputStream.toByteArray(), "MS949")
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun initData() {
        var story: List<String>
        // 튜토리얼4
        var storys = ArrayList<String>()
        storys.add("P001|벤이 자신의 보안 시스템을 조작하는데 사용하는 레버입니다. 벤은 상당히 현대적인 사람이지만 가끔 이런 구식 장치를 좋아하는 것 같습니다|지금처럼 게임 카드를 획득할 수 없는 문단도 있기 때문에 탐색할 구역은 신중하게 선택하셔야 합니다. 하지만 이런 경우에도 내용에 도움이 될만한 정보가 있을지도 모릅니다.")
        storys.add("P002|벤의 컵에서 보안 USB를 발견했습니다.|게임 카드 C148을 가져오세요. 왼쪽 아래에 문자가 쓰여있는 아이템 카드입니다. 게임을 진행하며 필요할 때 사용하십시오.")
        storys.add("P003|벤의 감상적인 면모를 보여주는 이 일기장에는 수많은 날짜들이 적혀있습니다. 각종 기념일부터 업무 관련 약속들까지... 지금은 큰 도움이 되지 않을 것 같습니다.|지금처럼 게임 카드를 획득할 수 없는 문단도 있기 때문에 탐색할 구역은 신중하게 선택하셔야 합니다. 하지만 이런 경우에도 내용에 도움이 될만한 정보가 있을지도 모릅니다.")
        storys.add("P004|벤의 컴퓨터입니다. 당신도 벤의 컴퓨터에 계정을 만들어 두고 가끔 사용하곤 합니다. 하지만 지금은 벤의 계정으로 접속하는 것이 좋ㅇ르 것 같기도 합니다. 누구의 계정으로 접속해야 할까요? 벤은 틀림없이 자신의 컴퓨터에 도움이 될만한 정보를 기록해 두었을 것입니다.|게임 카드 C063을 가져오세요. 이 카드는 수수께끼 카드입니다. 일치하는 문자를 수수께끼 탭에서 찾아보세요.")
        storys.add("P005|이 로봇은 항상 당신의 웃음거리였지만, 보안 모드가 가동되어 있는 지금은 평소와는 달라 보입니다. 전원을 끄는 것이 좋을 것 같습니다.|게임 카드 C084를 가져오세요. 이 카드는 조건 카드입니다. 조건에 맞는 문자가 표시된 아이템을 획득해야 조건 카드에 제시된 문단을 확인할 수 있습니다.")
        storys.add("P006|보안 USB를 꽂자 로봇의 전원이 꺼졌습니다.|게임 카드 C084, C148을 버리세요. C001에 진행 토큰 한 개를 올려 놓으세요.")
        storys.add("P007|벽면 모니터에 도식적인 그림이 표시되어 있습니다. 3D프린터를 끄는 데 도움이 될 것 같습니다.|게임 카드 C101을 가져오세요.")
        storys.add("P008|안타깝지만 선을 잘못 잘라서 감전되고 말았습니다. 전원이 차단되지 않았었나 보네요! 하지만 운이 좋게도 3D 프린터 역시 회로 결함으로 꺼져버렸습니다.|액션 토큰 1개를 버리세요. 버릴 수 있는 애겻ㄴ토큰이 남아 있지 않다면 스트레스 카드를 확인하고 액션토큰을 버리세요. 게임 카드 C050, C101이 있담녀 버리세요. C001에 진행 토큰 한 개를 올려놓으세요.")
        storys.add("P009|보안 시스템과 관련된 모든 기기를 꺼버렸습니다. 문을 가로막고 있던 레이저가 사라지며 문이 열리는 소리가 들립니다. 휴대전화를 찾아서 벤에게 전화하고 한마디 해야겠습니다. 이런 식으로 보안 시스템이 작동해서는 안됩니다. 딸이 이 방에 들어왔다면 어땠을지 상상도 하기 싫어집니다!|축하합니다! 튜토리얼을 무사히 마쳤습니다! 게임을 시작하려면 장소 카드, 수수께끼 카드, 액션 토큰, 진행 토큰을 모두 버리세요.")
        storys.add("P010|사랑하는 남편이지만 집 안에 레이저는 좀 너무한 것 같습니다.|게임 카드 C001을 가져오세요. 게임카드 C001의 설명에 따라 게임을 진행하며 지시가 있을 때 진행 토큰을 놓을 수 있습니다. 이제 탐색하고 싶은 구역을 생각해 보세요. 탐색하고 싶은 구역을 결정했다면 지도 카드의 해당 구역에 액션 토큰을 놓고 해당 구역에 적혀있는 번호의 문단을 확인하세요. 사용할 수 있는 액션 토큰이 있담녀 이 과정을 반복할 수 있습니다. 사용할 수 있는 액션 토큰이 남아있지 않다면 스트레스 카드를 확인하고 액션 토큰을 추가로 획득할 수 있습니다.")
        storys.add("P011|도난 방지용 덧문이 내려져 있습니다. 이것이 벤이 말 했던 해제해야 할 기기들 중 하나인 것 같습니다.|게임 카드 C058을 가져오세요. 이 카드는 수수께끼 카드입니다. 일치하는 문자를 수수께끼 탭에서 찾아보세요.")
        storys.add("P012|레이저... 영화의 한 장면 같습니다. 위험한 레이저가 아니면 좋겠군요.|지금처럼 게임 카드를 획득할 수 없는 문단도 있기 때문에 탐색할 구역은 신중하게 선택하셔야 합니다. 하지만 이런 경우에도 내용에 도움이 될만한 정보가 있을지도 모릅니다.")
        storys.add("P013|일련번호가 잘 보이는 3D 프린터입니다. 상당히 유용한 기기죠. 벤은 이 3D 프린터만 있으면 대부분의 물건들을 전문가의 도움 없이 고칠 수 있습니다.|지금처럼 게임 카드를 획득할 수 없는 문단도 있기 때문에 탐색할 구역은 신중하게 선택하셔야 합니다. 하지만 이런 경우에도 내용에 도움이 될만한 정보가 있을지도 모릅니다.")
        storys.add("P014|덧문이 열렸습니다! 이제서야 빛이 조금 들어오는군요.|게임 카드 C058을 버리세요. C001에 진행 토큰 한개를 올려놓으세요.")
        storys.add("P015|사무실을 가득 울리는 경고음에 귀가 아플 지경입니다. 보안 시스템을 엇어 해제하고 밖으로 나가야 합니다!|게임판을 준비하세요. 게임판은 격자무늬가 있는 면이 위로 가도록 테이블 위에 놓아주세요. 장소 카드 L01, L19를 가져오세요. 게임카드 C143을 가져오세요. 지도 카드를 참고하며 게임판 위에 장소 카드를 배치하세요. 지도 카드는 이렇게 장소 카드를 적절하게 배치할 수 있도록 도움을 줍니다. 액션 토큰 6개를 가져오고 1개를 지도 카드의 C3에 놓아주세요. 그곳에 해당하는 문단 P010을 확인하세요.")
        storys.add("P016|휴... 어렵지 않았습니다. 이제 선을 잘라내어 전원을 차단하면 됩니다. 그런데 어떤 선을 잘라내야 할까요?|게임 카드 C055을 버리세요. 게임카드 C050을 가져오세요. 이 카드는 결정 카드입니다. 게임을 진행하며 몽느 정보와 여러분의 판단력으로 선택하세요.")
        storys.add("P017|제대로 전원을 차단했습니다! 잘 하셨습니다!|게임 카드 C050. C101이 있다면 버려주세요. C001에 진행 토큰 한 개를 올려놓으세요.")
        storys.add("P018|벤의 계정에 암호를 입력하자, 다음과 같은 메시지가 화면에 표시됩니다. \"여보, 내 비밀번호가 그렇게 간단할 것 같아? 내 계정 말고 당신 계정을 쓰지그래.\"|액션 토큰 1개를 버리세요. 버릴 수 있는 액션 토큰이 남아있지 않다면 스트레스 카드를 확인하고 액션 토큰을 버리세요. 그리고 정답을 다시 입력하세요.")
        storys.add("P019|벤의 금고입니다. 3D 프린터 전원 버튼이 작동하지 않기 때문에 금고를 열어야 3D 프린터를 끌 수 있습니다. 금고 안에 프린터 배선이 설치되어 있기 때문입니다.|게임 카드 C044를 가져오세요. 이 카드는 수수께끼 카드입니다. 일치하는 문자를 수수께기 탭에서 찾아보세요.")
        storys.add("P020|벤과 당신의 사랑스러운 반려동물입니다. 벤이 이 사진을 사무실에 액자로 걸어둔 것을 보니 기분이 좋아집니다.|게임 카드 C037을 가져오세요")
        storys.add("P021|금고를 열자 퓨즈 상자가 보입니다. 비어있는 곳에 알맞은 퓨즈를 넣으면 안쪽에 있는 선을 확인할 수 있습니다.|게임 카드 C044. C048을 버리세요. 게임 카드 C055를 가져오세요.")
        storys.add("P022|당신은 자신의 계정으로 접속했습니다. 그런데 이상한 사진이 보이는군요.|게임 카드 C037, C063을 버리세요. 게임 카드 C048을 가져오세요. 이 카드는 수수께끼 카드입니다. 일치하는 문자를 수수께끼 탭에서 찾아보세요.")

        for(i in 0..storys.size-1 ){
            story = storys[i].split("|")
            var storyData = StoryData(story[0], story[1], story[2])
            insertData(storyData)
        }

        readData()
    }
}