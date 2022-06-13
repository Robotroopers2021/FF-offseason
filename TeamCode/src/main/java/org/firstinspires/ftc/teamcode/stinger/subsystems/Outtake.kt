package org.firstinspires.ftc.teamcode.stinger.subsystems

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.perseus.subsystem.Door

class Outtake(private val servo : KServo) : DeviceSubsystem() {

    companion object OuttakeConstants {
        const val openPosition = 0.90
        const val lockPosition = 0.78
        const val outtakePosition = 0.60
    }

    fun open() {
        servo.position = openPosition
    }

    fun lock() {
        servo.position = lockPosition
    }

    fun outtake() {
        servo.position = outtakePosition
    }
}

class OpenCmd(outtake : Outtake) : InstantCmd(outtake::open, outtake)
class OuttakeCmd(outtake : Outtake) : InstantCmd(outtake::outtake, outtake)
class LockCmd(outtake : Outtake) : InstantCmd(outtake::lock, outtake)