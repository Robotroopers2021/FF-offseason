package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*
import java.time.Clock

class ResetAfterDepositCommand(turret : Turret, arm : Arm, slides : Slides, turretAngle : Double, intake : Intake, clocking: Clocking) : SequentialCommandGroup(
    IntakeCommands.IntakeOff(intake)
        .alongWith(SlidesCommands.SlidesHomeCommand(slides)),
    WaitCommand(0.5),
    InstantCommand({turret.motor.followMotionProfile(turretAngle)}),
    WaitCommand(0.5),
    (InstantCommand({arm.motor.followMotionProfile(Arm.startPosition)}))
        .alongWith(ClockingCommands.ClockingIntake(clocking))
) {
    init {
        addRequirements(turret, arm, slides, clocking)
    }
}