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
    //@SerializedName("sales"           ) var sales           : Sales?                     = Sales(),
    //@SerializedName("dates"           ) var dates           : Dates?                     = Dates(),
    //@SerializedName("classifications" ) var classifications : ArrayList<Classifications> = arrayListOf(),
    //@SerializedName("promoter"        ) var promoter        : Promoter?                  = Promoter(),
    //@SerializedName("promoters"       ) var promoters       : ArrayList<Promoters>       = arrayListOf(),
    @SerializedName("pleaseNote"      ) var pleaseNote      : String?                    = null,
    @SerializedName("priceRanges"     ) var priceRanges     : ArrayList<PriceRanges>     = arrayListOf(),
    //@SerializedName("seatmap"         ) var seatmap         : Seatmap?                   = Seatmap(),
    //@SerializedName("accessibility"   ) var accessibility   : Accessibility?             = Accessibility(),
    //@SerializedName("ticketLimit"     ) var ticketLimit     : TicketLimit?               = TicketLimit(),
    @SerializedName("ageRestrictions" ) var ageRestrictions : AgeRestrictions?           = AgeRestrictions(),
    //@SerializedName("_links"          ) var Links           : Links?                     = Links(),
    @SerializedName("_embedded"       ) var Embedded        : Embedded?                  = Embedded()

)

data class PriceRanges (

    @SerializedName("type"     ) var type     : String? = null,
    @SerializedName("currency" ) var currency : String? = null,
    @SerializedName("min"      ) var min      : Int?    = null,
    @SerializedName("max"      ) var max      : Int?    = null

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

