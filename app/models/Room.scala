package models

import play.api.libs.json.Json

case class Room (name: String,
                 capacity: Int,
                 equipment: Equipment,
                 colour: String,
                 accessible: Boolean,
                 roomType: String)

object Room {
  implicit val formats = Json.format[Room]
}