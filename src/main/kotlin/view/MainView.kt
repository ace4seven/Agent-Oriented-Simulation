package view

import app.Styles
import javafx.scene.control.TabPane
import tornadofx.*
import view.tabs.SimulationTabView

class MainView : CoreView("Agentovo orientovaná simulácia") {

    override val root = tabpane {
        prefWidth = 1500.0
        minWidth = 1500.0
        prefHeight = 1000.0

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab(SimulationTabView::class)
    }

}