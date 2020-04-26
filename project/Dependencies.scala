import sbt._

object Dependencies {

  object PureConfig {
    private val version = "0.12.3"
    val pureConfig = "com.github.pureconfig" %% "pureconfig" % version
  }
}
