package view.tabs

import model.LogCell
import model.StatisticCell
import tornadofx.*
import view.support.D

/** Author: Bc. Juraj Macak **/

open class LocalStatisticView : View("Lokálne štatistiky") {

    override val root = hbox {
        vbox {
            label("Prehľad lokálnych štatistík") {
                vboxConstraints {
                    marginLeft = 20.0
                    marginTop = 20.0
                }
            }
            D.localStatistics = tableview {
                vboxConstraints {
                    marginLeft = 20.0
                    marginRight = 20.0
                    marginTop = 10.0
                }

                items = D.controller.localStatisticsDatasource
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
