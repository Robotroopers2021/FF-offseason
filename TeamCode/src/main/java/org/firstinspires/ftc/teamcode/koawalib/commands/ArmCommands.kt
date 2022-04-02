package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.CommandBase
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm

object ArmCommands {
    open class ArmCommand(private val arm: Arm, private val angle: Double) : CommandBase() {
        override fun execute() {
            arm.periodic()
        }

        override val isFinished: Boolean
            get() = arm.isAtTarget


        override fun initialize() {
            addRequirements(arm)
            arm.setPIDTarget(angle)
        }
    }

    class BottomPos(arm: Arm) : ArmCommand(arm, Arm.bottomPosition)
    class MidPos(arm: Arm) : ArmCommand(arm, Arm.midPosition)
    class TopPos(arm: Arm) : ArmCommand(arm, Arm.topPosition)
    class SharedPos(arm: Arm) : ArmCommand(arm, Arm.sharedPosition)
    class StartPos(arm: Arm) : ArmCommand(arm, Arm.startPosition)

}