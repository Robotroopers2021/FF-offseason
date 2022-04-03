package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

class ResetAfterDepositCommand(turret : Turret, arm : Arm, slides : Slides, turretAngle : Double) : SequentialCommandGroup(
    SlidesCommands.SlidesHomeCommand(slides),
    WaitCommand(0.3),
    InstantCommand({turret.setPIDTarget(turretAngle)})
        .alongWith(InstantCommand({arm.setPIDTarget(Arm.armIntakePos)}))
) {
    init {
        addRequirements(turret, arm, slides)
    }
}