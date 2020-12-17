import scala.io.Source
  


  lazy val root = (project in file(".")).
  
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.3"
    )),
    name := "scalatest-example"

  )

  libraryDependencies += "com.lihaoyi" %% "requests" % "0.6.5"
  libraryDependencies += "io.circe" %% "circe-core" % "0.13.0"
  libraryDependencies += "io.circe" %% "circe-generic" % "0.13.0"
  libraryDependencies += "io.circe" %% "circe-parser" % "0.13.0"
  libraryDependencies += "io.spray" %%  "spray-json" % "1.3.6"
  

