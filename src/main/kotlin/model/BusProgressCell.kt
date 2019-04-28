package model

import tornadofx.*

/** Author: Bc. Juraj Macak **/

class BusProgressCell {

    var id: Int by property<Int>()
    var link: String by property<String>()
    var currentStop: String by property<String>()
    var activity: String by property<String>()
    var freeCapacity: String by property<String>()

}