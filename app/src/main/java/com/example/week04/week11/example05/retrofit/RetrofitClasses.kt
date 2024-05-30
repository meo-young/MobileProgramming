package com.example.week04.week11.example05.retrofit

import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class ChoicesResponse(
    @SerializedName("choices")
    val choiceList: List<Choice>
)

data class Choice(
    val text: String,
    val index: Int,
    val logprobs: Any?,
    val finish_reason: String
)

data class CompletionRequest(
    val model: String,
    val prompt: String,
    val temperature: Int,
    val max_tokens: Int
)

interface ChatGptAPIService {
    @POST("completions")
    suspend fun getCompletion(
        @Body request: CompletionRequest
    ): Response<ChoicesResponse>
}


object RetrofitClient {
    private const val BASE_URL = "https://api.openai.com/v1/"
    private val apikey = "Your API key"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Bearer $apikey")
                .build()
            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ChatGptAPIService = retrofit.create(ChatGptAPIService::class.java)
}
