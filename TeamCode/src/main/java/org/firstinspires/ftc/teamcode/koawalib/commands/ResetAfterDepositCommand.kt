package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*
import java.time.Clock

class ResetAfterDepositCommand(turret : Turret, arm : Arm, slides : Slides, turretAngle : Double, intake : Intake, clocking: Clocking) : SequentialGroup(
    IntakeCommands.IntakeOff(intake)
        .alongWith(SlidesCommands.SlidesHomeCommand(slides)),
    WaitCmd(0.5),
    InstantCmd({turret.motor.setPIDTarget(turretAngle)}),
    WaitCmd(0.5),
    (InstantCmd({arm.motor.setPIDTarget(Arm.startPosition)}))
        .alongWith(ClockingCommands.ClockingIntake(clocking))
) {
    init {
        addRequirements(turret, arm, slides, clocking)
    }
}