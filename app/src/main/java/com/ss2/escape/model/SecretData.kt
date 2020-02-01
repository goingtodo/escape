package com.ss2.escape.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

//번호,정답,힌트,필요카드갯수
open class SecretData(s_no: String = "", answer: String = "", need_card_cnt: String = "0", hints: MutableList<String> = arrayListOf("")
) : RealmObject() {
    @PrimaryKey
    open var s_no : String? = s_no
    open var s_answer : String = answer
    open var s_need_card_cnt : String = need_card_cnt
    open var s_hints : MutableList<String> = hints
}