package ApiCaller
import Monster._
import scala.io.Source
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object apiCaller extends App {
  
  

  val apiInf =MonsterAPICaller.callDAndDMonsterApi()
}