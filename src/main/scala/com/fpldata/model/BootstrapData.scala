package com.fpldata.model

import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec, JsonKey}

@ConfiguredJsonCodec final case class BootstrapData(
    teams: List[Team],
    @JsonKey("elements") players: List[Player]
)

object BootstrapData {
    implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
}