name := "aSync"
version := "1.0"
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.yaml"                   %  "snakeyaml"       % "1.18",
  "org.typelevel"              %  "spire_2.11"      % "0.14.1",
  "ch.qos.logback"             %  "logback-classic" % "1.1.7",
  "com.typesafe.scala-logging" %% "scala-logging"   % "3.5.0",
  "com.google.protobuf"        %  "protobuf-java"   % "3.2.0",
  "de.sciss"                   %% "scalaosc"        % "1.1.5",
  "com.github.nscala-time"     %% "nscala-time"     % "2.16.0",
  "org.yaml"                   %  "snakeyaml"       % "1.18"

)

mainClass in (Compile, run) := Some("async.server.Server")