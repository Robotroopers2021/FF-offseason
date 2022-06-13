package org.firstinspires.ftc.teamcode.stinger.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.stinger.subsystems.Intake

class IntakeCommands {
    class IntakeOn(private val intake: Intake) : InstantCmd(intake::turnOn, intake)
    class IntakeOff(private val intake: Intake) : InstantCmd(intake::turnOff, intake)
    class IntakeReverse(private val intake: Intake) : InstantCmd(intake::turnReverse, intake)

    class IntakeReverseSequence(private val intake: Intake) : SequentialGroup(
        InstantCmd(intake::turnReverse, intake)
    ){
        init {
            addRequirements(intake)
        }
        override fun end() {
            intake.turnOff()
        }
    }
}