package com.iamkurtgoz.firebase

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseInitializer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val FAVORITES = "favorites"
    }

    fun init() {
        FirebaseApp.initializeApp(context)
    }

    val auth: FirebaseAuth
        get() = Firebase.auth

    val firestore: FirebaseFirestore
        get() = Firebase.firestore

    suspend fun loginOrRegister(email: String, password: String): Flow<FirebaseUser?> = flow {
        val user = try {
            auth.signInWithEmailAndPassword(email, password).await().user
        } catch (e: FirebaseAuthException) {
            if (e.errorCode == "ERROR_USER_NOT_FOUND") {
                auth.createUserWithEmailAndPassword(email, password).await().user
            } else {
                throw e
            }
        }
        emit(user)
    }

    suspend fun readFavorites(email: String?): List<DocumentSnapshot> {
        if (auth.currentUser == null) return emptyList()
        val favorites = firestore.collection(FAVORITES)
            .whereEqualTo("email", email)
            .get()
            .await()
        return favorites.documents
    }

    suspend fun addFavorites(email: String?, id: String?): DocumentReference? {
        val isExist = readFavorites(email).count { it.get("id") == id } > 0
        if (!isExist) {
            val data = hashMapOf(
                "email" to email,
                "id" to id
            )
            return firestore.collection(FAVORITES)
                .add(data)
                .await()
        }
        return null
    }

    suspend fun removeFavorites(documentId: String): Any {
        val docRef = firestore.collection(FAVORITES).document(documentId)
        val doc = docRef.get().await()

        return if (doc.exists()) {
            docRef.delete()
        } else {
            Any()
        }
    }
}
