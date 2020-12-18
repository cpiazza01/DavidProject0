import spray.json._
import DefaultJsonProtocol._


object Card {
  val cards: BufferedList[Card] = new BufferedList[Card]()
  
  class Card (
    id : Int,
    collectible : Int,
    slug: String,
    classId : Int,
    multiClassIds : Option[Array[String]],
    minionTypeId : Option[Int],
    cardTypeId : Int,
    setName : Option[String],
    rarityId : Int,
    artistName : Option[String],
    health : Int,
    attack : Option[Int],
    manaCost : Int,
    name : String,
    text : String,
    image : String,
    imageGold : String,
    flavorText : String,
    cropImage : Option[String],
    parentId : Option[Int],
    childIds : Option[Array[Int]],
    gameMode : String,
    duels : Option[Duels]
  )

  


object Duels {
  class Duels(relevant: Boolean, constructed: Boolean)
  object myJsonProtocol2 extends DefaultJsonProtocol {
    //implicit val duelsFormat = jsonFormat2(Duels)
  } 

  import myJsonProtocol2._
}