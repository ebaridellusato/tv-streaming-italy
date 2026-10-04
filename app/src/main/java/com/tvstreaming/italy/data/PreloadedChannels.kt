package com.tvstreaming.italy.data

import com.tvstreaming.italy.database.Channel

object PreloadedChannels {

    fun getChannels(): List<Channel> = listOf(
        Channel(name = "Rai 1", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=Vplayer&cont1=Rai1&output=47", category = "Generale", isPreloaded = true),
        Channel(name = "Rai 2", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=Vplayer&cont1=Rai2&output=47", category = "Generale", isPreloaded = true),
        Channel(name = "Rai 3", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=Vplayer&cont1=Rai3&output=47", category = "Generale", isPreloaded = true),
        Channel(name = "Rai News 24", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=1", category = "News", isPreloaded = true),
        Channel(name = "Rai Sport", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=358728", category = "Sport", isPreloaded = true),
        Channel(name = "Rai Movie", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=87712", category = "Film", isPreloaded = true),
        Channel(name = "Rai Premium", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=72342", category = "Intrattenimento", isPreloaded = true),
        Channel(name = "Rai Storia", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=24269", category = "Documentari", isPreloaded = true),
        Channel(name = "Rai Scuola", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=74718", category = "Educativo", isPreloaded = true),
        Channel(name = "Rai 5", streamUrl = "https://mediapolis.rai.it/relinker/relinkerServlet.htm?cont=72382", category = "Cultura", isPreloaded = true),
        Channel(name = "Canale 5", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-la5/playlist.m3u8", category = "Generale", isPreloaded = true),
        Channel(name = "Italia 1", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-la1/playlist.m3u8", category = "Intrattenimento", isPreloaded = true),
        Channel(name = "Rete 4", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-la4/playlist.m3u8", category = "Generale", isPreloaded = true),
        Channel(name = "20 Mediaset", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-l20/playlist.m3u8", category = "Intrattenimento", isPreloaded = true),
        Channel(name = "Sky TG24", streamUrl = "https://skyianywhere2-i.akamaihd.net/hls/live/200275/tg24/playlist.m3u8", category = "News", isPreloaded = true),
        Channel(name = "TGCOM 24", streamUrl = "https://tgb1.akamaized.net/hls/live/2035260/TGCOM24/master.m3u8", category = "News", isPreloaded = true),
        Channel(name = "La7", streamUrl = "https://main-seg.cdn.top-ix.org/la7/live/la7.isml/index.m3u8", category = "Generale", isPreloaded = true),
        Channel(name = "La7d", streamUrl = "https://main-seg.cdn.top-ix.org/la7/live/la7d.isml/index.m3u8", category = "Intrattenimento", isPreloaded = true),
        Channel(name = "TV8", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-t8/playlist.m3u8", category = "Intrattenimento", isPreloaded = true),
        Channel(name = "Boing", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-boi/playlist.m3u8", category = "Bambini", isPreloaded = true),
        Channel(name = "Cartoonito", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-cart/playlist.m3u8", category = "Bambini", isPreloaded = true),
        Channel(name = "Iris", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-iri/playlist.m3u8", category = "Film", isPreloaded = true),
        Channel(name = "Cielo", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-cie/playlist.m3u8", category = "Documentari", isPreloaded = true),
        Channel(name = "Real Time", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-rt/playlist.m3u8", category = "Lifestyle", isPreloaded = true),
        Channel(name = "Focus", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-foc/playlist.m3u8", category = "Documentari", isPreloaded = true),
        Channel(name = "Top Crime", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-top/playlist.m3u8", category = "Film", isPreloaded = true),
        Channel(name = "La5", streamUrl = "https://live02-seg.msf.cdn.mediaset.net/live/tv-la5b/playlist.m3u8", category = "Intrattenimento", isPreloaded = true)
    )

    val categories = listOf(
        "Generale", "News", "Sport", "Film",
        "Intrattenimento", "Documentari", "Bambini",
        "Musica", "Educativo", "Cultura", "Lifestyle"
    )
}
