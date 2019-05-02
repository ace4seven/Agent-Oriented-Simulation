package view.tabs

import javafx.scene.control.TabPane
import tornadofx.*

/** Author: Bc. Juraj Macak **/

open class Tab2Subview : View("Priebeh simulácie") {

    override val root = tabpane {
        prefWidth = 1500.0
        prefHeight = 1000.0

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab(OverviewSimulationTabView::class)
        tab(LocalStatisticView::class)
        tab(LogTabView::class)
    }

}