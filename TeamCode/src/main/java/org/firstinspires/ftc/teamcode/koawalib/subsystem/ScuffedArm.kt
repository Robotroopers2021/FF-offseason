package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class ScuffedArm (private val motor: KMotor) : DeviceSubsystem() {
    fun setPower(power: Double) {
        motor.power = power
    }
}

class ArmMove(private val power: Double, private val arm: ScuffedArm) : InstantCommand({ arm.setPower(power) }, arm )
