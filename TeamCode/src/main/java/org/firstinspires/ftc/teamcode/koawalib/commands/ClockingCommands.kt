package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Clocking

class ClockingCommands {
    class ClockingDeposit(clocking: Clocking) : InstantCmd(clocking::deposit, clocking)
    class ClockingIntake(clocking: Clocking) : InstantCmd(clocking::intakeClocking, clocking)
    class ClockingLift(clocking: Clocking) : InstantCmd(clocking::lift, clocking)
    class ClockingCap(clocking : Clocking) : InstantCmd(clocking::cap, clocking)
}