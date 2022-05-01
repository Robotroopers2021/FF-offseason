package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.commands.*
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

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
        driver.dpadUp.whilePressed(DuckSpinnerCommand (0.25, koawa.duckSpinner )
            .pauseFor(0.45)
            .andThen( DuckSpinnerCommand (0.35, koawa.duckSpinner))
            .pauseFor(0.5)
            .andThen(DuckSpinnerCommand (0.85, koawa.duckSpinner ))
            .pauseFor(0.4)
            .andThen(DuckSpinnerCommand (0.0, koawa.duckSpinner ))
            .cancelIf { driver.dpadUp.isJustReleased }
        )
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
        gunner.dpadLeft.onPress(InstantCmd({koawa.turret.motor.setPIDTarget(355.0)}, koawa.turret))
        gunner.dpadRight.onPress(InstantCmd({koawa.turret.motor.setPIDTarget(10.0)}, koawa.turret))
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