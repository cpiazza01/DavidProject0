package Rules
import io.circe.parser
import io.circe.generic.auto._
import DAndDApiCaller._
import io.circe.HCursor
import java.awt.Desktop.Action
import io.circe.Decoder
import java.io.PrintWriter

case class Rule(name:String,index:String,desc:String,subsections:List[Subsections],url:String)
case class Subsections(name:String,index:String,url:String)

object RulesApiCaller {
  def callDAndDRulesApi(): String= {
    val rulesCategoryOfInterest = "spellcasting"
    val rulesApiUrl = s"https://www.dnd5eapi.co/api/rules/$rulesCategoryOfInterest" // [additionally you can call for the index["adventuring","appendix",combat,equipment,spellcasting,using-ability-scores],
    val rulesApiInf = scala.io.Source.fromURL(rulesApiUrl).mkString.stripMargin
    println(rulesApiInf)
    rulesApiInf
  }

  def decodeJsonToRulesObject(rulesApiInf:String) {
    implicit val rulesDecoder: Decoder[Rule] =
      (hCursor: HCursor) => {
        for {
          name <- hCursor.get[String]("name")
          index <- hCursor.downField("index").as[String]
          desc <- hCursor.downField("desc").as[String]
          subsections <- hCursor.downField("subsections").as[List[Subsections]]
          url <- hCursor.downField("url").as[String]
        } yield Rule(name,index,desc,subsections,url)
      }
    val decodingResult = parser.decode[List[Rule]](rulesApiInf)
    decodingResult match {
      case Right(rules) => rules.map(println)
      case Left(error) => println(error.getMessage())
    }
  }

}