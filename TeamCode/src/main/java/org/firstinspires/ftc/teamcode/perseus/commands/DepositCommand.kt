package org.firstinspires.ftc.teamcode.perseus.commands

import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.perseus.subsystem.*

class DepositCommand (slides: Slides, intake: Intake, clocking : Clocking) : SequentialGroup(
    SlidesCommands.SlidesAllianceCommand(slides),
    WaitCmd(0.5),
    ClockingCommands.ClockingDeposit(clocking),
    WaitCmd(0.3),
    IntakeCommands.Outtake(intake),
    WaitCmd(1.0),
    IntakeCommands.IntakeOff(intake)
) {
   init {
       addRequirements(slides, intake)
   }
}