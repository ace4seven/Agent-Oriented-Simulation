package helper

import java.lang.Exception
import java.util.*

/** Author: Bc. Juraj Macak **/

enum class BusLink {
    LINK_A, LINK_B, LINK_C;

    companion object {
        val random = Random()

        fun generateRandom(): BusLink {
            var rand = random.nextDouble()

            if (rand < 0.33) {
                return LINK_A
            } else if (rand <= 0.66) {
                return LINK_B
            }

            return LINK_C
        }
    }

    fun backWayDuration(): Double {
        when(this) {
            LINK_A -> return 1500.0
            LINK_B -> return 600.0
            LINK_C -> return 1800.0
        }
    }

    fun formattedName(isSample: Boolean = false): String {
        if (isSample) {
            when(this) {
                LINK_A -> return "A"
                LINK_B -> return "B"
                LINK_C -> return "C"
            }
        } else {
            when(this) {
                LINK_A -> return "Linka A"
                LINK_B -> return "Linka B"
                LINK_C -> return "Linka C"
            }
        }
    }

}

data class GeneratorData(val start: Double, val stop: Double, val lambda: Double) {

    fun startGenerateTime(): Double {
        return start
    }

    fun stopGenerateTime(): Double {
        return stop
    }

}

enum class BusStop {
    A_A, A_B, A_C, A_D, A_E, A_F, A_G, A_H, A_I, A_J, A_K, A_L,
    B_A, B_B, B_C, B_D, B_E, B_F, B_G, B_H, B_I, B_J,
    C_A, C_B, C_C, C_D, C_E, C_F, C_G,
    A_K1, A_K2, A_K3,
    B_K1, B_K2, B_K3,
    C_K1, C_K2, C_K3, STATION,
    K1, K2, K3;

    fun getLink(): BusLink? {
        when(this) {
            A_A, A_B, A_C, A_D, A_E, A_F, A_G, A_H, A_I, A_J, A_K, A_L -> return BusLink.LINK_A
            B_A, B_B, B_C, B_D, B_E, B_F, B_G, B_H, B_I, B_J -> return BusLink.LINK_B
            C_A, C_B, C_C, C_D, C_E, C_F, C_G -> return BusLink.LINK_C
            A_K1, A_K2, A_K3, B_K1, B_K2, B_K3,C_K1, C_K2, C_K3, STATION, K1, K2, K3 -> return null
        }
    }

    fun capacity(): Int {
        when(this) {
            A_A -> return 123
            A_B -> return 92
            A_C -> return 241
            A_D -> return 123
            A_E -> return 215
            A_F -> return 245
            A_G -> return 137
            A_H -> return 132
            A_I -> return 164
            A_J -> return 124
            A_K -> return 213
            A_L -> return 185

            B_A -> return 79
            B_B -> return 69
            B_C -> return 43
            B_D -> return 127
            B_E -> return 30
            B_F -> return 69
            B_G -> return 162
            B_H -> return 90
            B_I -> return 148
            B_J -> return 171

            C_A -> return 240
            C_B -> return 310
            C_C -> return 131
            C_D -> return 190
            C_E -> return 132
            C_F -> return 128
            C_G -> return 70
            STATION -> return 0

            A_K1, B_K1, C_K1, K1 -> return 260
            A_K2, B_K2, C_K2, K2 -> return 210
            A_K3, B_K3, C_K3, K3 -> return 220
        }

        throw Exception("Bus stop enum failed to chaining")
    }

    fun duration(): Double {
        when(this) {
            A_A -> return 192.0
            A_B -> return 138.0
            A_C -> return 126.0
            A_D -> return 72.0
            A_E -> return 174.0
            A_F -> return 204.0
            A_G -> return 108.0
            A_H -> return 96.0
            A_I -> return 276.0
            A_J -> return 204.0
            A_K -> return 72.0
            A_L -> return 54.0

            B_A -> return 72.0
            B_B -> return 138.0
            B_C -> return 192.0
            B_D -> return 258.0
            B_E -> return 162.0
            B_F -> return 180.0
            B_G -> return 258.0
            B_H -> return 30.0
            B_I -> return 162.0
            B_J -> return 78.0

            C_A -> return 36.0
            C_B -> return 138.0
            C_C -> return 138.0
            C_D -> return 426.0
            C_E -> return 288.0
            C_F -> return 222.0
            C_G -> return 432.0

            A_K1 -> return 324.0
            A_K2 -> return 0.0
            A_K3 -> return 240.0

            B_K1 -> return 0.0
            B_K2 -> return 72.0
            B_K3 -> return 360.0

            C_K1 -> return 246.0
            C_K2 -> return 360.0
            C_K3 -> return 0.0

            STATION -> return 0.0
        }

        throw Exception("Bus stop enum failed to chaining")
    }

    fun generateInterval(): GeneratorData {
        when(this) {

            A_A -> { return GeneratorData(6.0, 3906.0, 31.70731707) }
            A_B -> { return GeneratorData(198.0, 4098.0, 42.39130435) }
            A_C -> { return GeneratorData(336.0, 4236.0, 16.18257261) }
            A_D -> { return GeneratorData(462.0, 4362.0, 31.70731707) }
            A_E -> { return GeneratorData(858.0, 4434.0, 18.13953488) }
            A_F -> { return GeneratorData(1032.0, 4758.0, 15.91836735) }
            A_G -> { return GeneratorData(1236.0, 4932.0, 28.46715328) }
            A_H -> { return GeneratorData(1584.0, 5136.0, 29.54545455) }
            A_I -> { return GeneratorData(1680.0, 5244.0, 23.7804878) }
            A_J -> { return GeneratorData(1956.0, 5484.0, 31.4516129) }
            A_K -> { return GeneratorData(2160.0, 5580.0, 18.30985915) }
            A_L -> { return GeneratorData(2232.0, 6060.0, 21.08108108) }



            B_A -> { return GeneratorData(324.0, 4224.0, 49.36708861) }
            B_B -> { return GeneratorData(396.0, 4296.0, 56.52173913) }
            B_C -> { return GeneratorData(534.0, 4434.0, 90.69767442) }
            B_D -> { return GeneratorData(726.0, 4626.0, 30.70866142) }
            B_E -> { return GeneratorData(1056.0, 4956.0, 130.0) }
            B_F -> { return GeneratorData(1218.0, 5118.0, 56.52173913) }
            B_G -> { return GeneratorData(1758.0, 5658.0, 24.07407407) }
            B_H -> { return GeneratorData(2016.0, 5916.0, 43.33333333) }
            B_I -> { return GeneratorData(2046.0, 5946.0, 26.35135135) }
            B_J -> { return GeneratorData(2208.0, 6108.0, 22.80701754) }


            C_A -> { return GeneratorData(0.0, 3900.0, 16.25) }
            C_B -> { return GeneratorData(36.0, 3936.0, 12.58064516) }
            C_C -> { return GeneratorData(780.0, 4680.0, 29.77099237) }
            C_D -> { return GeneratorData(918.0, 4818.0, 20.52631579) }
            C_E -> { return GeneratorData(1344.0, 5244.0, 29.54545455) }
            C_F -> { return GeneratorData(1632.0, 5532.0, 30.46875) }
            C_G -> { return GeneratorData(1854.0, 5754.0, 55.71428571) }


            K1 -> { return GeneratorData(174.0, 4434.0, 16.38461538) }
            K2 -> { return GeneratorData(420.0, 4884.0, 21.25714286) }
            K3 -> { return GeneratorData(1344.0, 5298.0, 17.97272727) }
        }

        throw Exception("Chain enum bad calling")
    }

    fun getConcreteStop(): BusStop {
        when (this) {
            A_K1, B_K1, C_K1, K1 -> return K1
            A_K2, B_K2, C_K2, K2 -> return K2
            A_K3, B_K3, C_K3, K3 -> return K3
        }

        return this
    }

    fun formattedStop(): String {
        when(this) {
            A_A, B_A, C_A -> return "A"
            A_B, B_B, C_B -> return "B"
            A_C, B_C, C_C -> return "C"
            A_D, B_D, C_D -> return "D"
            A_E, B_E, C_E -> return "E"
            A_F, B_F, C_F -> return "F"
            A_G, B_G, C_G -> return "G"
            A_H, B_H -> return "H"
            A_I, B_I -> return "I"
            A_J, B_J -> return "J"
            A_K -> return "K"
            A_L -> return "L"
            A_K1, B_K1, C_K1, K1 -> return "K1"
            A_K2, B_K2, C_K2, K2 -> return "K2"
            A_K3, B_K3, C_K3, K3 -> return "K3"
            STATION -> return "Štadión"
        }
    }

}