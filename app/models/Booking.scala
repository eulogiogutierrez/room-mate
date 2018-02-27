package models

import org.joda.time.DateTime

case class Booking(roomName: String,
                   startDate: DateTime,
                   endDate: DateTime,
                   owner: String,
                   guests: List[String])


