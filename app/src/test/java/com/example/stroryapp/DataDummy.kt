package com.example.stroryapp

import com.example.stroryapp.Respon.ListStoryItem

object DataDummy {

    fun generateDummyQuoteResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val quote = ListStoryItem(
                i.toString(),
                "2023-05-07T22:10:15.213Z",
                "iqra",
                "Halo",
                37.422092,
                "story-$i",
                -122.08392
            )
            items.add(quote)
        }
        return items
    }
}