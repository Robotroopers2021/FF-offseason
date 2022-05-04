package org.firstinspires.ftc.teamcode.koawalib.opmodes

import android.util.Log
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitForCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.commands.*
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

@TeleOp
class KoawaBlueOp : KOpMode() {
    private lateinit var koawa : Koawa

    override fun mInit() {
        Logger.config = LoggerConfig(
            isLogging = true,
            isPrinting = false,
            isLoggingTelemetry = true,
            isDebugging = false,
            maxErrorCount = 1
        )

        koawa = Koawa()
        bindDrive()
        bindDuck()
        bindDeposit()
        bindIntake()
        bindTurret()
        bindCapping()
//        bindTest()
    }

    private fun bindDrive() {
        koawa.drive.setDefaultCommand(
            MecanumCmd(
                koawa.drive,
                driver.leftStick,
                driver.rightStick,
                1.0, 1.0, 0.65,
                xScalar = 0.75, yScalar = 0.75
            )
        )
    }

    private fun bindDuck() {
        driver.dpadUp.onPress(DuckSequenceCommand(koawa.duckSpinner).cancelIf (driver.dpadUp::isJustReleased))
    }

    private fun bindDeposit() {
        driver.rightTrigger.onPress(SlidesCommands.SlidesAllianceCommand(koawa.slides))
        driver.rightBumper.onPress(SlidesCommands.SlidesSharedExtCommand(koawa.slides))
        driver.a.onPress(SlidesCommands.SlidesSharedCommand(koawa.slides))
        driver.b.onPress(ClockingCommands.ClockingDeposit(koawa.clocking).alongWith(IntakeCommands.Outtake(koawa.intake)))
    }

    private fun bindIntake() {
        driver.leftTrigger.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret,  koawa.arm, koawa.slides, koawa.clocking))
        driver.leftBumper.onPress(IntakeSequenceExtCommand(koawa.intake, koawa.turret,  koawa.arm, koawa.slides, koawa.clocking))
    }

    private fun bindTurret() {
        gunner.leftBumper.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.allianceAngleBlue, Arm.topPosition))
        gunner.rightBumper.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.sharedAngle, Arm.sharedPosition))
        gunner.leftTrigger.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.allianceHomeAngle, koawa.intake, koawa.clocking))
        gunner.rightTrigger.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.sharedHomeAngle, koawa.intake, koawa.clocking))
        gunner.a.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.allianceAngleBlue, Arm.midPosition))
    }

    private fun bindTest() {
        gunner.x.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.allianceAngleBlue, Arm.bottomPosition))
        driver.y.onPress(InstantCmd({koawa.turret.motor.setPIDTarget(Turret.allianceAngleBlue)}, koawa.turret))
        driver.x.onPress(InstantCmd({koawa.turret.motor.setPIDTarget(Turret.allianceHomeAngle)}, koawa.turret))
        driver.b.onPress(InstantCmd({koawa.arm.motor.setPIDTarget(Arm.topPosition)}, koawa.arm))
        driver.a.onPress(InstantCmd({koawa.arm.motor.setPIDTarget(Arm.armIntakePos)}, koawa.arm))
    }

    private fun bindCapping() {
        gunner.dpadLeft.onPress(TurretCommand(-0.25, koawa.turret).cancelIf { gunner.dpadLeft.isJustReleased })
        gunner.dpadRight.onPress(TurretCommand(0.25, koawa.turret).cancelIf { gunner.dpadRight.isJustReleased })
        gunner.dpadUp.onPress(ArmCommand(0.5, koawa.arm).cancelIf { gunner.dpadUp.isJustReleased })
        gunner.dpadDown.onPress(ArmCommand(-0.5, koawa.arm).cancelIf { gunner.dpadDown.isJustReleased })
    }

    override fun mStart() {
    }

    override fun mLoop() {
//        Logger.addTelemetryData("power", koawa.drive.powers)
//        Logger.addTelemetryData("position", koawa.drive.pose)
        Logger.addTelemetryData("turret angle", koawa.turret.motor.encoder.position)
        Logger.addTelemetryData("arm angle", koawa.arm.motor.encoder.position)
        Logger.addTelemetryData("dSensor", koawa.hardware.loadingSensor.lastRead)
    }
}