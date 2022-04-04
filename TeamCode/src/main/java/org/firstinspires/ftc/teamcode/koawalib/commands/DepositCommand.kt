package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.WaitCommand
import com.asiankoala.koawalib.command.group.ParallelCommandGroup
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

class DepositCommand (slides: Slides, intake: Intake) : SequentialCommandGroup(
    SlidesCommands.SlidesAllianceCommand(slides),
    WaitCommand(0.5),
    IntakeCommands.IntakeReverse(intake),
    WaitCommand(1.0),
    IntakeCommands.IntakeOff(intake)
) {
   init {
       addRequirements(slides, intake)
   }
}