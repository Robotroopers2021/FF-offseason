package org.firstinspires.ftc.teamcode.stinger.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import org.firstinspires.ftc.teamcode.stinger.subsystems.*

class ResetCmd (arm : Arm, intake : Intake, outtake: Outtake, lights: Lights) : ParallelGroup(
    InstantCmd({arm.motor.setPIDTarget(Arm.restAngle)}),
    IntakeCommands.IntakeOff(intake),
    OpenCmd(outtake),
    RedCmd(lights)
) {
    init {
        addRequirements(arm, intake, outtake, lights)
    }
}