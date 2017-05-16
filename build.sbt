name := "aSync"

val commonSettings = Seq(
  scalaVersion := "2.12.2",
  version := "1.0"
)

val http4s = "0.17.0-M2"

lazy val server = project
  .settings(commonSettings)
  .settings(
    name := "async-server",
    libraryDependencies ++= Seq(
      "org.http4s"                 %% "http4s-dsl"          % http4s,
      "org.http4s"                 %% "http4s-blaze-server" % http4s,
      "org.http4s"                 %% "http4s-blaze-client" % http4s,

      "org.yaml"                   % "snakeyaml"            % "1.18",
      "org.typelevel"              %% "spire"               % "0.14.1",
      "ch.qos.logback"             % "logback-classic"      % "1.1.7",
      "com.typesafe.scala-logging" %% "scala-logging"       % "3.5.0",
      "com.google.protobuf"        % "protobuf-java"        % "3.2.0",
      "de.sciss"                   %% "scalaosc"            % "1.1.5"

      //"org.webjars"                % "jquery"               % "3.2.0"
    ),
    resourceGenerators in Compile += Def.task {
      val trg = (resourceManaged in Compile).value / "web" / "js" / "gFlow.js"
      IO.copyFile((fastOptJS in Compile in flow).value.data, trg)
      Seq(trg)
    }.taskValue
  )

lazy val flow = project
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "be.doeraene"  %%% "scalajs-jquery" % "0.9.1",
    skip in packageJSDependencies   := false,
    jsDependencies      += "org.webjars"  %   "jquery"         % "3.2.0" / "3.2.0/jquery.js"
  )

lazy val root = project.in(file("."))
  .aggregate(server, flow)