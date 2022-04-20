package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Clocking

class ClockingCommands {
    class ClockingDeposit(clocking: Clocking) : InstantCommand(clocking::deposit, clocking)
    class ClockingIntake(clocking: Clocking) : InstantCommand(clocking::intakeClocking, clocking)
    class ClockingLift(clocking: Clocking) : InstantCommand(clocking::lift, clocking)
}