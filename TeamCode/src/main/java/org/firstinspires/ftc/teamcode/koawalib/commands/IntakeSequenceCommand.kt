package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.commands.WaitUntilCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

class IntakeSequenceCommand (intake : Intake, turret : Turret, turretAngle: Double, arm : Arm, slides : Slides, armAngle : Double, clocking : Clocking) : SequentialCommandGroup(
        ClockingCommands.ClockingIntake(clocking)
                .alongWith(InstantCommand({arm.setPIDTarget(Arm.armIntakePos)}, arm)),
        WaitCommand(0.3),
        SlidesCommands.SlidesIntakeCommand(slides)
                .alongWith(IntakeCommands.IntakeOn(intake))
                .alongWith(InstantCommand(intake::startReading)),
        WaitUntilCommand(intake::hasMineral),
        IntakeCommands.IntakeOff(intake),
        WaitCommand(0.25),
        InstantCommand({arm.setPIDTarget(armAngle)}, arm),
        WaitCommand(0.3),
        SlidesCommands.SlidesHomeCommand(slides)
//                .alongWith(InstantCommand({turret.setPIDTarget(turretAngle)}, turret))
) {
        init {
            addRequirements(intake, turret, arm, slides, clocking )
        }
}

class TurretSequenceCommand(turret : Turret, turretAngle: Double, arm: Arm, armAngle: Double, clocking: Clocking) : SequentialCommandGroup(
        InstantCommand({arm.setPIDTarget(Arm.topPosition)}),
        WaitCommand(0.5),
        InstantCommand({turret.setPIDTarget(turretAngle)}),
        WaitCommand(0.5),
        InstantCommand({arm.setPIDTarget(armAngle)})
)
