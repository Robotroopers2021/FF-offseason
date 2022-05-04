package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Turret(val motor : KMotorEx) : DeviceSubsystem() {
    companion object TurretConstants {
        const val turretZeroPosition = 0.0
        const val allianceHomeAngle = 0.0
        const val sharedHomeAngle = 180.0
        const val allianceAngleBlue = 90.0
        const val sharedAngle = 270.0
        const val allianceAngleRed = 270.0
    }

    fun setPower(power: Double) {
        motor.power = power
    }

}

class TurretCommand(private val power: Double, private val turret: Turret) : SequentialGroup(
    InstantCmd({ turret.setPower(power) }, turret)
){
    init {
        addRequirements(turret)
    }

    override fun end() {
        turret.setPower(0.0)
    }
}

