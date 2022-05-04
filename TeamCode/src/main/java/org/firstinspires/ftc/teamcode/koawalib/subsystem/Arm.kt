package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.util.Encoder

class Arm(val motor : KMotorEx) : DeviceSubsystem() {
    companion object ArmConstants {
        const val topPosition = 10.0
        const val midPosition = -15.0
        const val bottomPosition = -30.0
        const val sharedPosition = -30.0
        const val sharedLow = 25.0
        const val sharedHigh = 35.0
        const val homePosition = -35.0
        const val armIntakeExt = -32.0
        const val armIntakePos = -40.0
    }

    fun setPower(power: Double) {
        motor.power = power
    }

}

class ArmCommand(private val power: Double, private val arm: Arm) : SequentialGroup(
 InstantCmd({ arm.setPower(power) }, arm)
){
    init {
        addRequirements(arm)
    }

    override fun end() {
        arm.setPower(0.0)
    }
}
