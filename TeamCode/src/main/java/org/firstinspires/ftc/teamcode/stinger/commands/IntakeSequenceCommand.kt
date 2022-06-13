package org.firstinspires.ftc.teamcode.stinger.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitForCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.stinger.subsystems.*

class IntakeSequenceCommand (intake: Intake, outtake: Outtake, lights: Lights) : SequentialGroup(
    OpenCmd(outtake)
        .alongWith(IntakeCommands.IntakeOn(intake))
        .alongWith(InstantCmd(intake::startReading)),
    WaitForCmd(intake::hasMineral),
    WaitCmd(0.2),
    LockCmd(outtake)
        .alongWith(IntakeCommands.IntakeOff(intake))
        .alongWith(GreenCmd(lights))
        .alongWith(InstantCmd(intake::stopReading)),
    WaitCmd(0.3),
    IntakeCommands.IntakeReverse(intake),
    WaitCmd(0.75),
    IntakeCommands.IntakeOff(intake)
) {
    init {
        addRequirements(intake, outtake, lights)
    }
}