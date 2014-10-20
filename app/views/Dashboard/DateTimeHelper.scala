package views.Dashboard

import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat

object DateTimeHelper {

  def format(date: LocalDateTime, pattern: String = "HH:mm:ss dd-MM-yyyy") = DateTimeFormat.forPattern(pattern).print(date)

}
