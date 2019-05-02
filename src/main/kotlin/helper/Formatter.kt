package helper

/** Author: Bc. Juraj Macak **/

class Formatter {

    companion object {

        fun timeFormatterInc(value: Double, noAddition: Boolean = false): String {
            var hours = (value / 3600).toInt() // Begining is in 11:00
            if (!noAddition) {
                hours += 15
            }
            val rem = value % 3600
            val minutes = (rem / 60).toInt()
            val seconds = (rem % 60).toInt()

            val formatedHour = "${if (hours < 10) "0${hours}" else hours}"
            val formatedMinutes = "${if (minutes < 10) "0${minutes}" else minutes}"
            val formatedSeconds = "${if (seconds < 10) "0${seconds}" else seconds}"

            return "${formatedHour} : ${formatedMinutes} : ${formatedSeconds}"
        }

        fun round2Decimals(value: Double): String {
            return "${Math.round(value * 10.0) / 10.0}"
        }

        fun convertToText(value: String): String {
            when(value) {
                // Links
                "A" -> return "Linka A"
                "B" -> return "Linka B"
                "C" -> return "Linka C"

                // BusTyps
                "S" -> return "Malý autobus"
                "L" -> return "Veľký autobus"
                "M" -> return "Mikrobus"

                // Strategy
                "WAIT" -> return "S čakaním"
                "NO WAIT" -> return "Bez čakania"
            }

            return value
        }

    }

}