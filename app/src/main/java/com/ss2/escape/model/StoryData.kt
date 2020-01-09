package com.ss2.escape.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class StoryData(
        p_no: String = "", story: String = "", context: String = ""
) : RealmObject(){
    @PrimaryKey
    open var p_No : String? = p_no
    open var mainStory : String = story
    open var directive : String = context
}