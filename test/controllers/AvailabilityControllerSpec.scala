package controllers

import java.text.SimpleDateFormat

import models.RoomAvailabilityRequest
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatestplus.play.{OneAppPerSuite, OneAppPerTest, PlaySpec}

/**
  * Created by user on 27/02/18.
  */
class AvailabilityControllerSpec extends PlaySpec with OneAppPerTest {

  "AvailabilityController" must {
    " return one room  " when {
      val controller = new AvailabilityController()
      val dateFormat = "yyyy-MM-dd HH:mm"
      val date = "2018-02-28 09:00"
      val startDate = DateTime.parse(date, DateTimeFormat.forPattern(dateFormat))
      var request = RoomAvailabilityRequest(startDate, 30, "open", 3)
      val length = controller.listOfAvailableRooms(request).length
      println(length)
    }
  }

}
