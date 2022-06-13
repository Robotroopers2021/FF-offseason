package org.firstinspires.ftc.teamcode.perseus.subsystem

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class ScuffedArm (private val motor: KMotor) : DeviceSubsystem() {
    fun setPower(power: Double) {
        motor.power = power
    }
}

class ArmMove(private val power: Double, private val arm: ScuffedArm) : InstantCmd({ arm.setPower(power) }, arm )
