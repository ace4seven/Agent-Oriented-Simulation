package aba.entities

import aba.simulation.BusHockeySimulation
import helper.BusLink

/** Author: Bc. Juraj Macak **/

class BusEntity(id: Int,
                link: BusLink,
                type: BusType,
                strategy: TravelStrategyType,
                deployTime: Double,
                sim: BusHockeySimulation): Vehicle(id, link, type, strategy, deployTime, sim) {

}