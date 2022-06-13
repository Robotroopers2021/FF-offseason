package org.firstinspires.ftc.teamcode.perseus.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.perseus.subsystem.*

class ResetAfterDepositCommand(turret : Turret, arm : Arm, slides : Slides, turretAngle : Double, intake : Intake, clocking: Clocking, door: Door) : SequentialGroup(
    IntakeCommands.IntakeOff(intake)
        .alongWith(SlidesCommands.SlidesHomeCommand(slides)),
    WaitCmd(0.5),
    InstantCmd({turret.motor.setPIDTarget(turretAngle)}),
    WaitCmd(0.5),
    (InstantCmd({arm.motor.setPIDTarget(Arm.homePosition)}))
        .alongWith(ClockingCommands.ClockingIntake(clocking))
        .alongWith(DoorIntake(door))
) {
    init {
        addRequirements(turret, arm, slides, clocking, door)
    }
}