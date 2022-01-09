package com.example.ticketmastermvvm.data

import com.google.gson.annotations.SerializedName

data class EventModel(
    @SerializedName("_embedded" ) var Embedded : Embedded? = Embedded()
)

data class Embedded (
    @SerializedName("events" ) var events : List<EventData> = emptyList()
)

data class EventData (

    @SerializedName("name"            ) var name            : String?                    = null,
    @SerializedName("type"            ) var type            : String?                    = null,
    @SerializedName("id"              ) var id              : String?                    = null,
    @SerializedName("test"            ) var test            : Boolean?                   = null,
    @SerializedName("url"             ) var url             : String?                    = null,
    @SerializedName("locale"          ) var locale          : String?                    = null,
    @SerializedName("images"          ) var images          : ArrayList<Images>          = arrayListOf(),
    @SerializedName("distance"        ) var distance        : Double?                    = null,
    @SerializedName("units"           ) var units           : String?                    = null,
    //@SerializedName("sales"           ) var sales           : Sales?                     = Sales(),
    @SerializedName("dates"           ) var dates           : Dates?                     = Dates(),
    //@SerializedName("classifications" ) var classifications : ArrayList<Classifications> = arrayListOf(),
    //@SerializedName("promoter"        ) var promoter        : Promoter?                  = Promoter(),
    //@SerializedName("promoters"       ) var promoters       : ArrayList<Promoters>       = arrayListOf(),
    @SerializedName("info"            ) var info            : String?                    = null,
    @SerializedName("pleaseNote"      ) var pleaseNote      : String?                    = null,
    @SerializedName("priceRanges"     ) var priceRanges     : ArrayList<PriceRanges>     = arrayListOf(),
    //@SerializedName("seatmap"         ) var seatmap         : Seatmap?                   = Seatmap(),
    //@SerializedName("accessibility"   ) var accessibility   : Accessibility?             = Accessibility(),
    //@SerializedName("ticketLimit"     ) var ticketLimit     : TicketLimit?               = TicketLimit(),
    @SerializedName("ageRestrictions" ) var ageRestrictions : AgeRestrictions?           = AgeRestrictions(),
    //@SerializedName("_links"          ) var Links           : Links?                     = Links(),
    @SerializedName("_embedded"       ) var Embedded        : Embedded?                  = Embedded()

)
data class Dates (

    @SerializedName("start"            ) var start            : Start?   = Start(),
    @SerializedName("timezone"         ) var timezone         : String?  = null,
    @SerializedName("status"           ) var status           : Status?  = Status(),
    @SerializedName("spanMultipleDays" ) var spanMultipleDays : Boolean? = null

)

data class Start (

    @SerializedName("localDate"      ) var localDate      : String?  = null,
    @SerializedName("localTime"      ) var localTime      : String?  = null,
    @SerializedName("dateTime"       ) var dateTime       : String?  = null,
    @SerializedName("dateTBD"        ) var dateTBD        : Boolean? = null,
    @SerializedName("dateTBA"        ) var dateTBA        : Boolean? = null,
    @SerializedName("timeTBA"        ) var timeTBA        : Boolean? = null,
    @SerializedName("noSpecificTime" ) var noSpecificTime : Boolean? = null

)

data class Status (

    @SerializedName("code" ) var code : String? = null

)

data class PriceRanges (
    @SerializedName("type"     ) var type     : String? = null,
    @SerializedName("currency" ) var currency : String? = null,
    @SerializedName("min"      ) var min      : Double?    = null,
    @SerializedName("max"      ) var max      : Double?    = null

)

data class Images (

    @SerializedName("ratio"    ) var ratio    : String?  = null,
    @SerializedName("url"      ) var url      : String?  = null,
    @SerializedName("width"    ) var width    : Int?     = null,
    @SerializedName("height"   ) var height   : Int?     = null,
    @SerializedName("fallback" ) var fallback : Boolean? = null

)

data class AgeRestrictions (

    @SerializedName("legalAgeEnforced" ) var legalAgeEnforced : Boolean? = null

)

