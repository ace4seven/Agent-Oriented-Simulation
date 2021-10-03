package helper

import java.lang.Exception
import java.util.Locale
import java.text.NumberFormat



/** Author: Bc. Juraj Macak **/

class Formatter {

    companion object {

        fun currencyFormatter(value: Int): String {
            val str = NumberFormat.getNumberInstance(Locale.GERMANY).format(value)

            return str
        }

        fun timeFormatterInc(value: Double, noAddition: Boolean = false): String {
            var hours = (value / 3600).toInt()
            if (!noAddition) {
                hours += 15
            }
            hours = hours % 24
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
            when(value.trim()) {
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

        fun busStopFormatter(value: String): String {
            when(value) {
                "A_A" -> return "Linka A, Zastávka A"
                "A_B" -> return "Linka A, Zastávka B"
                "A_C" -> return "Linka A, Zastávka C"
                "A_D" -> return "Linka A, Zastávka D"
                "A_E" -> return "Linka A, Zastávka E"
                "A_F" -> return "Linka A, Zastávka F"
                "A_G" -> return "Linka A, Zastávka G"
                "A_H" -> return "Linka A, Zastávka H"
                "A_I" -> return "Linka A, Zastávka I"
                "A_J" -> return "Linka A, Zastávka J"
                "A_K" -> return "Linka A, Zastávka K"
                "A_L" -> return "Linka A, Zastávka L"

                "B_A" -> return "Linka B, Zastávka A"
                "B_B" -> return "Linka B, Zastávka B"
                "B_C" -> return "Linka B, Zastávka C"
                "B_D" -> return "Linka B, Zastávka D"
                "B_E" -> return "Linka B, Zastávka E"
                "B_F" -> return "Linka B, Zastávka F"
                "B_G" -> return "Linka B, Zastávka G"
                "B_H" -> return "Linka B, Zastávka H"
                "B_I" -> return "Linka B, Zastávka I"
                "B_J" -> return "Linka B, Zastávka J"
                "B_K" -> return "Linka B, Zastávka K"

                "C_A" -> return "Linka C, Zastávka A"
                "C_B" -> return "Linka C, Zastávka B"
                "C_C" -> return "Linka C, Zastávka C"
                "C_D" -> return "Linka C, Zastávka D"
                "C_E" -> return "Linka C, Zastávka E"
                "C_F" -> return "Linka C, Zastávka F"
                "C_G" -> return "Linka C, Zastávka G"
                "C_H" -> return "Linka C, Zastávka H"

                "K1" -> return "Zastávka K1"
                "K2" -> return "Zastávka K2"
                "K3" -> return "Zastávka K3"
            }

            throw Exception("No key for bus stop")
        }

    }

}