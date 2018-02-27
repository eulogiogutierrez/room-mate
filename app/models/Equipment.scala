package models

import play.api.libs.json.Json

case class Equipment (chair: Boolean,
                      table: Boolean,
                      desk: Boolean,
                      tv: Boolean,
                      whiteboard: Boolean,
                      projector: Boolean,
                      phone: Boolean,
                      sofa: Boolean)
object Equipment {
  implicit val formats = Json.format[Equipment]
}
