package view.tabs

import javafx.scene.control.TabPane
import view.support.CoreView
import tornadofx.*

/** Author: Bc. Juraj Macak **/

open class Tab2Subview : CoreView("Priebeh simulácie") {

    override val root = tabpane {
        prefWidth = 1500.0
        prefHeight = 1000.0

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        val overView = OverviewSimulationTabView()
        overView.busData = busData
        tab(overView)
        tab(LogTabView::class)
    }

}