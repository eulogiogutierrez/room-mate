package models

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.libs.json.{Json, Reads}

case class Booking(roomName: String,
                   startDate: DateTime,
                   endDate: DateTime,
                   owner: String,
                   guests: List[String])





object Booking {
//2018-02-28 09:00
  val dateFormat = "yyyy-MM-dd HH:mm"

  val jodaDateReads = Reads[DateTime](js =>
    js.validate[String].map[DateTime](dtString =>
      DateTime.parse(dtString, DateTimeFormat.forPattern(dateFormat))
    )
  )


  implicit val dateReads: Reads[DateTime] =  Reads[DateTime](js =>
    js.validate[String].map[DateTime](dtString =>
      DateTime.parse(dtString, DateTimeFormat.forPattern(dateFormat))
    )
  )
  implicit val formats = Json.format[Booking]
}


