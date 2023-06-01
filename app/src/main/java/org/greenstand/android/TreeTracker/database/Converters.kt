/*
 * Copyright 2023 Treetracker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.greenstand.android.TreeTracker.database

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {

    @TypeConverter
    fun jsonToMap(value: String?) = value?.let { Json.decodeFromString<Map<String, String>>(it) }

    @TypeConverter
    fun mapToJson(value: Map<String, String>?): String = Json.encodeToString(value)

    @TypeConverter
    fun instantToString(value: String?) = value?.let { Json.decodeFromString<Instant>(it) }

    @TypeConverter
    fun stringToInstance(value: Instant?) = value?.let { Json.encodeToString(it) }

    @TypeConverter
    fun stringToArray(value: String?) = value?.let { Json.decodeFromString<List<String>>(it) }

    @TypeConverter
    fun arrayToString(value: List<String>?): String = Json.encodeToString(value)
}