package view.window

import model.PassengerCell
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class BusDetailView : View("Detail autobusu") {

    override val root = vbox {
        prefWidth = 1500.0
        prefHeight = 1000.0

        vbox {
            label("Prehľad pasažierov v autobuse") {
                vboxConstraints {
                    marginLeft = 20.0
                    marginTop = 20.0
                }
            }
            D.busPassengersTableView = tableview {
                vboxConstraints {
                    marginLeft = 10.0
                    marginRight = 10.0
                    marginTop = 10.0
                }

                items = D.controller.busPassengerDatasource

                column("ID pasažiera", PassengerCell::id) {
                    minWidth = 150.0
                }

                column("Čas čakania", PassengerCell::waitingTime) {
                    minWidth = 150.0
                }

                column("Príchod na zastávku", PassengerCell::stopArrival) {
                    minWidth = 150.0
                }

                column("Nástup do autobusu", PassengerCell::busArrival) {
                    minWidth = 150.0
                }

                column("Výstup z autobusu", PassengerCell::busArrival) {
                    minWidth = 150.0
                }

                column("Dvere nástupu", PassengerCell::doorIn) {
                    minWidth = 150.0
                }

//                column("Dvere výstupu", PassengerCell::doorOut) {
//                    minWidth = 120.0
//                }
            }
        }

    }

}