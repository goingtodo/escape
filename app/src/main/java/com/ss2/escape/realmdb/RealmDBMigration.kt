package com.ss2.escape.realmdb

import android.os.Parcel
import android.os.Parcelable
import io.realm.DynamicRealm
import io.realm.RealmMigration

open class RealmDBMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

    }

}