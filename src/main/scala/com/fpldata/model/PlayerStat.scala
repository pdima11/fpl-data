package com.fpldata.model

import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec, JsonKey}

@ConfiguredJsonCodec final case class PlayerStat(
  @JsonKey("history") gameweeksStat: List[GameweekStat]
)

object PlayerStat {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
}
