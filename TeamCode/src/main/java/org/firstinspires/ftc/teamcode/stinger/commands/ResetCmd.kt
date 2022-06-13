package org.firstinspires.ftc.teamcode.stinger.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.ParallelGroup
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.stinger.subsystems.*

class ResetCmd (arm : Arm, intake : Intake, outtake: Outtake, lights: Lights) : SequentialGroup(
    InstantCmd({arm.motor.setPIDTarget(Arm.restAngle)})
        .alongWith(IntakeCommands.IntakeOff(intake))
        .alongWith(OpenCmd(outtake))
        .alongWith(RedCmd(lights))
) {
    init {
        addRequirements(arm, intake, outtake, lights)
    }
}