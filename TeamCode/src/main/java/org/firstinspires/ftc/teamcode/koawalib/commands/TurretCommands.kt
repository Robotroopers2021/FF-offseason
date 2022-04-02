package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.CommandBase
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

object TurretCommands {
    open class TurretCommand(private val turret: Turret, private val angle: Double) : CommandBase() {
        override fun execute() {
        }

        override val isFinished: Boolean
            get() = turret.isAtTarget

        init {
            addRequirements(turret)
            turret.setPIDTarget(angle)
        }
    }

    class Home(turret: Turret) : TurretCommand(turret, Turret.turretHomeAngle)
    class SharedBlue(turret: Turret) : TurretCommand(turret, Turret.sharedAngleBlue)
    class SharedRed(turret: Turret) : TurretCommand(turret, Turret.sharedAngleRed)
    class AllianceBlue(turret: Turret) : TurretCommand(turret, Turret.allianceAngleBlue)
    class AllianceRed(turret: Turret) : TurretCommand(turret, Turret.allianceAngleRed)
}