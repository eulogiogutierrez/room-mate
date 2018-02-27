package models

import org.joda.time.DateTime

case class Availability (roomName: String,
                         startDate: DateTime,
                         endDate: DateTime,
                         owner: String,
                         guests: List[String])


