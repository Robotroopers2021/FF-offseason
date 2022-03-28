package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.command.commands.CommandBase
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup

object IntakeCommands {
    class IntakeSmartCommand(private val intake: Intake) : CommandBase() {
        override fun init() {
            intake.startReading()
            intake.turnOn()
        }

        override fun execute() {
        }

        override fun end(interrupted: Boolean) {
            intake.stopReading()
            intake.turnOff()
        }

        override val isFinished: Boolean
            get() = intake.isMineralIn

        init {
            addRequirements(intake)
        }
    }

    class IntakeOn(private val intake: Intake) : InstantCommand(intake::turnOn, intake)
    class IntakeOff(private val intake: Intake) : InstantCommand(intake::turnOff, intake)
    class IntakeReverse(private val intake: Intake) : InstantCommand(intake::turnReverse, intake)
//    class setIntakeSpeed(private val intake: Intake) : InstantCommand(intake::setIntakeSpeed,intake)

    class IntakeSequenceCommand(
        intake: Intake
    ) : SequentialCommandGroup(
        IntakeSmartCommand(intake),
    )
}