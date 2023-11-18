package com.mict.hopeharbour.model.updates

data class UpdateEntry(
    var authors: List<Author>,
    var link: String,
    var published: String,
    var title: String
)
