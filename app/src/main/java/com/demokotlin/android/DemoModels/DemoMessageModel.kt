package com.demokotlin.android

class DemoMessageModel {
    var identifier: Long = 0
    var message: String = ""
    var type: Boolean = false // false: status, true: message
    var direction: Boolean = true // true: outgoing, false: incoming

    constructor(identifier: Long = 0, message: String = "", type: Boolean = false,
                direction: Boolean = true) {
        this.identifier = identifier
        this.message = message
        this.type = type
        this.direction = direction
    }
}
