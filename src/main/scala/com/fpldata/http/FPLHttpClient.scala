package com.fpldata.http

import com.fpldata.config.FPLApiConfig
import com.fpldata.model.{BootstrapData, Player, PlayerStat, Team}
import sttp.client.HttpURLConnectionBackend
import io.circe.Error
import sttp.client._
import sttp.client.circe._
import cats.instances.either._
import cats.instances.list._
import cats.syntax.traverse._


class FPLHttpClient(apiConfig: FPLApiConfig) {
  private implicit val sttpBackend = HttpURLConnectionBackend()

  private val baseURL = apiConfig.baseUrl
  private val bootstrapData = requestBootstrapData

  def getBootstrapData: Either[ResponseError[Error], BootstrapData] = bootstrapData

  def getTeamsData: Either[ResponseError[Error], List[Team]] = getBootstrapData.map(_.teams)

  def getPlayersStatData: Either[ResponseError[Error], List[PlayerStat]] =
    getBootstrapData match {
      case Left(error) => Left(error)
      case Right(data) => data.playersInfo.map(info => getPlayerStatDataById(info.id)).sequence
    }

  def getPlayersData: Either[ResponseError[Error], List[Player]] = {
    (getBootstrapData, getPlayersStatData) match {
      case (Right(BootstrapData(_, info)), Right(stats)) => Right(info.zip(stats).map { case (pi, ps) => Player(pi, ps) })
      case (Left(error), _) => Left(error)
      case (_, Left(error)) => Left(error)
    }
  }

  def getPlayerStatDataById(playerId: Long): Either[ResponseError[Error], PlayerStat] = {
    val response = basicRequest
      .get(uri"$baseURL${apiConfig.playerStatEndpoint}$playerId/")
      .response(asJson[PlayerStat])
      .send()
    response.body
  }

  private def requestBootstrapData: Either[ResponseError[Error], BootstrapData] = {
    val response = basicRequest
      .get(uri"$baseURL${apiConfig.allInfoEndpoint}")
      .response(asJson[BootstrapData])
      .send()
    response.body
  }
}

object FPLHttpClient {
  def apply(apiConfig: FPLApiConfig): FPLHttpClient = new FPLHttpClient(apiConfig)
}
