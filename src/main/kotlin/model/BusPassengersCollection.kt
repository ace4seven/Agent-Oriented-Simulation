package model

import aba.entities.PassengerEntity
import javafx.collections.FXCollections

/** Author: Bc. Juraj Macak **/

class BusPassengersCollection {
    var busPassengers = mutableListOf<PassengerEntity>()
}

class StopPassengersCollection {
    var stopPassengers = mutableListOf<PassengerEntity>()
}