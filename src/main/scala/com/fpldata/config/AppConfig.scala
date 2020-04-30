package com.fpldata.config

import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto._

case class AppConfig (
  fplApi: FPLApiConfig
)

case class FPLApiConfig(
  baseUrl: String,
  allInfoEndpoint: String,
  playerStatEndpoint: String
)

object AppConfig {

  def apply(config: Config): AppConfig =
    ConfigSource.fromConfig(config).at("app").loadOrThrow[AppConfig]

}
