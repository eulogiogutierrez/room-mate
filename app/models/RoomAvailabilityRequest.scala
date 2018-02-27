package models

import org.joda.time.DateTime
import play.api.libs.json.Json

case class RoomAvailabilityRequest(startDate: DateTime,
                                   duration: Int = 60,
                                   roomType: String = "any",
                                   maxNumberOfGuests: Int = 10,
                                   equipment: Option[Equipment] = None)


object RoomAvailabilityRequest {
  implicit val formats = Json.format[RoomAvailabilityRequest]
}