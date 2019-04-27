package aba.entities

import aba.simulation.BusHockeySimulation
import helper.BusLink

/** Author: Bc. Juraj Macak **/

class MicroBusEntity(link: BusLink, type: BusType, strategy: TravelStrategyType, sim: BusHockeySimulation): Vehicle(link, type, strategy, sim) {

}