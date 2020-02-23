val ScalatraVersion = "2.7.0-RC1"

organization := "com.example"

name := "sample"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.0"

val akka = "2.6.0"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.19.v20190610" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.scalatra" %% "scalatra-json" % ScalatraVersion,
  "org.json4s" %% "json4s-jackson" % "3.7.0-M2",
  // swagger
  //  "org.json4s"   %% "json4s-native" % "3.7.0-M2",
  //  "org.scalatra" %% "scalatra-swagger" % ScalatraVersion,
  //"org.webjars" % "swagger-ui" % "2.0.21",
  "org.scalatra.scalate" %% "scalate-core" % "1.9.5",
  // akka
  "com.typesafe.akka" %% "akka-stream" % akka, // stream not working with scalatra :(
  "com.typesafe.akka" %% "akka-actor" % akka,
  //  zio
  //  "dev.zio" %% "zio" % "1.0.0-RC17",
  //  "dev.zio" %% "zio-streams" % "1.0.0-RC17",

  //mongo
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.8.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11",
  "ch.rasc" % "bsoncodec" % "1.0.1",

  "org.eclipse.jetty" % "jetty-webapp" % "9.4.8.v20171121" % "container",
  "org.eclipse.jetty" % "jetty-webapp" % "9.4.8.v20171121" % "compile;container",
)

//addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.2")

//enablePlugins(SbtTwirl)
enablePlugins(ScalatraPlugin)
//enablePlugins(JavaAppPackaging)
