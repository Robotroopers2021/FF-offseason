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
    InstantCmd(intake::stopReading),
    WaitCmd(0.1),
    LockCmd(outtake)
        .alongWith(IntakeCommands.IntakeOff(intake))
        .alongWith(GreenCmd(lights)),
    WaitCmd(0.01),
    IntakeCommands.IntakeReverse(intake),
    WaitCmd(0.5),
    IntakeCommands.IntakeOff(intake)
) {
    init {
        addRequirements(intake, outtake, lights)
    }
}