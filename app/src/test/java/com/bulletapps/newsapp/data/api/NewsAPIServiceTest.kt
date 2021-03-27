package com.bulletapps.newsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {
    private lateinit var service:NewsAPIService
    private lateinit var server:MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    private fun enqueueMockResponse(
        fileName:String
    ){
        //get the resource file wih json as a inputstream
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)

        //Get the datasource from the stream an set in the memory buffer
        val source = inputStream.source().buffer()

        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sendRequest(){
        /*Runblock is the coroutine builder we use for testing.
        * this runs a new coroutine and blocks the current thread until its completion*/
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody =  service.getTopHeadlines("us",1).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=5e31094cf3554e5e93c3dd4fe389081b")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList?.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articlesList = responseBody!!.articles
            val article = articlesList?.get(0)
            assertThat(article?.author).isEqualTo("TMZ Staff")
            assertThat(article?.url).isEqualTo("https://www.tmz.com/2021/03/27/beyonce-storage-unit-theft-million-dollars-stolen-items-cops-investigating/")
            assertThat(article?.publishedAt).isEqualTo("2021-03-27T09:00:00Z")
        }
    }

}