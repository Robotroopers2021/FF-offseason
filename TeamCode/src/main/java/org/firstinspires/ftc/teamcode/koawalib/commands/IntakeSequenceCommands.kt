package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.*
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

class IntakeSequenceCommand (intake : Intake, turret : Turret, arm : Arm, slides : Slides, clocking : Clocking, door : Door) : SequentialGroup(
        ClockingCommands.ClockingIntake(clocking)
                .alongWith(InstantCmd({arm.motor.setPIDTarget(Arm.armIntakePos)}, arm))
                .alongWith(DoorIntake(door)),
        WaitCmd(0.3),
        SlidesCommands.SlidesIntakeCommand(slides)
                .alongWith(IntakeCommands.IntakeOn(intake))
                .alongWith(InstantCmd(intake::startReading)),
        WaitForCmd(intake::hasMineral),
        ClockingCommands.ClockingLift(clocking)
                .alongWith(IntakeCommands.IntakeOff(intake))
                .alongWith(DoorLock(door)),
        WaitCmd(0.3),
        SlidesCommands.SlidesHomeCommand(slides)
                .alongWith(InstantCmd(intake::stopReading)),
) {
        init {
            addRequirements(intake, turret, arm, slides, clocking, door )
        }
}

class IntakeSequenceExtCommand (intake : Intake, turret : Turret, arm : Arm, slides : Slides, clocking : Clocking, door : Door) : SequentialGroup(
        ClockingCommands.ClockingIntake(clocking)
                .alongWith(InstantCmd({arm.motor.setPIDTarget(Arm.armIntakeExt)}, arm)),
        WaitCmd(0.3),
        SlidesCommands.SlidesIntakeExtCommand(slides)
                .alongWith(IntakeCommands.IntakeOn(intake))
                .alongWith(InstantCmd(intake::startReading)),
        WaitForCmd(intake::hasMineral),
        ClockingCommands.ClockingLift(clocking)
                .alongWith(IntakeCommands.IntakeOff(intake))
                .alongWith(DoorLock(door)),
        WaitCmd(0.3),
        SlidesCommands.SlidesHomeCommand(slides)
                .alongWith(InstantCmd(intake::stopReading)),
) {
        init {
                addRequirements(intake, turret, arm, slides, clocking, door )
        }
}


