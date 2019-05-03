package helper

import java.lang.Exception

/** Author: Bc. Juraj Macak **/


class BusScheduler(val busLink: BusLink) {

    private var currentStop: BusStop? = null

    private var startTime = 0.0
    private var endTime = 0.0

    init {
        when(busLink) {
            BusLink.LINK_A -> currentStop = BusStop.A_A
            BusLink.LINK_B -> currentStop = BusStop.B_A
            BusLink.LINK_C -> currentStop = BusStop.C_A
        }
    }

    fun isFinalDestination(): Boolean {
        when(currentStop) {
            BusStop.STATION -> return true
        }

        return false
    }

    fun getActualStop(): BusStop? {
        return currentStop
    }

    fun getDuration(): Double? {
        return currentStop?.duration()
    }

    fun moveToAnotherStop() {
        when(busLink) {
            BusLink.LINK_A -> progressALink()
            BusLink.LINK_B -> progressBLink()
            BusLink.LINK_C -> progressCLink()
        }
    }

    private fun progressCLink() {
        when(currentStop) {
            BusStop.C_A -> currentStop = BusStop.C_B
            BusStop.C_B -> currentStop = BusStop.C_K1
            BusStop.C_K1 -> currentStop = BusStop.C_C
            BusStop.C_C -> currentStop = BusStop.C_D
            BusStop.C_D -> currentStop = BusStop.C_E
            BusStop.C_E -> currentStop = BusStop.C_F
            BusStop.C_F -> currentStop = BusStop.C_G
            BusStop.C_G -> currentStop = BusStop.STATION
            BusStop.STATION -> currentStop = BusStop.C_A
        }
    }

    private fun progressBLink() {
        when(currentStop) {
            BusStop.B_A -> currentStop = BusStop.B_B
            BusStop.B_B -> currentStop = BusStop.B_C
            BusStop.B_C -> currentStop = BusStop.B_D
            BusStop.B_D -> currentStop = BusStop.B_K2
            BusStop.B_K2 -> currentStop = BusStop.B_E
            BusStop.B_E -> currentStop = BusStop.B_F
            BusStop.B_F -> currentStop = BusStop.B_K3
            BusStop.B_K3 -> currentStop = BusStop.B_G
            BusStop.B_G -> currentStop = BusStop.B_H
            BusStop.B_H -> currentStop = BusStop.B_I
            BusStop.B_I -> currentStop = BusStop.B_J
            BusStop.B_J -> currentStop = BusStop.STATION
            BusStop.STATION -> currentStop = BusStop.B_A
        }
    }

    private fun progressALink() {
        when(currentStop) {
            BusStop.A_A -> currentStop = BusStop.A_B
            BusStop.A_B -> currentStop = BusStop.A_C
            BusStop.A_C -> currentStop = BusStop.A_D
            BusStop.A_D -> currentStop = BusStop.A_K1
            BusStop.A_K1 -> currentStop = BusStop.A_E
            BusStop.A_E -> currentStop = BusStop.A_F
            BusStop.A_F -> currentStop = BusStop.A_G
            BusStop.A_G -> currentStop = BusStop.A_K3
            BusStop.A_K3 -> currentStop = BusStop.A_H
            BusStop.A_H -> currentStop = BusStop.A_I
            BusStop.A_I -> currentStop = BusStop.A_J
            BusStop.A_J -> currentStop = BusStop.A_K
            BusStop.A_K -> currentStop = BusStop.A_L
            BusStop.A_L -> currentStop = BusStop.STATION
            BusStop.STATION -> currentStop = BusStop.A_A
        }
    }

    fun getNextStop(): BusStop? {
        when(currentStop) {
            BusStop.A_A -> return BusStop.A_B
            BusStop.A_B -> return BusStop.A_C
            BusStop.A_C -> return BusStop.A_D
            BusStop.A_D -> return BusStop.A_K1
            BusStop.A_K1 -> return BusStop.A_E
            BusStop.A_E -> return BusStop.A_F
            BusStop.A_F -> return BusStop.A_G
            BusStop.A_G -> return BusStop.A_K3
            BusStop.A_K3 -> return BusStop.A_H
            BusStop.A_H -> return BusStop.A_I
            BusStop.A_I -> return BusStop.A_J
            BusStop.A_J -> return BusStop.A_K
            BusStop.A_K -> return BusStop.A_L
            BusStop.A_L -> return BusStop.STATION

            BusStop.B_A -> return BusStop.B_B
            BusStop.B_B -> return BusStop.B_C
            BusStop.B_C -> return BusStop.B_D
            BusStop.B_D -> return BusStop.B_K2
            BusStop.B_K2 -> return BusStop.B_E
            BusStop.B_E -> return BusStop.B_F
            BusStop.B_F -> return BusStop.B_K3
            BusStop.B_K3 -> return BusStop.B_G
            BusStop.B_G -> return BusStop.B_H
            BusStop.B_H -> return BusStop.B_I
            BusStop.B_I -> return BusStop.B_J
            BusStop.B_J -> return BusStop.STATION

            BusStop.C_A -> return BusStop.C_B
            BusStop.C_B -> return BusStop.C_K1
            BusStop.C_K1 -> return BusStop.C_C
            BusStop.C_C -> return BusStop.C_D
            BusStop.C_D -> return BusStop.C_E
            BusStop.C_E -> return BusStop.C_F
            BusStop.C_F -> return BusStop.C_G
            BusStop.C_G -> return BusStop.STATION

            BusStop.STATION -> {
                when(busLink) {
                    BusLink.LINK_A -> return BusStop.A_A
                    BusLink.LINK_B -> return BusStop.B_A
                    BusLink.LINK_C -> return BusStop.C_A
                }
            }
        }

        throw Exception("Error connecting BUS STOP link in scheduler")
    }

    fun initTransportStats(inTime: Double) {
        startTime = inTime
        endTime = inTime + currentStop!!.duration()
    }

    fun resetTransposrtStats() {
        startTime = 0.0
        endTime = 0.0
    }

    fun prepareToMoveNextStop(inTime: Double) {
        moveToAnotherStop()

//        if (isFinalDestination()) {
//            startTime = inTime
//            endTime = inTime + busLink.backWayDuration()
//        } else {
//            startTime = inTime
//            endTime = inTime + currentStop!!.duration()
//        }
    }

    fun addStartTime(value: Double) {
        startTime = value
    }

    fun addFinishTime(value: Double) {
        endTime = value
    }

    fun getStarTime(): Double {
        return startTime
    }

    fun getEndTime(): Double {
        return endTime
    }

    fun clear() {
        when(busLink) {
            BusLink.LINK_A -> currentStop = BusStop.A_A
            BusLink.LINK_B -> currentStop = BusStop.B_A
            BusLink.LINK_C -> currentStop = BusStop.C_A
        }

        startTime = 0.0
        endTime = 0.0
    }

}