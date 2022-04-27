package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Clocking
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

class TurretSequenceCommand(turret : Turret, turretAngle: Double, arm: Arm, armAngle: Double, clocking: Clocking) : SequentialGroup(
    InstantCmd({arm.motor.setPIDTarget(Arm.topPosition)})
        .alongWith(InstantCmd({turret.motor.setPIDTarget(turretAngle)})),
    WaitCmd(0.3),
    InstantCmd({arm.motor.setPIDTarget(armAngle)})
)

class TurretSharedCommand(turret: Turret, turretAngle : Double, arm : Arm, armAngle : Double) : SequentialGroup(
    InstantCmd({arm.motor.setPIDTarget(60.0)})
        .alongWith(InstantCmd({turret.motor.setPIDTarget(turretAngle)})),
    WaitCmd(0.5),
    InstantCmd({arm.motor.setPIDTarget(armAngle)})
)