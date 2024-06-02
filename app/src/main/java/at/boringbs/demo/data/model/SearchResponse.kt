package at.boringbs.demo.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SearchResponse(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<GithubSearchItem>,
    @SerialName("total_count")
    val totalCount: Int
)
