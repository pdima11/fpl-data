package com.fpldata.model

import java.time.{Instant, LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec, JsonKey}
import cats.syntax.either._

@ConfiguredJsonCodec case class GameweekStat(
  @JsonKey("round") gameweekNumber: Int,
  @JsonKey("element") playerId: Int,
  @JsonKey("opponent_team") opponentTeamId: Int,
  totalPoints: Int,
  wasHome: Boolean,
  kickoffTime: Instant,
  @JsonKey("team_h_score") homeTeamScore: Int,
  @JsonKey("team_a_score") AwayTeamScore: Int,
  minutes: Int,
  goalsScored: Int,
  assists: Int,
  cleanSheets: Int,
  goalsConceded: Int,
  ownGoals: Int,
  penaltiesSaved: Int,
  penaltiesMissed: Int,
  yellowCards: Int,
  redCards: Int,
  saves: Int,
  bonus: Int,
  @JsonKey("bps") bonusPoint: Int,
  influence: String, //"37.4"
  creativity: String, //"6.5"
  threat: String, //"106.0"
  ictIndex: String, //"15.0"
  value: Int,
  transfersBalance: Int,
  selected: Int,
  transfersIn: Int,
  transfersOut: Int
)

object GameweekStat {
  implicit val decodeLocalDate: Decoder[Instant] = Decoder.decodeString.emap { str =>
    Either.catchNonFatal(Instant.parse(str)).leftMap(err => "Failed to parse Instant: " + err.getMessage)
  }

  implicit val config: Configuration = Configuration.default.withSnakeCaseMemberNames

  implicit val encodeLocalDate: Encoder[Instant] = Encoder.encodeString.contramap[Instant](_.toString)

}
