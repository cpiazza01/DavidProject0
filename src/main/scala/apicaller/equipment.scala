package Equipment
import io.circe.parser
import io.circe.generic.auto._
import DAndDApiCaller._
import io.circe.HCursor
import java.awt.Desktop.Action
import io.circe.Decoder
import java.io.PrintWriter

// Main case class EquipmentCatagories
case class EquipmentCatagories(index:String,name:String,equipment:List[Equipment])

// SubParameter case classes that are made up of Lists
case class Equipment(index:String,name:String,equipmentCatagory:List[EquipmentCatagory],weaponCatagory:String,weaponRange:String,catagoryRange:String,cost:List[Cost],damage:List[Damage],range:List[Range],weight:Int,properties:List[Properties],url:String)
case class EquipmentCatagory(name:String,url:String)
case class Cost(quantity:Int,unit:String)
case class Damage(damageDice:String, damageType:DamageType)
case class DamageType(name:String,index:String,url:String)
case class Range(normal:Int,long:Int)
case class Properties(name:String,index:String,url:String)


//choose one from user menu
object Equipment {
  val equipmentCategrory = ""
  def getEquipmentCatagories(): String= {
    val equipCatUrl = "https://www.dnd5eapi.co/api/equipment-categories" //additionally you can dive deeper if you already know the categories by appending it to the end of the url.
    //Catagories [adventuring-gear,ammunition,arcane-foci,armor,artisan-tools,druidic-foci,equipment-packs,gaming-sets,heavy-armor,holy-symbols,kits]
    val equipmentInf = scala.io.Source.fromURL(equipCatUrl).mkString.stripMargin
    println(equipmentInf)
    equipmentInf

  }

  def decodeEquipmentJsonIntoEquipmenObject(equipmentInf:String){
    implicit val equipmentCatagoriesDecoder: Decoder[EquipmentCatagories] =
      (hCursor: HCursor) => {
        for {
          index <- hCursor.get[String]("Index")
          name <- hCursor.downField("name").as[String]
          equipment <- hCursor.downField("equipment").as[List[Equipment]]
        } yield EquipmentCatagories(index,name,equipment)
      }
      
      val decodingResult = parser.decode[List[EquipmentCatagories]](equipmentInf)

      decodingResult match {
          case Right(equipmentCatagories) => equipmentCatagories.map(println)
          case Left(error) => println(error.getMessage())
            
    }
  }

}  
      

