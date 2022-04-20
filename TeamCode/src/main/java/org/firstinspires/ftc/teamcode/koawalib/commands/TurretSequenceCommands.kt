package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Clocking
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

class TurretSequenceCommand(turret : Turret, turretAngle: Double, arm: Arm, armAngle: Double, clocking: Clocking) : SequentialCommandGroup(
    InstantCommand({arm.setPIDTarget(Arm.topPosition)})
        .alongWith(InstantCommand({turret.setPIDTarget(turretAngle)})),
    WaitCommand(0.3),
    InstantCommand({arm.setPIDTarget(armAngle)})
)

class TurretSharedCommand(turret: Turret, turretAngle : Double, arm : Arm, armAngle : Double) : SequentialCommandGroup(
    InstantCommand({arm.setPIDTarget(60.0)})
        .alongWith(InstantCommand({turret.setPIDTarget(turretAngle)})),
    WaitCommand(0.5),
    InstantCommand({arm.setPIDTarget(armAngle)})
)