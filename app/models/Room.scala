package models

case class Room (name: String,
                 capacity: Int,
                 equipment: Equipment,
                 colour: String,
                 accessible: Boolean,
                 roomType: String)

