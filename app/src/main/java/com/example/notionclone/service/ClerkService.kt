package com.example.notionclone.service

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import org.json.JSONObject

private const val baseUrl = "https://pretty-manatee-97.clerk.accounts.dev/v1/client"
private const val signInUrl = "$baseUrl/sign_ins"
private const val sessionUrl = "$baseUrl/sessions"

const val host = "pretty-manatee-97.clerk.accounts.dev"
const val redirectUrl = "https://$host/oauth"


class ClerkService(
    private val httpClient: HttpClient
) {
    suspend fun signIn(): String? {
        println("signIn() called")
        return try {
            val response = httpClient.submitForm(
                url = signInUrl,
                formParameters = Parameters.build {
                    append("strategy", "oauth_google")
                    append("redirect_url", redirectUrl)
                }
            )

            val body = response.bodyAsText()
            println("Response signIn: $body")

            // Parse JSON để lấy URL
            val json = JSONObject(body)
            val redirectUrl = json
                .getJSONObject("response")
                .getJSONObject("first_factor_verification")
                .getString("external_verification_redirect_url")

            redirectUrl
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getSession(sessionId: String, sessionToken: String) {
        try {
            val response = httpClient.get("$sessionUrl/$sessionId") {
                header("__session", sessionToken)
            }

            val body = response.bodyAsText()
            println("Response getSession: $body")

        } catch (e: Exception) {
            e.printStackTrace()

            println("Cannot call getSession api")

        }

    }

}