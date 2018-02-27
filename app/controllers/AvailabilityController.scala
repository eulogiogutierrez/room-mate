package controllers

import javax.inject._

import com.fasterxml.jackson.databind.JsonNode
import models.{Booking, Room, RoomAvailabilityRequest}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.Future
import scala.io.Source

@Singleton
class AvailabilityController @Inject() extends Controller {

  def post = Action.async(parse.json) {
    implicit request => {
      request.body.validate[RoomAvailabilityRequest].fold(
        errors => Future.successful(BadRequest()),
        roomAvailabilityRequest =>
          Future.successful(Ok(listOfAvailableRooms(roomAvailabilityRequest)))
      )
    }
  }

  private def listOfAvailableRooms(roomAvailabilityRequest: RoomAvailabilityRequest): List[Room] = {

    val allBookings: List[Booking] = readBookings()
    val allRooms: List[Room] = readRooms()

    val startDate = roomAvailabilityRequest.startDate
    val endDate = roomAvailabilityRequest.startDate.plusMinutes(roomAvailabilityRequest.duration)

    // look at free rooms by startdate and duration
    /*allBookings
      .filter(b => startDate.i)
      .filter(b => )*/

    // look at number of guests

    // look at equipment

    // look at roomType

    List()
  }


  private def readBookings(): List[Booking] = {
    val jValue = Json.parse(Source.fromFile(getClass.getResource("/conf/bookings.json").getPath).mkString)
    jValue.validate[List[Booking]].fold(
      errors => throw new Exception("Cannot read bookings."),
      bookings => bookings
    )
  }

  private def readRooms(): List[Room] = {
    val jValue = Json.parse(Source.fromFile(getClass.getResource("/conf/catalogue.json").getPath).mkString)
    jValue.validate[List[Room]].fold(
      errors => throw new Exception("Cannot read catalogue."),
      rooms => rooms
    )
  }

}
