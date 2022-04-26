package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Intake

object IntakeCommands {
    class IntakeOn(private val intake: Intake) : InstantCmd(intake::turnOn, intake)
    class IntakeOff(private val intake: Intake) : InstantCmd(intake::turnOff, intake)
    class IntakeReverse(private val intake: Intake) : InstantCmd(intake::turnReverse, intake)
    class IntakeFast(private val intake: Intake) : InstantCmd(intake::fastIntake)
    class Outtake(private val intake: Intake) : InstantCmd(intake::outtake)
}