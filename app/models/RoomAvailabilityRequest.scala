package models

import org.joda.time.DateTime

case class RoomAvailabilityRequest(startDate: DateTime,
                                   duration: Int = 60,
                                   roomType: String = "any",
                                   maxNumberOfGuests: Int = 10,
                                   equipment: Option[Equipment] = None)