import sbt._

object Dependencies {

  object PureConfig {
    private val version = "0.12.3"
    val pureConfig = "com.github.pureconfig" %% "pureconfig" % version
  }

  object Sttp {
    private val version = "2.1.0-RC1"
    val client = "com.softwaremill.sttp.client" %% "core" % version
    val circe = "com.softwaremill.sttp.client" %% "circe" % version
  }

  object Circe {
    private val version = "0.13.0"
    val Core = "io.circe" %% "circe-core" % version
    val Generic = "io.circe" %% "circe-generic" % version
    val GenericExtras = "io.circe" %% "circe-generic-extras" % version
    val Parser = "io.circe" %% "circe-parser" % version
  }
}
