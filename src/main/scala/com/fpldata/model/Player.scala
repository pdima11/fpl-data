package com.fpldata.model

import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec, JsonKey}

@ConfiguredJsonCodec final case class Player(
  firstName: String,
  secondName: String,
  teamCode: Int,
  @JsonKey("team") teamId: Int
)

object Player {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
}