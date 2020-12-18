import scala.io.Source
  


  lazy val root = (project in file(".")).
  
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.3"
    )),
    name := "scalatest-example"

  )

  
  
  libraryDependencies += "io.spray" %%  "spray-json" % "1.3.6"
  

