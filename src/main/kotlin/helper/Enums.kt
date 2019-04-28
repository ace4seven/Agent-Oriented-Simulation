package helper

import java.lang.Exception

/** Author: Bc. Juraj Macak **/

enum class BusLink {
    LINK_A, LINK_B, LINK_C;

    fun backWayDuration(): Double {
        when(this) {
            LINK_A -> return 1500.0
            LINK_B -> return 600.0
            LINK_C -> return 1800.0
        }
    }
}

data class GeneratorData(val start: Double, val stop: Double, val lambda: Double) {

    fun startGenerateTime(): Double {
        return Constants.simulationTime - start
    }

    fun stopGenerateTime(): Double {
        return Constants.simulationTime - stop
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
            A_A -> { return GeneratorData(6780.0, 2880.0, 31.70731707) }
            A_B -> { return GeneratorData(6588.0, 2688.0, 42.39130435) }
            A_C -> { return GeneratorData(6450.0, 2550.0, 16.18257261) }
            A_D -> { return GeneratorData(6324.0, 2424.0, 31.70731707) }
            A_E -> { return GeneratorData(5928.0, 2028.0, 18.13953488) }
            A_F -> { return GeneratorData(5754.0, 1854.0, 15.91836735) }
            A_G -> { return GeneratorData(5550.0, 1650.0, 28.46715328) }
            A_H -> { return GeneratorData(5202.0, 1302.0, 29.54545455) }
            A_I -> { return GeneratorData(5106.0, 1206.0, 23.7804878) }
            A_J -> { return GeneratorData(4830.0, 930.0, 31.4516129) }
            A_K -> { return GeneratorData(4626.0, 726.0, 18.30985915) }
            A_L -> { return GeneratorData(4554.0, 654.0, 21.08108108) }

            B_A -> { return GeneratorData(6462.0, 2562.0, 49.36708861) }
            B_B -> { return GeneratorData(6390.0, 2490.0, 56.52173913) }
            B_C -> { return GeneratorData(6252.0, 2352.0, 90.69767442) }
            B_D -> { return GeneratorData(6060.0, 2160.0, 30.70866142) }
            B_E -> { return GeneratorData(5730.0, 1830.0, 130.0) }
            B_F -> { return GeneratorData(5568.0, 1668.0, 56.52173913) }
            B_G -> { return GeneratorData(5028.0, 1128.0, 24.07407407) }
            B_H -> { return GeneratorData(4770.0, 870.0, 43.33333333) }
            B_I -> { return GeneratorData(4740.0, 840.0, 26.35135135) }
            B_J -> { return GeneratorData(4578.0, 678.0, 22.80701754) }

            C_A -> { return GeneratorData(6786.0, 2886.0, 16.25) }
            C_B -> { return GeneratorData(6750.0, 2850.0, 12.58064516) }
            C_C -> { return GeneratorData(6006.0, 2106.0, 29.77099237) }
            C_D -> { return GeneratorData(5868.0, 1968.0, 20.52631579) }
            C_E -> { return GeneratorData(5442.0, 1542.0, 29.54545455) }
            C_F -> { return GeneratorData(5154.0, 1254.0, 30.46875) }
            C_G -> { return GeneratorData(4932.0, 1032.0, 55.71428571) }

            K1 -> { return GeneratorData(6612.0, 2352.0, 16.38461538) }
            K2 -> { return GeneratorData(6366.0, 1902.0, 21.25714286) }
            K3 -> { return GeneratorData(5442.0, 1488.0, 17.97272727) }
        }

        throw Exception("Chain enum bad calling")
    }

}