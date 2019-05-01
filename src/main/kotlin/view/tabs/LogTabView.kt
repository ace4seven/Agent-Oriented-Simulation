package view.tabs

import model.LogCell
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class LogTabView : View("Log") {

    override val root = vbox {
        label("Prehľad udalosti") {
            vboxConstraints {
                marginLeft = 20.0
                marginTop = 20.0
            }
        }
        D.logTableView = tableview {
            vboxConstraints {
                marginLeft = 20.0
                marginRight = 20.0
                marginTop = 10.0
            }

            items = D.controller.logTableViewDataSource
            column("ID", LogCell::id) {
                minWidth = 20.0
            }

            column("Čas výskytu", LogCell::time) {
                minWidth = 150.0
            }

            column("Popis", LogCell::description) {
                minWidth = 500.0
            }
        }
    }
}