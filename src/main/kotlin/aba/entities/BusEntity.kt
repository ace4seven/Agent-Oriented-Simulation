package aba.entities

import aba.simulation.BusHockeySimulation
import helper.BusLink

/** Author: Bc. Juraj Macak **/

class BusEntity(id: Int, link: BusLink, type: BusType, strategy: TravelStrategyType, sim: BusHockeySimulation): Vehicle(id, link, type, strategy, sim) {

}