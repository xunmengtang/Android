package com.reaksmeyarun.pda.utils

import com.google.firebase.database.DataSnapshot

object DataSnapShotConvert {
    fun <Model> dataSnapShotToArrayList(classModel : Class<Model>, dataSnapShot : DataSnapshot?) : ArrayList<Model>{
        var arrayList = ArrayList<Model>()
        dataSnapShot?.let {
            var dataSnapshotInteger: Iterator<DataSnapshot> = it.children.iterator()
            while (dataSnapshotInteger.hasNext()){
                var data : Model? = dataSnapshotInteger.next().getValue(classModel)
                if (data != null) {
                    arrayList.add(data)
                }
            }
        }
        return arrayList
    }
}