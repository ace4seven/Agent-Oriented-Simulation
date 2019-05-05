package model

import OSPStat.Stat
import tornadofx.*

/** Author: Bc. Juraj Macak **/

class StatisticCell {

    var name: String by property<String>()
    var desc: String by property<String>()

    companion object {

        fun makeCell(name: String, desc: String): StatisticCell {
            val cell = StatisticCell()

            cell.name = name
            cell.desc = desc

            return cell
        }

        fun makeCellConfidental(name: String, value: Stat, isPercent: Boolean = false): StatisticCell {
            val cell = StatisticCell()

            cell.name = name
            if (isPercent) {
                cell.desc = "${value.mean() * 100.0} % ± (${(value.mean() - value.confidenceInterval_90()[0]) * 100.0}})"
            } else {
                cell.desc = "${value.mean()} ± (${value.mean() - value.confidenceInterval_90()[0]}})"
            }

            return cell
        }

    }

}