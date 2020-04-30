package com.fpldata.model

import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec, JsonKey}

@ConfiguredJsonCodec final case class PlayerInfo(
  id: Long,
  firstName: String,
  secondName: String,
  teamCode: Int,
  @JsonKey("team") teamId: Int
)

object PlayerInfo {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
}