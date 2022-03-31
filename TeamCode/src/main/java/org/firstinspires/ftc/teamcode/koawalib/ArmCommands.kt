package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.command.commands.CommandBase

object ArmCommands {
    open class ArmCommand(private val arm: Arm, private val angle: Double) : CommandBase() {
        override fun execute() {
            arm.setArmAngle(angle)
        }

        override val isFinished: Boolean
            get() = arm.isAtTarget

        init {
            addRequirements(arm)
        }
    }

    class BottomPos(arm: Arm) : ArmCommand(arm, Arm.bottomPosition)
    class MidPos(arm: Arm) : ArmCommand(arm, Arm.midPosition)
    class TopPos(arm: Arm) : ArmCommand(arm, Arm.topPosition)
    class SharedPos(arm: Arm) : ArmCommand(arm, Arm.sharedPosition)

}