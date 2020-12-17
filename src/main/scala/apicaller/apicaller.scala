//External Libraries
import java.io.PrintWriter
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import java.security.AllPermission
import scala.collection.mutable.ArrayBuffer
import io.circe.Json
import io.circe.syntax._
import io.circe.generic.semiauto._
import spray.json._
import DefaultJsonProtocol._
//Internal Libraries
import scala.sys.process._
import java.io.PrintWriter._
import sys.env



object APICaller extends App {
  val realm = "dalaran"
  val id = sys.env.get("CLIENT_ID").get
  val secret = sys.env.get("CLIENT_SECRET").get
  println(id)
  println(secret)

  def getToken(): String = {
    val tokenURL = s"curl -u ${id}:${secret} -d grant_type=client_credentials https://us.battle.net/oauth/token"
    val token = tokenURL.!!.split("\"")(3)
    println(token)
    token
  }

  def getCards(token: String): String = {
    val cardRequestURL = s"https://us.api.blizzard.com/hearthstone/cards?locale=en_US&access_token=$token"
    val apiInfo = requests.get(cardRequestURL)
    val responseJsonToString = apiInfo.toString()
    val fileName ="cars.json"
    writeToFile(fileName, apiInfo)

    val lineSource = ""
    lineSource
    
    
  }

  def writeToFile(file: String, info: Any) {
    val writer = new PrintWriter(s"$file")
    writer.print(info)
    writer.close()
  }

  def getAuctions(realmId: String, token: String): Unit = {
    val auctionURL = s"https://us.api.blizzard.com/data/wow/connected-realm/$realmId/auctions?namespace=dynamic-us&locale=en_US&access_token=$token"
    val apiInfo = requests.get(auctionURL)
    val jsonString = apiInfo.toString()
    val js = Json.fromString(jsonString)
    val file = "apiAuctions.json"
    writeToFile(file:String, apiInfo: Any)
  }

  val token = getToken()
  val cards: String = getCards(token: String)
  //getAuctions(realmId: String, token: String)
}