package view.tabs

import model.StatisticCell
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class GlobalStatisticView : View("Globálne štatistiky") {

    override val root = hbox {
        vbox {
            label("Prehľad globálnych štatistík") {
                vboxConstraints {
                    marginLeft = 20.0
                    marginTop = 20.0
                }
            }
            D.globalStatistics = tableview {
                vboxConstraints {
                    marginLeft = 20.0
                    marginRight = 20.0
                    marginTop = 10.0
                }

                items = D.controller.globalStatisticsDatasource
                column("Názov štatistiky", StatisticCell::name) {
                    minWidth = 200.0
                }

                column("Hodnota štatistiky", StatisticCell::desc) {
                    minWidth = 500.0
                }
            }
        }
    }

}