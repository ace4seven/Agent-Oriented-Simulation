package helper

import java.io.FileWriter
import java.io.IOException

/** Author: Bc. Juraj Macak **/

data class CSVBusEntry(
        val link: String,
        val type: String,
        val strategy: String,
        val scheduler: String
)

data class CSVResultEntry(
        val averageWaiting: Double,
        val averageNoCome: Double
)

class ExperimentExporter(val fileName: String) {

    var fileWriter: FileWriter? = null
    val delimeter = ','

    val busHeader = "Linka autobusu, Typ, Stratégia vozenia, Čas vyslania"
    val resultHeader = "Priemerný čas čakania, Percent nestihli"

    fun initializeWriter() {
        fileWriter = FileWriter(fileName)
    }

    fun addRow(data: CSVBusEntry) {
        fileWriter?.append("${data.link} ${delimeter} ${data.type} ${delimeter} ${data.strategy} ${delimeter} ${data.scheduler}")
        fileWriter?.append('\n')
    }

    fun addRow(data: CSVResultEntry) {
        fileWriter?.append("${data.averageWaiting} ${delimeter} ${data.averageNoCome}")
        fileWriter?.append('\n')
    }

    fun addBusHeader() {
        try {
            fileWriter?.append(busHeader)
            fileWriter?.append('\n')
        } catch (error: IOException) {
            println(error.message)
        }
    }

    fun addResultHeader() {
        try {
            fileWriter?.append(resultHeader)
            fileWriter?.append('\n')
        } catch (error: IOException) {
            println(error.message)
        }
    }

    fun closeWriter() {
        try {
            fileWriter?.flush()
            fileWriter?.close()
        } catch (e: IOException) {
            println("Flushing/closing error!")
            e.printStackTrace()
        }
    }

}