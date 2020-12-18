//External Libraries
import java.io.PrintWriter
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import java.security.AllPermission
import scala.collection.mutable.ArrayBuffer
import myJsonProtocol._
import myJsonProtocol2._
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
    token
  }

  def getCards(token: String): Unit = {
    val cardRequestURL = s"https://us.api.blizzard.com/hearthstone/cards?locale=en_US&access_token=$token"
    val response = io.Source.fromURL(cardRequestURL).mkString.parseJson.asJsObject.fields("cards").prettyPrint
    val temp = response
    val fileName = "cards.json"
    writeToFile(fileName, temp)
    
  }

  def parseCardToCardObject(fileName: String): Unit = {
    val fileName = "cards.json"
    val lines = io.Source.fromFile(fileName).getLines()
    val counter = 0
    for (line.trimmed <- lines) {
      line match {
        case 1 if (line.contains("artistName")) => card.artistName_= selectObjectValues(line,14)
      }
      
    }
  }

  def selectSubstring(line: String, index: Int, typeOf: Any): String = {
    line.trim()
    typeOf match {
      case 1  if(typeOf.getClass == String) => {
        val sub = line.substring(index, line.length())} 
        sub
      case 2 if(typeOf.getClass == Int) => {
        val sub = line.substring(index,line.length()).toInt
        sub
      }
      case 3 if(typeOf.getClass() == Array) => {
        line.split("(?=\\[) | ")
      }
    }
    line.trim()
    val sub = line.substring(index, line.length())
    sub
  }

  def selectIntValues(line: String, index: Int): Int = {
    line.trim()
    val sub = line.substring(index, line.length).toInt
    sub

  }

  def selectArrayValues(line: String)

  def writeToFile(file: String, info: Any) {
    val writer = new PrintWriter(s"$file")
    writer.print(info)
    writer.close()
  }

  

  val token = getToken()
  val cards: Unit = getCards(token: String)
  
}