package com.iamkurtgoz.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.iamkurtgoz.domain.model.FavoriteUIModel
import javax.inject.Inject

internal class FavoriteMapper @Inject constructor() {

    fun map(response: List<DocumentSnapshot>): List<FavoriteUIModel> = response.map { data ->
        with(data) {
            FavoriteUIModel(
                documentId = this.id,
                id = this.data?.get("id")?.toString(),
                email = this.data?.get("email")?.toString()
            )
        }
    }
}
