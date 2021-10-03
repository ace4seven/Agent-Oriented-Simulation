package view.window

import model.PassengerCell
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class BusStopDetail : View("Detail autobusovej zastávky") {

    override val root = vbox {
        prefWidth = 1500.0
        prefHeight = 1000.0

        vbox {
            label("Prehľad čalateľov na zastávke") {
                vboxConstraints {
                    marginLeft = 20.0
                    marginTop = 20.0
                }
            }
            tableview<PassengerCell> {
                vboxConstraints {
                    marginLeft = 10.0
                    marginRight = 10.0
                    marginTop = 10.0
                }

                items = D.controller.stopPassengerDataSource

                column("ID pasažiera", PassengerCell::id) {
                    minWidth = 150.0
                }

                column("Príchod na zastávku", PassengerCell::stopArrival) {
                    minWidth = 150.0
                }

//                column("Výstup z autobusu", PassengerCell::busArrival) {
//                    minWidth = 150.0
//                }

//                column("Dvere výstupu", PassengerCell::doorOut) {
//                    minWidth = 120.0
//                }
            }
        }

    }

}