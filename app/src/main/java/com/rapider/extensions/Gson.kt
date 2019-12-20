package com.rapider.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> genericType(): Type = object: TypeToken<T>() {}.type
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json,T::class.java)
inline fun <reified T> Gson.fromJsonToList(json: String) = this.fromJson<Array<T>>(json, Array<T>::class.java).toList()