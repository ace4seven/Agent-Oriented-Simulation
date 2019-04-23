package view

import javafx.scene.control.TabPane
import tornadofx.*
import view.support.CoreView
import view.tabs.Tab1Subview

class MainView : CoreView("Agentovo orientovaná simulácia") {

    override val root = tabpane {
        prefWidth = 1500.0
        minWidth = 1500.0
        prefHeight = 1000.0

        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

        tab(Tab1Subview::class)
    }

}