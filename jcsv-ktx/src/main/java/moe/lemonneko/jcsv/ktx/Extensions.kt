package moe.lemonneko.jcsv.ktx

import moe.lemonneko.jcsv.*
import java.io.File

fun Any.toCSVObject(): CSVObject {
    val clazz = this::class.java
    val fields = clazz.declaredFields
    val csvObject = CSVObject()
    fields.forEach {
        val annotation = it.getAnnotation(CSVField::class.java)
        if (annotation != null) {
            csvObject[annotation.name] = it[this] as String
        } else {
            csvObject[it.name] = it[this] as String
        }
    }
    return csvObject
}

fun <T> List<T>.toCSVObjectList(): CSVObjectList{
    val csvObjectList = CSVObjectList()
    forEach {
        if (it != null) {
            csvObjectList.add(it.toCSVObject())
        }
    }
    return csvObjectList
}

inline fun <reified T> File.readAll(): List<T>{
    val reader = CSVReader(this)
    val list = reader.readAll(T::class.java)
    reader.close()
    return list
}

inline fun <reified T> List<T>.writeAll(file: File){
    val writer = CSVWriter(file)
    writer.writeAll(this,T::class.java)
    writer.close()
}