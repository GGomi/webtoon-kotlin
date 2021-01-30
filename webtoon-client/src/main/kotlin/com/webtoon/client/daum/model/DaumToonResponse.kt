package com.webtoon.client.daum.model

import com.fasterxml.jackson.annotation.JsonProperty

class AppThumbnailImage(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("name")
    var name: Any? = null,

    @JsonProperty("size")
    var size: Any? = null,

    @JsonProperty("width")
    var width: Int? = null,

    @JsonProperty("height")
    var height: Int? = null,

    @JsonProperty("mediaType")
    var mediaType: Any? = null,

    @JsonProperty("encryptKey")
    var encryptKey: Any? = null,

    @JsonProperty("serviceStatus")
    var serviceStatus: String? = null
)

class Artist(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("name")
    var name: String? = null,

    @JsonProperty("penName")
    var penName: String? = null,

    @JsonProperty("introduction")
    var introduction: String? = null,

    @JsonProperty("history")
    var history: Any? = null,

    @JsonProperty("career")
    var career: Any? = null,

    @JsonProperty("pictureImage")
    var pictureImage: PictureImage? = null,

    @JsonProperty("smallPictureImage")
    var smallPictureImage: Any? = null,

    @JsonProperty("appImage")
    var appImage: Any? = null,

    @JsonProperty("nameImage")
    var nameImage: Any? = null,

    @JsonProperty("birthDay")
    var birthDay: Any? = null,

    @JsonProperty("debutDay")
    var debutDay: Any? = null,

    @JsonProperty("email")
    var email: String? = null,

    @JsonProperty("daumEmail")
    var daumEmail: Any? = null,

    @JsonProperty("daumEmailDisplayYn")
    var daumEmailDisplayYn: Any? = null,

    @JsonProperty("homepage")
    var homepage: String? = null,

    @JsonProperty("blog")
    var blog: Any? = null,

    @JsonProperty("cafe")
    var cafe: Any? = null,

    @JsonProperty("fancafe")
    var fancafe: Any? = null,

    @JsonProperty("facebook")
    var facebook: Any? = null,

    @JsonProperty("twitter")
    var twitter: Any? = null,

    @JsonProperty("teamYn")
    var teamYn: Any? = null,

    @JsonProperty("team")
    var team: Any? = null,

    @JsonProperty("joinDay")
    var joinDay: Any? = null,

    @JsonProperty("breakDay")
    var breakDay: Any? = null,

    @JsonProperty("artistOrder")
    var artistOrder: Int? = null,
    @JsonProperty("artistType")
    var artistType: String? = null,

    @JsonProperty("authUserinfo")
    var authUserinfo: Any? = null,

    @JsonProperty("agency")
    var agency: Any? = null
)

class ThumbnailImage(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("url")
    var url: String = "",

    @JsonProperty("name")
    var name: Any? = null,

    @JsonProperty("size")
    var size: Any? = null,

    @JsonProperty("width")
    var width: Int? = null,

    @JsonProperty("height")
    var height: Int? = null,

    @JsonProperty("mediaType")
    var mediaType: Any? = null,

    @JsonProperty("encryptKey")
    var encryptKey: Any? = null,

    @JsonProperty("serviceStatus")
    var serviceStatus: String? = null
)

class PictureImage(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("name")
    var name: Any? = null,

    @JsonProperty("size")
    var size: Any? = null,

    @JsonProperty("width")
    var width: Int? = null,

    @JsonProperty("height")
    var height: Int? = null,

    @JsonProperty("mediaType")
    var mediaType: Any? = null,

    @JsonProperty("encryptKey")
    var encryptKey: Any? = null,

    @JsonProperty("serviceStatus")
    var serviceStatus: String? = null
)

class Result(
    @JsonProperty("status")
    var status: String? = null,

    @JsonProperty("message")
    var message: String? = null
)

class WebtoonService(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("webtoonId")
    var webtoonId: Int? = null,

    @JsonProperty("serviceTarget")
    var serviceTarget: String? = null
)

class WebtoonWeek(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("weekDay")
    var weekDay: String? = null
)

class LatestWebtoonEpisode(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("episode")
    var episode: Int? = null,

    @JsonProperty("title")
    var title: String? = null,

    @JsonProperty("shortTitle")
    var shortTitle: Any? = null,

    @JsonProperty("thumbnailImage")
    var thumbnailImage: ThumbnailImage? = null,

    @JsonProperty("episodeImage")
    var episodeImage: EpisodeImage? = null,

    @JsonProperty("encryptUseYn")
    var encryptUseYn: String? = null,

    @JsonProperty("serviceStatus")
    var serviceStatus: String? = null,

    @JsonProperty("articleId")
    var articleId: Int? = null,

    @JsonProperty("commentUseYn")
    var commentUseYn: Any? = null,

    @JsonProperty("dateCreated")
    var dateCreated: String? = null,

    @JsonProperty("webtoon")
    var webtoon: Any? = null,

    @JsonProperty("serviceType")
    var serviceType: Any? = null,

    @JsonProperty("multiType")
    var multiType: Any? = null,

    @JsonProperty("multiBgm")
    var multiBgm: Any? = null,

    @JsonProperty("multiBackgroundImage")
    var multiBackgroundImage: Any? = null,

    @JsonProperty("price")
    var price: Int? = null,

    @JsonProperty("padtoonImage")
    var padtoonImage: Any? = null,

    @JsonProperty("voteTarget")
    var voteTarget: Any? = null,

    @JsonProperty("shareVoteTarget")
    var shareVoteTarget: Any? = null,

    @JsonProperty("isTopRecommend")
    var isTopRecommend: Boolean? = null,

    @JsonProperty("simpleUrl")
    var simpleUrl: Any? = null,

    @JsonProperty("specialSearchString")
    var specialSearchString: Any? = null,

    @JsonProperty("specialSearchUrl")
    var specialSearchUrl: Any? = null,

    @JsonProperty("previewEndDate")
    var previewEndDate: Any? = null,

    @JsonProperty("isPaid")
    var isPaid: Boolean? = null,

    @JsonProperty("payWebtoonEpisode")
    var payWebtoonEpisode: Any? = null,

    @JsonProperty("product")
    var product: Any? = null,

    @JsonProperty("ticketAvailable")
    var ticketAvailable: Boolean? = null,

    @JsonProperty("ageGrade")
    var ageGrade: Int? = null
)

class EpisodeImage(
    @JsonProperty("id")
    var id: Int? = null,

    @JsonProperty("url")
    var url: String? = null,

    @JsonProperty("name")
    var name: Any? = null,

    @JsonProperty("size")
    var size: Any? = null,

    @JsonProperty("width")
    var width: Int? = null,

    @JsonProperty("height")
    var height: Int? = null,

    @JsonProperty("mediaType")
    var mediaType: Any? = null,

    @JsonProperty("encryptKey")
    var encryptKey: Any? = null,

    @JsonProperty("serviceStatus")
    var serviceStatus: String? = null
)

class DaumRestTemplate(
    @JsonProperty("result")
    var result: Result,

    @JsonProperty("data")
    var data: List<Datum>
)

class Datum(
    @JsonProperty("id")
    val id: Int,

    @JsonProperty("nickname")
    var nickname: String? = null,

    @JsonProperty("webtoonType")
    var webtoonType: String? = null,

    @JsonProperty("title")
    var title: String = "",

    @JsonProperty("englishTitle")
    var englishTitle: Any? = null,

    @JsonProperty("finishYn")
    var finishYn: String? = null,

    @JsonProperty("titleImage2")
    var titleImage2: Any? = null,

    @JsonProperty("thumbnailImage")
    val thumbnailImage: ThumbnailImage?,

    @JsonProperty("thumbnailImage2")
    val thumbnailImage2: ThumbnailImage?,

    @JsonProperty("padThumbnailImage")
    var padThumbnailImage: Any? = null,

    @JsonProperty("artistCommentImage")
    var artistCommentImage: Any? = null,

    @JsonProperty("homeThumbnailImage")
    var homeThumbnailImage: Any? = null,

    @JsonProperty("appThumbnailImage")
    var appThumbnailImage: AppThumbnailImage? = null,

    @JsonProperty("introduction")
    var introduction: String? = null,

    @JsonProperty("serviceStatus")
    var serviceStatus: String? = null,

    @JsonProperty("weekTerm")
    var weekTerm: String? = null,

    @JsonProperty("articleId")
    var articleId: Int? = null,

    @JsonProperty("media")
    var media: String? = null,

    @JsonProperty("url")
    var url: Any? = null,

    @JsonProperty("simpleUrl")
    var simpleUrl: Any? = null,

    @JsonProperty("webtoonGroupId")
    var webtoonGroupId: Any? = null,

    @JsonProperty("payYn")
    var payYn: String? = null,

    @JsonProperty("payType")
    var payType: String? = null,

    @JsonProperty("price")
    var price: Int? = null,

    @JsonProperty("ageGrade")
    var ageGrade: Int? = null,

    @JsonProperty("restYn")
    var restYn: String? = null,

    @JsonProperty("monopolize")
    var monopolize: String? = null,

    @JsonProperty("dateCreated")
    var dateCreated: String? = null,

    @JsonProperty("webtoonComment")
    var webtoonComment: Any? = null,

    @JsonProperty("cp")
    var cp: Any? = null,

    @JsonProperty("webtoonWeeks")
    var webtoonWeeks: List<WebtoonWeek>? = null,

    @JsonProperty("webtoonEpisodes")
    var webtoonEpisodes: Any? = null,

    @JsonProperty("previewWebtoonEpisodes")
    var previewWebtoonEpisodes: Any? = null,

    @JsonProperty("latestWebtoonEpisode")
    var latestWebtoonEpisode: LatestWebtoonEpisode? = null,

    @JsonProperty("webtoonServices")
    var webtoonServices: List<WebtoonService>? = null,

    @JsonProperty("relatedProducts")
    var relatedProducts: Any? = null,

    @JsonProperty("promotionContents")
    var promotionContents: Any? = null,

    @JsonProperty("score")
    var score: Double? = null,

    @JsonProperty("tag")
    var tag: Any? = null,

    @JsonProperty("isNew")
    var isNew: Boolean? = null,

    @JsonProperty("averageScore")
    var averageScore: Double? = null,

    @JsonProperty("seriesYn")
    var seriesYn: Any? = null,

    @JsonProperty("ranking")
    var ranking: Int? = null,

    @JsonProperty("diff")
    var diff: Int? = null,

    @JsonProperty("metricsScore")
    var metricsScore: Int? = null,

    @JsonProperty("sort")
    var sort: String? = null,

    @JsonProperty("sortWeight")
    var sortWeight: Int? = null
)
