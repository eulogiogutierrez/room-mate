package controllers

import javax.inject._

import com.fasterxml.jackson.databind.JsonNode
import models.{Booking, Room, RoomAvailabilityRequest}
import org.joda.time.DateTime
import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._

import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.io.Source

@Singleton
class AvailabilityController @Inject() extends Controller {

  def post = Action.async(parse.json) {
    implicit request => {
      request.body.validate[RoomAvailabilityRequest].fold(
        errors => Future.successful(BadRequest("Request is not correct")),
        roomAvailabilityRequest => {
          val freeRooms = listOfAvailableRooms(roomAvailabilityRequest)
          Future.successful(Ok(Json.toJson(freeRooms)))
        }
      )
    }
  }

  def listOfAvailableRooms(roomAvailabilityRequest: RoomAvailabilityRequest): List[Room] = {

    val allBookings: List[Booking] = readBookings()
    val allRooms: List[Room] = readRooms()

    val startDate = roomAvailabilityRequest.startDate
    val endDate = roomAvailabilityRequest.startDate.plusMinutes(roomAvailabilityRequest.duration)
    Logger.info(s"all bookings : ${allBookings}")
    Logger.info(s"allRooms : ${allRooms}")

    // look at free rooms by startdate and duration
   val bookedRooms =  allBookings
      .filter(b => isBetweenDate(startDate,b.startDate, b.endDate ))
      .filter(b => isBetweenDate(endDate,b.startDate, b.endDate) )

    val bookedRoomNames = new ListBuffer[String]()
    for(booking <- bookedRooms){
      bookedRoomNames += booking.roomName
    }
    Logger.info(s"booked rooms : ${bookedRoomNames}")

    val availableRooms = allRooms.filter(
      r=> !bookedRoomNames.contains(r.name)
    )
    // look at number of guests
   /* if(freeRooms.length > 1 ) {
      free
    }*/

    // look at equipment

    // look at roomType
    Logger.info(s"available rooms : ${availableRooms}")
    availableRooms
  }


  def isBetweenDate(myDate: DateTime, fromDate:DateTime, tillDate:DateTime) : Boolean ={
    (myDate.isBefore(tillDate) && myDate.isAfter(fromDate)) ||
      myDate.isEqual(tillDate) ||
      myDate.isEqual(fromDate)
  }


  private def readBookings(): List[Booking] = {
    val jValue = Json.parse(Source.fromFile(getClass.getResource("/bookings.json").getPath).mkString)

    jValue.validate[List[Booking]].fold(
      errors => {
        println("throwing exception"+errors)
        throw new Exception("Cannot read bookings.")
      },
      bookings => bookings
    )
  }

  private def readRooms(): List[Room] = {
    val jValue = Json.parse(Source.fromFile(getClass.getResource("/catalogue.json").getPath).mkString)
    jValue.validate[List[Room]].fold(
      errors => throw new Exception("Cannot read catalogue."),
      rooms => rooms
    )
  }

}


