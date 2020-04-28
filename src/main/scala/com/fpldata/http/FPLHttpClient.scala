package com.fpldata.http

import com.fpldata.config.FPLApiConfig
import com.fpldata.model.BootstrapData
import sttp.client.HttpURLConnectionBackend
import io.circe.Error
import sttp.client._
import sttp.client.circe._



class FPLHttpClient(apiConfig: FPLApiConfig) {
    private implicit val sttpBackend = HttpURLConnectionBackend()

    private val baseURL = apiConfig.baseUrl

    def getBootstrapData(): Either[ResponseError[Error], BootstrapData] = {
        val request = basicRequest
          .get(uri"$baseURL${apiConfig.allInfoEndpoint}")
          .response(asJson[BootstrapData])
        val response = request.send()
        response.body
    }
}

object FPLHttpClient {
    def apply(apiConfig: FPLApiConfig): FPLHttpClient = new FPLHttpClient(apiConfig)
}
