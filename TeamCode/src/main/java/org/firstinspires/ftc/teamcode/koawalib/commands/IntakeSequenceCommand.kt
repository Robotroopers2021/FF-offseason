package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.commands.WaitUntilCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

class IntakeSequenceCommand (intake : Intake, turret : Turret, turretAngle: Double, arm : Arm, slides : Slides, armAngle : Double, clocking : Clocking) : SequentialCommandGroup(
        ClockingCommands.ClockingIntake(clocking)
                .alongWith(SlidesCommands.SlidesIntakeCommand(slides)),
        WaitCommand(0.3),
        InstantCommand({arm.setPIDTarget(Arm.armIntakePos)}, arm)
                .alongWith(IntakeCommands.IntakeOn(intake))
                .alongWith(InstantCommand(intake::startReading)),
        WaitUntilCommand(intake::hasMineral),
        IntakeCommands.IntakeSlow(intake),
        WaitCommand(0.5),
        InstantCommand({arm.setPIDTarget(armAngle)}, arm)
                .alongWith(SlidesCommands.SlidesHomeCommand(slides))
                .alongWith(ClockingCommands.ClockingLift(clocking))
//                .alongWith(InstantCommand({turret.setPIDTarget(turretAngle)}, turret))
) {
        init {
            addRequirements(intake, turret, arm, slides, clocking )
        }
}


