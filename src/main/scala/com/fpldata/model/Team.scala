package com.fpldata.model

import io.circe.generic.extras._

@ConfiguredJsonCodec final case class Team(
  id: Int,
  code: Int,
  name: String,
  shortName: String
)

object Team {
  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames
}