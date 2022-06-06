package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Door (private val servo : KServo) : DeviceSubsystem() {

    companion object DoorConstants {
        const val intakePosition = 0.5
        const val openPosition = 0.68
        const val lockPosition = 0.45
    }

    fun intake() {
        servo.position = intakePosition
    }

    fun open() {
        servo.position = openPosition
    }

    fun lock() {
        servo.position = lockPosition
    }
}

class DoorOpen(door : Door) : InstantCmd(door::open, door)
class DoorIntake(door : Door) : InstantCmd(door::intake, door)
class DoorLock(door : Door) : InstantCmd(door::lock, door)