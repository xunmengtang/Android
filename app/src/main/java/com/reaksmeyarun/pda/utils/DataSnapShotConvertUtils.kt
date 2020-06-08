@file:Suppress("NAME_SHADOWING")

package com.reaksmeyarun.pda.utils

import com.google.firebase.database.DataSnapshot
import com.reaksmeyarun.pda.model.CategoryModel
import com.reaksmeyarun.pda.model.ItemModel

object DataSnapShotConvertUtils {
    fun <Model> dataSnapShotToArrayList(classModel : Class<Model>, dataSnapShot : DataSnapshot?) : ArrayList<Model>{
        val arrayList = ArrayList<Model>()
        dataSnapShot?.let {
            val dataSnapshotInteger: Iterator<DataSnapshot> = it.children.iterator()
            while (dataSnapshotInteger.hasNext()){
                val data : Model? = dataSnapshotInteger.next().getValue(classModel)
                if (data != null) {
                    arrayList.add(data)
                }
            }
        }
        return arrayList
    }

    fun dataSnapShotToCategoryListID(dataSnapShot: DataSnapshot?) : ArrayList<String>{
        val arrayList = ArrayList<String>()
        dataSnapShot?.let {
            val dataSnapshotInteger: Iterator<DataSnapshot> = it.children.iterator()
            while (dataSnapshotInteger.hasNext()){
                val data : CategoryModel? = dataSnapshotInteger.next().getValue(CategoryModel::class.java)
                data?.categoryInformation?.id?.let { it -> arrayList.add(it) }
            }
        }
        return arrayList
    }

    fun dataSnapShotToCategoryListName(dataSnapShot: DataSnapshot?) : ArrayList<String>{
        val arrayList = ArrayList<String>()
        dataSnapShot?.let {
            val dataSnapshotInteger: Iterator<DataSnapshot> = it.children.iterator()
            while (dataSnapshotInteger.hasNext()){
                val data : CategoryModel? = dataSnapshotInteger.next().getValue(CategoryModel::class.java)
                data?.categoryInformation?.categoryName?.let { data -> arrayList.add(data) }
            }
        }
        return arrayList
    }

    fun dataSnapShotToItemID(dataSnapShot: DataSnapshot?) : ArrayList<String>{
        val arrayList = ArrayList<String>()
        dataSnapShot?.let {
            val dataSnapshotInteger: Iterator<DataSnapshot> = it.children.iterator()
            while (dataSnapshotInteger.hasNext()){
                val data : ItemModel? = dataSnapshotInteger.next().getValue(ItemModel::class.java)
                data?.itemInformation?.id?.let { data -> arrayList.add(data) }
            }
        }
        return arrayList
    }
}