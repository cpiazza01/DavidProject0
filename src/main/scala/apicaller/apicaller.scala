package DAndDApiCaller
import Monster._
import Rules._
import Equipment._
import scala.io.Source
import scala.io.StdIn.readLine
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object DAndDApiCaller  extends App{
  
  def step1StartApplication(): String= {
    val greeting = "Hello and welcome to the D&D Api Caller. In this application you will be able to select whether you want to pull information about monsters,rules of the game, or equipment. One you call the api you will be asked what you would like to know about the particular category you have chosen. All goes well you should recieve information back based on the query you made.This app is still being worked on so look for more content to come in the future."
    println(greeting)
    println("What should I call you?")
    val name = readLine()
    name
  }

  def step2GetGenderResponse(name: String): Unit = {
    print("Are you a man or woman?")
    val gender = readLine()
    while (false) { 
      gender match {
      case "man" => {println(s"Good Evening Sir $name! Taking a break from adventuring about the kingdom, are we? How may I be of assistance to thee? Would thoust care to:\n")}
      false
      case "woman" => {println(s"Good Evening Ma'daam $name! Taking a break from adventuring about the kingdom, are we? How may I be of assistance to thee? Would thoust care to:\n")}
      false
      case _ => println("I'm aplogize Sir or Ma'daam, but it does seem that thoughst did not enter an exceptable gender when asked. Please enter man or woman when asked.\n")
      step2GetGenderResponse(name: String)
      }
    }
  }

  def step3ShowStartMenuAndGetInput() {
    val menu = println(s"\n\n\n Please let me know what you would like to do (Please enter a number from the list below): \n" +
               s"1) Need help slaying a ferocious beast? Why not look information about the beast! \n" +
               s"2) Need to reequip thy self with new equipment? Why not browse the store for a new item? You can look but unfortunately you are not able to buy as of just yet.\n" +
               s"3) Need to look up a specific declaration from the king as to what thoust is allowed to do on his land while adventuring? Take a look at the Rules to make sure thost does not end up in the stocks.") 
    println(menu)
    val choice = readLine()
    while (true) {
      val selection = choice match {
        case "1" => println("With the new knowlege your going to gain, thost should find no difficulty in   slaying monsters")
        false
        case "2" => println("The wenches shall fall at thy sires feet, when they see thoust new armor!")
        case choice if(choice == "3") => println("Oh Thank the heavens, The stockades are no place for  someone of thoust standing!")
        false
        case _ => println("I'm sorry my lord, I must have not explained well enough. If thoust wouldn't   mind please input either 1 , 2 , or 3 based on what thoust desires to do.")
        step3ShowStartMenuAndGetInput()
      }   
    }     
  }

  val monsterApiInf = MonsterAPICaller.callDAndDMonsterApi()
  val rulesApiInf = RulesApiCaller.callDAndDRulesApi()
  val equipmentInf =  Equipment.getEquipmentCatagories()
  
  // Run
  val name = step1StartApplication()
  step2GetGenderResponse(name:String)
  step3ShowStartMenuAndGetInput()
}