package model

import helper.Formatter
import tornadofx.*
import java.util.*

/** Author: Bc. Juraj Macak **/

class LogCell {

    var id: Int by property<Int>()
    var time: String by property<String>()
    var description: String by property<String>()

    companion object {
        var index = 1

        fun inc() {
            index += 1
        }
    }

}

class LogEntry(val id: Int, val time: Double, val desc: String) {

    fun logCell(): LogCell {
        val logCell = LogCell()
        logCell.id = this.id
        logCell.time = Formatter.timeFormatterInc(this.time)
        logCell.description = this.desc

        return logCell
    }

}