package helper

/** Author: Bc. Juraj Macak **/

class Formatter {

    companion object {

        fun timeFormatterInc(value: Double): String {
            val hours = (value / 3600).toInt() + 15 // Begining is in 11:00
            val rem = value % 3600
            val minutes = (rem / 60).toInt()
            val seconds = (rem % 60).toInt()

            val formatedHour = "${if (hours < 10) "0${hours}" else hours}"
            val formatedMinutes = "${if (minutes < 10) "0${minutes}" else minutes}"
            val formatedSeconds = "${if (seconds < 10) "0${seconds}" else seconds}"

            return "${formatedHour} : ${formatedMinutes} : ${formatedSeconds}"
        }

    }

}