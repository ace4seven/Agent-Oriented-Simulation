package model

import OSPABA.Simulation
import aba.entities.*
import helper.BusLink
import tornadofx.*
import java.lang.Exception

/** Author: Bc. Juraj Macak **/

class BusTableData {

    var id: Int by property<Int>()
    var link: String by property<String>()
    var type: String by property<String>()
    var strategy: String by property<String>()
    var scheduleTime: String by property<String>()

    var rawTime: Double = 0.0

    fun transformToVehicle(sim: Simulation): Vehicle {
        var newType: BusType = BusType.MICROBUS
        var strategy: TravelStrategyType = TravelStrategyType.NO_WAIT
        var link: BusLink = BusLink.LINK_A

        when(this.link.trim()) {
            "Linka A" -> link = BusLink.LINK_A
            "Linka B" -> link = BusLink.LINK_B
            "Linka C" -> link = BusLink.LINK_C
            "A" -> link = BusLink.LINK_A
            "B" -> link = BusLink.LINK_B
            "C" -> link = BusLink.LINK_C
        }

        when(this.strategy) {
            "S čakaním" -> strategy = TravelStrategyType.WAIT
            "Bez čakania" -> strategy = TravelStrategyType.NO_WAIT
        }

        when(this.type.trim()) {
            "Malý autobus" -> return Vehicle(this.id, link, BusType.SMALL, strategy, this.rawTime, sim)
            "Veľký autobus" -> return Vehicle(this.id, link, BusType.BIG, strategy, this.rawTime, sim)
            "Mikrobus" -> return Vehicle(this.id, link, BusType.MICROBUS, strategy, this.rawTime, sim)
            "L" -> return Vehicle(this.id, link, BusType.BIG, strategy, this.rawTime, sim)
            "S" -> return Vehicle(this.id, link, BusType.SMALL, strategy, this.rawTime, sim)
            "M" -> return Vehicle(this.id, link, BusType.MICROBUS, strategy, this.rawTime, sim)
        }

        throw Exception("Error transform vehicle from table")
    }

}
