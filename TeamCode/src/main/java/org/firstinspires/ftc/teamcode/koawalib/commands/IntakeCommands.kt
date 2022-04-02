package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.CommandBase
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Intake

object IntakeCommands {
    class IntakeSmartCommand(private val intake: Intake) : CommandBase() {
        override fun initialize() {
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
            get() = intake.hasMineral

        init {
            addRequirements(intake)
        }
    }

    class IntakeOn(private val intake: Intake) : InstantCommand(intake::turnOn, intake)
    class IntakeOff(private val intake: Intake) : InstantCommand(intake::turnOff, intake)
    class IntakeReverse(private val intake: Intake) : InstantCommand(intake::turnReverse, intake)

    class IntakeSequenceCommand(
        intake: Intake
    ) : SequentialCommandGroup(
        IntakeSmartCommand(intake)
    )
}