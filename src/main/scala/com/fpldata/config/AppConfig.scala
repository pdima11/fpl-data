package com.fpldata.config

import com.fpldata.config.AppConfig.FplApiConfig
import com.typesafe.config.Config
import pureconfig.ConfigSource
import pureconfig.generic.auto._

case class AppConfig (
  fplApi: FplApiConfig
)

object AppConfig {
  case class FplApiConfig (
    baseUrl: String,
    playersInfoEndpoint: String
  )

  def apply(config: Config): AppConfig =
    ConfigSource.fromConfig(config).at("app").loadOrThrow[AppConfig]

}
