package Monster
import io.circe.parser
import io.circe.generic.auto._
import ApiCaller._
import io.circe.HCursor
import java.awt.Desktop.Action
import io.circe.Decoder
import java.io.PrintWriter

case class Monster(index:String,name:String,size:String,typeOf:String,subType:String,alignment:String,armorClass:Int,hitPoints:Int,hitDice:Int,speed:Speed,strength:Int,dexterity:Int,constitution:Int,intelligence:Int,wisdom:Int,charisma:Int,proficiencies:List[Proficiencies],damageVulnerabilities:List[String],damageResistences:List[String],damageImmunities:List[String],conditionImmunities:List[String],senses:List[Senses],languages:String,challengeRating:Int,specialAbilities:List[SpecialAbilities],actions:List[Actions],legendaryActions:List[LegendaryActions],url:String)

//Had to create a ton of case classes since this is a nested JsonString otherwise it wouldn't decode correctly
case class Speed(walk:String,swim:String)
case class Proficiencies(value:Int, proficiency:List[Proficiency])
case class Proficiency(index: String,name:String,url:String)
case class DamageType (name:String,url:String)
case class Damage(damage_type:DamageType,damage_dice:String,damage_bonus:Int)
case class Usage(type_of:String,times:Int)
case class Senses(darkvision: String, passive_perception:Int)
case class DC(dc_type:DCType,dc_value:Int,success_type:String)
case class DCType(name:String,url:String)
case class SpecialAbilities(name:String,desc:String,dc:DC)
case class Actions(name:String, desc:String,attack_bonus:Int,dc:DC,damage:Damage)
case class LegendaryActions(name:String,desc:String,damage:Damage)


//Actual ApiCall Object with Monster methods
object MonsterAPICaller {
    def callDAndDMonsterApi(): String= {
    val url = "https://www.dnd5eapi.co/api/monsters"//[additionaly can add /index number for monster]
    val apiInf = scala.io.Source.fromURL(url).mkString.stripMargin
    println(apiInf)
    apiInf
    }

    def decodeJsonToMonsterObject(apiInfo: String) {
      implicit val MonsterDecoder: Decoder[Monster] =
        (hCursor: HCursor) => {
          for {
            index <- hCursor.get[String]("index")
            name <- hCursor.downField("name").as[String]
            size <- hCursor.downField("size").as[String]
            typeOf <- hCursor.downField("type_of").as[String]
            subType <- hCursor.downField("sub_type").as[String]
            alignment <- hCursor.downField("alignment").as[String]
            armorClass <- hCursor.downField("armor_class").as[Int]
            hitPoints <- hCursor.downField("hit_points").as[Int]
            hitDice <- hCursor.downField("hit_dice").as[Int]
            speed <- hCursor.downField("speeed").as[Speed]
            strength <- hCursor.downField("strength").as[Int]
            dexterity <- hCursor.downField("dexterity").as[Int]
            constitution <- hCursor.downField("constitution").as[Int]
            intelligence <- hCursor.downField("intelligence").as[Int]
            wisdom <- hCursor.downField("wisdom").as[Int]
            charisma <- hCursor.downField("charisma").as[Int]
            proficiencies <- hCursor.downField("proficiencies").as[List[Proficiencies]]
            damageVulnerabilities <- hCursor.downField("damage_vulnerabilities").as[List[String]]
            damageResistences <- hCursor.downField("damage_resistences").as[List[String]]
            damageImmunities <- hCursor.downField("damage_immunities").as[List[String]]
            conditionImmunities <- hCursor.downField("condition_immunities").as[List[String]]
            senses <- hCursor.downField("senses").as[List[Senses]]
            languages <- hCursor.downField("languages").as[String]
            challengeRating <- hCursor.downField("challenge_rating").as[Int]
            specialAbilities <- hCursor.downField("special_abilities").as[List[SpecialAbilities]]
            actions <- hCursor.downField("actions").as[List[Actions]]
            legendaryActions <- hCursor.downField("legendary_actions").as[List[LegendaryActions]]
            url <- hCursor.downField("url").as[String]
          } yield Monster(index,name,size,typeOf,subType,alignment,armorClass,hitPoints,hitDice,speed,    strength,dexterity,constitution,intelligence,wisdom,charisma,proficiencies,damageVulnerabilities,damageResistences,damageImmunities,conditionImmunities,senses,languages,challengeRating,specialAbilities,actions,legendaryActions,url)
        }
      
          val decodingResult = parser.decode[List[Monster]](apiInfo)
      decodingResult match {
        case Right(monsters) => 
          monsters.map(println)
          val writer = new PrintWriter("monsters.json")
          writer.print(monsters)
          writer.close()
        case Left(error) => println(error.getMessage())
      }
    }

} 







