package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class ScuffedTurret (private val motor: KMotor) : DeviceSubsystem() {
    fun setPower(power: Double) {
        motor.power = power
    }
}

class TurretMove(private val power: Double, private val turret: ScuffedTurret) : InstantCmd({ turret.setPower(power) }, turret )
