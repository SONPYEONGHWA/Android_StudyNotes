package com.example.climateseoul.climateinfo.model

import com.google.gson.annotations.SerializedName

data class IotVdata(
    val IotVdata017: ResponseClimate
) {
    data class ResponseClimate(
        @SerializedName("list_total_count")
        val listTotalCount: Int,
        @SerializedName("RESULT")
        val result: Result,
        val row: List<Row>
    )

    data class Result(
        @SerializedName("CODE")
        val Code: String,
        @SerializedName("MESSAGE")
        val Message: String
    )

    data class Row(
        @SerializedName("ORGAN_NM")
        val organizationName: String?,
        @SerializedName("TRANSMIT_SERVER")
        val transmitServerNumber: Double?,
        @SerializedName("DATA_NO")
        val dataNumber: Double?,
        val COLUMN0: String?,
        val COLUMN1: String?,
        val COLUMN2: String?,
        @SerializedName("COLUMN3")
        val hyperFineDust: String?,
        @SerializedName("COLUMN4")
        val fineDust: String?,
        @SerializedName("COULMN5")
        val temperature: String?,
        val COLUMN6: String?,
        @SerializedName("COULMN7")
        val windDirection: String?,
        @SerializedName("COULMN8")
        val windSpeed: String?,
        val COLUMN9: String?,
        val COLUMN10: String?,
        val COLUMN11: String?,
        @SerializedName("COLUMN12")
        val uvLevel: String?,
        val COLUMN13: String?,
        val COLUMN14: String?,
        val COLUMN15: String?,
        val COLUMN17: String?,
        val COLUMN18: String?,
        val COLUMN19: String?,
        val COLUMN20: String?,
        val COLUMN21: String?,
        val COLUMN22: String?,
        val COLUMN23: String?,
        @SerializedName("REGIST_DT")
        var registerDateTime: String?
    )
}

