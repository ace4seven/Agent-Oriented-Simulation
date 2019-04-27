package helper

/** Author: Bc. Juraj Macak **/


class BusScheduler(val busLink: BusLink) {

    private var nextStop: BusStop? = null

    init {
        when(busLink) {
            BusLink.LINK_A -> nextStop = BusStop.A_A
            BusLink.LINK_B -> nextStop = BusStop.B_A
            BusLink.LINK_B -> nextStop = BusStop.B_A
        }
    }

    fun getDuration(): Double? {
        return nextStop?.duration()
    }

    fun moveToAnotherStop() {
        when(busLink) {
            BusLink.LINK_A -> progressALink()
            BusLink.LINK_B -> progressBLink()
            BusLink.LINK_B -> progressALink()
        }
    }

    private fun progressCLink() {
        when(nextStop) {
            BusStop.C_A -> nextStop = BusStop.C_B
            BusStop.C_B -> nextStop = BusStop.C_K1
            BusStop.C_K1 -> nextStop = BusStop.C_C
            BusStop.C_C -> nextStop = BusStop.C_D
            BusStop.C_D -> nextStop = BusStop.C_E
            BusStop.C_E -> nextStop = BusStop.C_F
            BusStop.C_F -> nextStop = BusStop.C_G
            BusStop.C_G -> nextStop = BusStop.C_A
        }
    }

    private fun progressBLink() {
        when(nextStop) {
            BusStop.B_A -> nextStop = BusStop.B_B
            BusStop.B_B -> nextStop = BusStop.B_C
            BusStop.B_C -> nextStop = BusStop.B_D
            BusStop.B_D -> nextStop = BusStop.B_K2
            BusStop.B_K2 -> nextStop = BusStop.B_E
            BusStop.B_E -> nextStop = BusStop.B_F
            BusStop.B_F -> nextStop = BusStop.B_K3
            BusStop.B_K3 -> nextStop = BusStop.B_G
            BusStop.B_G -> nextStop = BusStop.B_H
            BusStop.B_H -> nextStop = BusStop.B_I
            BusStop.B_I -> nextStop = BusStop.B_J
            BusStop.B_J -> nextStop = BusStop.B_A
        }
    }

    private fun progressALink() {
        when(nextStop) {
            BusStop.A_A -> nextStop = BusStop.A_B
            BusStop.A_B -> nextStop = BusStop.A_C
            BusStop.A_C -> nextStop = BusStop.A_D
            BusStop.A_D -> nextStop = BusStop.A_K1
            BusStop.A_K1 -> nextStop = BusStop.A_E
            BusStop.A_E -> nextStop = BusStop.A_F
            BusStop.A_F -> nextStop = BusStop.A_G
            BusStop.A_G -> nextStop = BusStop.A_K3
            BusStop.A_K3 -> nextStop = BusStop.A_H
            BusStop.A_H -> nextStop = BusStop.A_I
            BusStop.A_I -> nextStop = BusStop.A_J
            BusStop.A_J -> nextStop = BusStop.A_K
            BusStop.A_K -> nextStop = BusStop.A_L
            BusStop.A_L -> nextStop = BusStop.A_A
        }
    }

}