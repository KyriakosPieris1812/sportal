package app

import cats.{Id, NonEmptyParallel}
import cats.data.NonEmptyList
import org.http4s.blaze.server.BlazeServerBuilder
import com.monovore.decline._

import scala.concurrent.ExecutionContext
import cats.implicits._
import cats.effect.Async
import org.http4s.HttpApp
import sup.{HealthCheck, HealthReporter}
import sup.data.HealthReporter

abstract class AbstractAppWithResource[F[_] : NonEmptyParallel : Async, C, R] {

  val name: String
  val version: String

  val executionContext: ExecutionContext

  val configParser: Command[C]

  def httpServer(config: HttpServerConfig, httpApp: HttpApp[F]): BlazeServerBuilder[F] =
    BlazeServerBuilder[F]
      .withExecutionContext(executionContext)
      .bindHttp(config.port, config.host)
      .withHttpApp(httpApp)

  def heathChecks(checks: NonEmptyList[HealthCheck[F, Id]]): HealthReporter[F, NonEmptyList, Id] =
    HealthReporter.parWrapChecks(checks)

  def backgroundJobs(config: C, resources: R): fs2.Stream[F, Unit]
}


case class HttpServerConfig(host: String, port: Int)

object HttpServerConfig {

  def load: Opts[HttpServerConfig] = {
    val host: Opts[String] = Opts.env[String]("HOST", help = "Server host", metavar = "HOST")
    val port: Opts[Int] = Opts.env[Int]("PORT", help = "Server port", metavar = "PORT")
    (host, port).mapN(HttpServerConfig.apply)
  }
}
