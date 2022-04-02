package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.commands.WaitUntilCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Intake
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

class IntakeSequenceCommand (intake : Intake, turret : Turret, turretAngle: Double, arm : Arm, slides : Slides, armAngle : Double) : SequentialCommandGroup(
        SlidesCommands.SlidesIntakeCommand(slides),
        WaitCommand(0.1),
        IntakeCommands.IntakeOn(intake)
                .alongWith(InstantCommand(intake::startReading)),
        WaitUntilCommand(intake::hasMineral),
        IntakeCommands.IntakeOff(intake),
        WaitCommand(0.1),
        SlidesCommands.SlidesHomeCommand(slides),
        WaitCommand(0.1),
        InstantCommand({arm.setPIDTarget(armAngle)}, arm)
                .alongWith(InstantCommand({turret.setPIDTarget(turretAngle)}, turret))
) {
        init {
            addRequirements(intake, turret, arm, slides, )
        }
}
