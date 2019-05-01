package model

import tornadofx.*

/** Author: Bc. Juraj Macak **/

class PassengerCell {

    var id: Int by property<Int>()
    var doorIn: String by property<String>()
    var doorOut: String by property<String>()
    var stopArrival: String by property<String>()
    var busArrival: String by property<String>()
    var busOut: String by property<String>()

}