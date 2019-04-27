package helper

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

enum class BusStop {
    A_A, A_B, A_C, A_D, A_E, A_F, A_G, A_H, A_I, A_J, A_K, A_L,
    B_A, B_B, B_C, B_D, B_E, B_F, B_G, B_H, B_I, B_J,
    C_A, C_B, C_C, C_D, C_E, C_F, C_G,
    A_K1, A_K2, A_K3,
    B_K1, B_K2, B_K3,
    C_K1, C_K2, C_K3, STATION;

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

            A_K1, B_K1, C_K1 -> return 260
            A_K2, B_K2, C_K2 -> return 210
            A_K3, B_K3, C_K3 -> return 220
        }
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
    }

}