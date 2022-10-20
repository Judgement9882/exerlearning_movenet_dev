package org.tensorflow.lite.examples.poseestimation

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

data class LoginJson(
    val code: String,
    val message: String
)

data class ExerJson(
    val code : String,
    val length : String,
    val exer_OK : JsonArray
)