package helper

import java.util.*

/** Author: Bc. Juraj Macak **/

class Constants {

    companion object {
        val simulationTime = 10800.0

        val availableBusStops = arrayOf(
                BusStop.A_A, BusStop.A_B, BusStop.A_C, BusStop.A_D, BusStop.A_E,
                BusStop.A_F, BusStop.A_G, BusStop.A_H, BusStop.A_I, BusStop.A_J, BusStop.A_K, BusStop.A_L,
                BusStop.B_A, BusStop.B_B, BusStop.B_C, BusStop.B_D, BusStop.B_E, BusStop.B_F, BusStop.B_G, BusStop.B_H, BusStop.B_I, BusStop.B_J,
                BusStop.C_A, BusStop.C_B, BusStop.C_C, BusStop.C_D, BusStop.C_E, BusStop.C_F, BusStop.C_G,
                BusStop.K1, BusStop.K2, BusStop.K3)

        val randomSeader = Random()
    }

}