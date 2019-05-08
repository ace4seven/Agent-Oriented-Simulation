package helper

import aba.entities.Vehicle
import model.BusTableData
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/** Author: Bc. Juraj Macak **/

class FileManager {

    private var fileReader: BufferedReader? = null
    private val busFileName = "bus_schedule.csv"

    private val headerLink = 0
    private val headerType = 1
    private val headerStrategy = 2
    private val headerDeploy = 3

    private val buses = mutableListOf<BusTableData>()

    fun getBusSchedule(): List<BusTableData> {
        buses.clear()

        readBuses()

        return buses
    }

    fun resetRead() {
        buses.clear()
    }

    private fun readBuses() {
        try {
            var line: String?

            Vehicle.clear()

            fileReader = BufferedReader(FileReader(busFileName))
            line = fileReader?.readLine()
            while (line != null) {
                val tokens = line.split(",")
                if (tokens.size > 0) {
                    val busData = BusTableData()

                    busData.id = Vehicle.getUniqueID()
                    busData.link = tokens[headerLink]
                    busData.type = tokens[headerType]
                    busData.strategy = tokens[headerStrategy]
                    busData.scheduleTime = tokens[headerDeploy]

                    buses.add(busData)
                }

                line = fileReader?.readLine()
            }
        } catch (e: Exception) {
            println("Reading CSV Error!")
            e.printStackTrace()
        } finally {
            try {
                fileReader!!.close()
            } catch (e: IOException) {
                println("Closing fileReader Error!")
                e.printStackTrace()
            }
        }
    }


}