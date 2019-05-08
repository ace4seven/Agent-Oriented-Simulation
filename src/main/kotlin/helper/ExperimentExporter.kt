package helper

import model.BusTableData
import model.StatisticCell
import java.io.File
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
        val averageNoCome: Double,
        val averageIncomeMicrobus: Double,
        val busCostExpenses: Int
)

class ExperimentExporter {

    var fileWriter: FileWriter? = null
    val delimeter = ','

    val busHeader = "Linka autobusu, Typ, Stratégia vozenia, Čas vyslania"
    val resultHeader = "Priemerný čas čakania, Percent nestihli, Zisk z microbusov, Náklady spoločnosti"
    val blockSeparator = "\n \n \n"

    fun initializeWriter(fileName: String) {
        fileWriter = FileWriter(fileName)
    }

    fun addRow(data: BusTableData) {
        fileWriter?.append("${data.link} ${delimeter} ${data.type} ${delimeter} ${data.strategy} ${delimeter} ${data.scheduleTime}")
        fileWriter?.append('\n')
    }

    fun addRow(data: CSVBusEntry) {
        fileWriter?.append("${data.link} ${delimeter} ${data.type} ${delimeter} ${data.strategy} ${delimeter} ${data.scheduler}")
        fileWriter?.append('\n')
    }

    fun addRow(data: CSVResultEntry) {
        fileWriter?.append("${data.averageWaiting} ${delimeter} ${data.averageNoCome} ${delimeter} ${data.averageIncomeMicrobus} ${delimeter} ${data.busCostExpenses}")
        fileWriter?.append('\n')
    }

    fun addRow(data: StatisticCell) {
        fileWriter?.append("${data.name} ${delimeter} ${data.desc}")
        fileWriter?.append('\n')
    }

    fun addBlockLine() {
        fileWriter?.append(blockSeparator)
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