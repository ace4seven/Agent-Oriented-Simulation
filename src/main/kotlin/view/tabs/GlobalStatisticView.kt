package view.tabs

import javafx.scene.chart.NumberAxis
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
                    minWidth = 370.0
                }

                column("Hodnota štatistiky", StatisticCell::desc) {
                    minWidth = 400.0
                }

                minHeight = 650.0
                maxHeight = 650.0
            }
        }
        vbox {
            D.averageWaitingChart = linechart("Graf - priemerné čakanie na zastávkach", NumberAxis(), NumberAxis()) {
                createSymbols = false
                isLegendVisible = false
                maxHeight = 350.0
                minWidth = 500.0
                animated = false
                D.averageWaitingChartData = series("", D.controller.averageWaitingChartData)

                vboxConstraints {
                    marginLeft = 20.0
                    marginRight = 20.0
                    marginTop = 10.0
                }

                with(yAxis as NumberAxis) {
                    label = "Priemerné čakanie [min]"
                    isForceZeroInRange = false
                    isAutoRanging = true
                }
                with(xAxis as NumberAxis) {
                    xAxis.label = "Číslo replikácie"
                    isForceZeroInRange = false
                    isAutoRanging = true
                }
            }

            D.averageMissHockeyChart = linechart("Graf - percento meškania na zápas", NumberAxis(), NumberAxis()) {
                createSymbols = false
                isLegendVisible = false
                maxHeight = 350.0
                minWidth = 500.0
                animated = false
                D.averageMissHockeyChartData = series("", D.controller.averageMissHockeyChartData)

                vboxConstraints {
                    marginLeft = 20.0
                    marginRight = 20.0
                    marginTop = 10.0
                }

                with(yAxis as NumberAxis) {
                    label = "Priemerné čakanie [min]"
                    isForceZeroInRange = false
                    isAutoRanging = true
                }
                with(xAxis as NumberAxis) {
                    xAxis.label = "Číslo replikácie"
                    isForceZeroInRange = false
                    isAutoRanging = true
                }
            }
        }
    }

}