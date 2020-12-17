import io.circe.Json
import io.circe.syntax._
import io.circe.generic.semiauto._
import scala.collection._

case class Duels(relevant: Boolean, constructed: Boolean)
case class Card (
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
  duels : Option[Duels],
)

implicit val cardDecoder: Decoder[Card] = json -> for {
  id <- json.get[Int]("id")
  collectible <- json.get[Int]("Collectible")
  slug <- json.get[String]("slug")
  classId <- json.get[Int]("classId")
  multiClassIds <- json.get[Option[Array[String]]]("multiClassIds") 
  minionTypeId <- json.get[Option[Int]]("minionTypeId")
  cardTypeId <- json.get[Int]("cardTypeId")
  setName <- json.get[Option[String]]("setName")
  rarityId <- json.get[Int](rarityId)
  artistName <- json.get[Option[String]]("artistName")
  health <- json.get[Int]("health")
  attack <- json.get[Option[Int]]("attack")
  manaCost <- json.get[Int]("manaCost")
  name <- json.get[String]("name")
  text <- json.get[String]("text")
  image <- json.get[String]("image")
  imageGold <- json.get[String]("imageGold")
  flavorText <- json.get[String]("flavorText")
  cropImage <- json.get[Option[String]]("cropImage")
  parentId <- json.get[Option[Int]]("parentId")
  childIds <- json.get[Option[Array[Int]]]("childIds")
  gameMode <- json.get[String]("gameMode")
  duels <- json.get[Option[List[Boolean,Boolean]]]("duels")} yield Card(id, collectible, slug, classId, multiClassIds, minionTypeId, cardTypeId, setNamerarityId, artistName, health, attack, manaCost, name, text, image, imageGold, flavorText, cropImageparentId, childIds, gameMode, duels)

implicit val duelsDecoder: Decoder[Duels] = Json -> for {
  relevant <- json.get[Boolean]("relevant")
  constructed <- json.get[Boolean]("constructed")
} yield Duels(relevant, constructed)