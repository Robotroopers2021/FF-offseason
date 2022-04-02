package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.group.ParallelCommandGroup
import com.asiankoala.koawalib.command.group.SequentialCommandGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

object DepositCommand {
   class DepositHighBlue(condition: () -> Boolean,
                     private val slides : Slides,
                     private val turret: Turret,
                     private val arm: Arm,
                     private val intake : Intake
                     ) : SequentialCommandGroup(
                         ParallelCommandGroup(
                             TurretCommands.AllianceBlue(turret),
                             ArmCommands.TopPos(arm),
                         ))
}