package org.firstinspires.ftc.teamcode.perseus.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import org.firstinspires.ftc.teamcode.perseus.subsystem.Intake

object IntakeCommands {
    class IntakeOn(private val intake: Intake) : InstantCmd(intake::turnOn, intake)
    class IntakeOff(private val intake: Intake) : InstantCmd(intake::turnOff, intake)
    class IntakeReverse(private val intake: Intake) : InstantCmd(intake::turnReverse, intake)
    class IntakeSlow(private val intake: Intake) : InstantCmd(intake::slowIntake)
    class Outtake(private val intake: Intake) : InstantCmd(intake::outtake)
}