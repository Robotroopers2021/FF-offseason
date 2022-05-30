package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Door (private val servo : KServo) : DeviceSubsystem() {

    companion object DoorConstants {
        const val zeroPosition = 0.0212
        const val openPosition = 0.175
    }

    fun zero() {
        servo.position = zeroPosition
    }

    fun open() {
        servo.position = openPosition
    }
}

class DoorOpen(door : Door) : InstantCmd(door::open, door)
class DoorZero(door : Door) : InstantCmd(door::zero, door)