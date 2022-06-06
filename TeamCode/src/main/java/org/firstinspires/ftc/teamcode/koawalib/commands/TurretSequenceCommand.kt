package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

class TurretSequenceCommand(turret : Turret, arm : Arm, slides : Slides, intake : Intake, clocking : Clocking, turretAngle : Double, armAngle : Double) : SequentialGroup(
    InstantCmd({arm.motor.setPIDTarget(armAngle)})
        .alongWith(InstantCmd({turret.motor.setPIDTarget(turretAngle)}))
        .alongWith(SlidesCommands.SlidesHomeCommand(slides))
        .alongWith(ClockingCommands.ClockingLift(clocking))
        .alongWith(IntakeCommands.IntakeOff(intake))
        .alongWith(InstantCmd(intake::stopReading))

) {
    init {
        addRequirements(turret, arm, intake, slides, clocking)
    }
}