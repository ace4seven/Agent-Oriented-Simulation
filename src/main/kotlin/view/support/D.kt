package view.support

import aba.entities.BusStopEntity
import aba.entities.BusType
import aba.entities.TravelStrategyType
import aba.entities.Vehicle
import controller.AppController
import helper.Formatter
import javafx.collections.FXCollections
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import javafx.scene.control.*
import model.*
import tornadofx.*

/** Author: Bc. Juraj Macak **/

class D {

    companion object {

        val controller = AppController()

        var simulationTime: Label by singleAssign()

        val busLinks = FXCollections.observableArrayList("Linka A", "Linka B","Linka C")
        val busType = FXCollections.observableArrayList("Malý autobus", "Veľký autobus")
        val busStrategy = FXCollections.observableArrayList("Bez čakania", "S čakaním")

        val analyzerStrategy = FXCollections.observableArrayList("Bez čakania", "S čakaním")
        val analyzerStrategyMicrobuses = FXCollections.observableArrayList("Bez čakania", "S čakaním")
        val analyzerBusType = FXCollections.observableArrayList("Malý", "Veľký", "Kombinovaný")

        var repeatDelimiterTextField: TextField by singleAssign()
        var repeatDelimiterMicroBusesTextField: TextField by singleAssign()
        var incomeBusMinTextField: TextField by singleAssign()
        var incomeBusMaxTextField: TextField by singleAssign()
        var incomeBusMinMicroBusesTextField: TextField by singleAssign()
        var incomeBusMaxMicroBusesTextField: TextField by singleAssign()

        var numberOfBussesMin: TextField by singleAssign()
        var numberOfBussesMax: TextField by singleAssign()

        var analyzerResultCount: TextField by singleAssign()
        var analyzerResultCountMicrobuses: TextField by singleAssign()
        var analyzeFileNameTextField: TextField by singleAssign()
        var analyzeFileNameMicrobusesTextField: TextField by singleAssign()

        var addMicrobusesCheckBox = CheckBox()

        var logCheckbox = CheckBox()
        var fastModeCheckBox = CheckBox()

        var speedSlider = Slider()
        var intensitySlider = Slider()

        var simulationProgressBar: ProgressBar by singleAssign()

        fun startAnalyzer(completion: () -> Unit) {
            controller.analyzator.addIndexStop(repeatDelimiterTextField.text.toInt())
            controller.analyzator.addScheduleTimeBorder(incomeBusMinTextField.text.toInt(), incomeBusMaxTextField.text.toInt())
            controller.analyzator.addVehiclesCount(numberOfBussesMin.text.toInt(), numberOfBussesMax.text.toInt())
            controller.analyzator.setHasMicrobuses(addMicrobusesCheckBox.isSelected)
            controller.analyzator.addNumberOfResults(analyzerResultCount.text.toInt())
            controller.analyzator.changeFileName(analyzeFileNameTextField.text)

            when(analyzerBusTypeCombobox.selectedItem) {
                "Malý" -> controller.analyzator.setBusType(BusType.SMALL)
                "Veľký" -> controller.analyzator.setBusType(BusType.BIG)
                else -> controller.analyzator.setBusType(null)
            }

            when(analyzerBusStrategyCombobox.selectedItem) {
                "S čakaním" -> controller.analyzator.setTravelStrategy(TravelStrategyType.WAIT)
                else -> controller.analyzator.setTravelStrategy(TravelStrategyType.NO_WAIT)
            }

            controller.analyzator.startExperiments(completion)
        }

        fun startAnalyzeWithMicrobuses() {
            controller.analyzator.addIndexStop(repeatDelimiterMicroBusesTextField.text.toInt())
            controller.analyzator.addScheduleTimeBorder(incomeBusMinMicroBusesTextField.text.toInt(), incomeBusMaxMicroBusesTextField.text.toInt())
            controller.analyzator.addNumberOfResults(analyzerResultCountMicrobuses.text.toInt())
            controller.analyzator.changeFileName(analyzeFileNameMicrobusesTextField.text)

            when(analyzerBusStrategyMicroBusesCombobox.selectedItem) {
                "S čakaním" -> controller.analyzator.setTravelStrategy(TravelStrategyType.WAIT)
                else -> controller.analyzator.setTravelStrategy(TravelStrategyType.NO_WAIT)
            }

            controller.analyzator.startExperimentsMicrobuses()
        }

        // TABLES

        var startButton: Button by singleAssign()
        var pauseButton: Button by singleAssign()
        var stopButton: Button by singleAssign()

        var resetBusTables: Button by singleAssign()
        var startExperiments: Button by singleAssign()

        var busProgressTableView: TableView<BusProgressCell> by singleAssign()

        var averageWaitingChart: LineChart<Number, Number> by singleAssign()
        var averageWaitingChartData by singleAssign<XYChart.Series<Number, Number>>()

        var averageMissHockeyChart: LineChart<Number, Number> by singleAssign()
        var averageMissHockeyChartData by singleAssign<XYChart.Series<Number, Number>>()

        var linkATableView: TableView<LinkCell> by singleAssign()
        var linkBTableView: TableView<LinkCell> by singleAssign()
        var linkCTableView: TableView<LinkCell> by singleAssign()
        var linkKTableView: TableView<LinkCell> by singleAssign()

        var localStatistics: TableView<StatisticCell> by singleAssign()
        var globalStatistics: TableView<StatisticCell> by singleAssign()

        var busPassengersTableView: TableView<PassengerCell> by singleAssign()

        var busLinkATableView: TableView<BusTableData> by singleAssign()
        var busLinkATableViewDatasource= FXCollections.observableArrayList<BusTableData>()

        var busLinkBTableView: TableView<BusTableData> by singleAssign()
        var busLinkBTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        var busLinkCTableView: TableView<BusTableData> by singleAssign()
        var busLinkCTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        var logTableView: TableView<LogCell> by singleAssign()
        var logTableViewDataSource= FXCollections.observableArrayList<BusTableData>()

        // MARK: CoMBOBOX

        var analyzerBusTypeCombobox: ComboBox<String> by singleAssign()
        var analyzerBusStrategyCombobox: ComboBox<String> by singleAssign()
        var analyzerBusStrategyMicroBusesCombobox: ComboBox<String> by singleAssign()
        var busLinkComboBox: ComboBox<String> by singleAssign()
        var busTypeComboBox: ComboBox<String> by singleAssign()
        var busStrategyComboBox: ComboBox<String> by singleAssign()

        // TEXTFIELDS

        var replicationTextField: TextField by singleAssign()
        var timeToDeployTextField: TextField by singleAssign()

        var busData: MutableList<BusTableData>? = null

        // METHODS

        fun loadBusesFromFile() {
            val bData = controller.fileManager.getBusSchedule().map {
                val formattedBus = BusTableData()

                formattedBus.scheduleTime = Formatter.timeFormatterInc(it.scheduleTime.toDouble())
                formattedBus.type = Formatter.convertToText(it.type)
                formattedBus.strategy = Formatter.convertToText(it.strategy)
                formattedBus.id = it.id
                formattedBus.link = Formatter.convertToText(it.link)
                formattedBus.rawTime = it.scheduleTime.toDouble()

                return@map formattedBus
            }

            this.busData = bData.toMutableList()

            busData?.forEach {
                when(it.link) {
                    "Linka A" -> busLinkATableViewDatasource.add(it)
                    "Linka B" -> busLinkBTableViewDataSource.add(it)
                    "Linka C" -> busLinkCTableViewDataSource.add(it)
                }
            }
        }

        fun clearBusTables() {
            busLinkATableViewDatasource.clear()
            busLinkBTableViewDataSource.clear()
            busLinkCTableViewDataSource.clear()

            controller.clearVehicles()
        }

        fun addBuss() {
            var busTableData = BusTableData()

            busTableData.type = busTypeComboBox.value
            busTableData.strategy = busStrategyComboBox.value
            busTableData.rawTime = timeToDeployTextField.text.toDouble()
            busTableData.scheduleTime = Formatter.timeFormatterInc(timeToDeployTextField.text.toDouble())
            busTableData.id = Vehicle.getUniqueID()
            busTableData.link = busLinkComboBox.value

            when (busLinkComboBox.value) {
                "Linka A" -> {
                    busLinkATableViewDatasource.add(busTableData)
                }
                "Linka B" -> {
                    busLinkBTableViewDataSource.add(busTableData)
                }
                "Linka C" -> {
                    busLinkCTableViewDataSource.add(busTableData)
                }
            }

            controller.addVehicle(busTableData)
        }

        fun startSimulationButtonPressed() {
            startButton.isDisable = true
            stopButton.isDisable = false
            pauseButton.isDisable = false

            logCheckbox.isDisable = true
            fastModeCheckBox.isDisable = true

            busData?.forEach {
                controller.addVehicle(it)
            }

            controller.startSimulation()
        }

        fun stopSimulation() {
            startButton.isDisable = false
            stopButton.isDisable = true
            pauseButton.isDisable = true

            controller.progress = 0.0

            logCheckbox.isDisable = false
            fastModeCheckBox.isDisable = false

            busData?.clear()

            controller.averageWaitingChartData.clear()
            controller.averageWaitingChartData.removeAll()
            controller.averageMissHockeyChartData.clear()
            controller.averageMissHockeyChartData.removeAll()
            controller.globalStatisticsDatasource.clear()
            controller.globalStatisticsDatasource.removeAll()
            controller.localStatisticsDatasource.clear()
            controller.localStatisticsDatasource.removeAll()

            controller.stopSimulation()
        }

        fun pauseSimulation() {
            startButton.isDisable = true
            stopButton.isDisable = false
            pauseButton.isDisable = false

            controller.pauseSimulation()

            if (controller.simulationCore.isPaused) {
                pauseButton.text = "Prestávka"
            } else {
                pauseButton.text = "Pokračovať"
            }
        }

        fun exportCurrentConfigurationCSV() {
            controller.currentConfigurationExporter.initializeWriter("current_configuration.csv")

            busLinkATableViewDatasource.forEach {
               controller.currentConfigurationExporter.addRow(it)
            }

            busLinkBTableViewDataSource.forEach {
                controller.currentConfigurationExporter.addRow(it)
            }

            busLinkCTableViewDataSource.forEach {
                controller.currentConfigurationExporter.addRow(it)
            }

            controller.currentConfigurationExporter.closeWriter()
        }

        fun alert(type: Alert.AlertType,
                  header: String,
                  content: String,
                  vararg buttons: ButtonType,
                  actionFn: (Alert.(ButtonType) -> Unit)? = null): Alert {

            val alert = Alert(type, content, *buttons)
            alert.headerText = header
            val buttonClicked = alert.showAndWait()
            buttonClicked.ifPresent { actionFn?.invoke(alert, buttonClicked.get()) }
            return alert
        }

    }

}