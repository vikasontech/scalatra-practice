package com.example.app.db.config.serializer

import java.time.LocalDate

import org.json4s.CustomSerializer
import org.json4s.JsonAST.{JString, JValue}

class DateSerializer extends CustomSerializer[LocalDate](
  implicit formats => ( {
    case json: JValue =>
      val date = json.values.toString
      val day = date.substring(0, 2).toInt
      val month = date.substring(3, 5).toInt
      val year = date.substring(6, 10).toInt
      LocalDate.of(year, month, day)
  }, {
    case a: LocalDate => JString(a.toString)
  })) {}



