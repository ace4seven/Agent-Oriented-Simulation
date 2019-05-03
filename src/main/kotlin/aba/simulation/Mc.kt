package aba.simulation

import OSPABA.*

class Mc : IdList() {
    companion object {
        //meta! userInfo="Generated code: do not modify", tag="begin"
        val busMoveStart = 1011
        val travelingProcess = 1013
        val travellerExit = 1020
        val travelerArrival = 1015
        val prepareForStart = 1018
        val waitForBus = 1007
        val busArrival = 1009
        val initVehicles = 1004
        val finishInitVehicle = 1005
        val finishTravelStop = 1006
        var passengerOutFromBusFinish = 1101
        var finishBusReturn = 1102
        var initPassengers = 1103
        var newPassenger = 1104
        var passengerFinishIncome = 1105
        var busFinishWaiting = 1106
        var passengerFinishIncomeWaitingBus = 1107

        // MARK: - Contlin correction
        val start = 2147483647
        val finish = 2147483646
        val breakCA = 2147483645
        val goal = 2147483644
        val done = 2147483643
        val transfer = 2147483642
        val cancel = 2147483641
        val handoverDA = 2147483640
        val entrustDA = 2147483639
        val returnDA = 2147483638
        val undefined = 2147483637
        internal val stopSim = 2147483636
        internal val pauseSim = 2147483635
        internal val stopReplication = 2147483634
        internal val startSlowdown = 2147483633
        internal val slowdownAgentId = 2147483647
        internal val slowdownManagerId = 2147483646
        internal val slowdownProcessId = 2147483645
        val absorbtion = -1
    }
    //meta! tag="end"

    // 1..1000 range reserved for user
}
