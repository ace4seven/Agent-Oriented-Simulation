package model

import tornadofx.*

/** Author: Bc. Juraj Macak **/

class BusProgressCell {

    var id: Int by property<Int>()
    var type: String by property<String>()
    var strategy: String by property<String>()
    var link: String by property<String>()
    var currentStop: String by property<String>()
    var nextStop: String by property<String>()
    var progress: String by property<String>()
    var activity: String by property<String>()
    var freeCapacity: String by property<String>()
    var numbOfTravelers: String by property<String>()

}