package controllers

import javax.inject._

import models.{Room, RoomAvailabilityRequest}
import play.api.mvc._

import scala.concurrent.Future

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
    List()
  }

}
