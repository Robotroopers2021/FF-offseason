package org.firstinspires.ftc.teamcode.perseus.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.perseus.Koawa
import org.firstinspires.ftc.teamcode.perseus.commands.*
import org.firstinspires.ftc.teamcode.perseus.subsystem.*

@TeleOp
class KoawaBlueOp : KOpMode() {
    private lateinit var koawa : Koawa

    override fun mInit() {
        Logger.config = LoggerConfig(
            isLogging = true,
            isPrinting = false,
            isLoggingTelemetry = true,
            isDebugging = true,
            maxErrorCount = 1
        )

        koawa = Koawa()
        bindDrive()
        bindDuck()
        bindDeposit()
        bindIntake()
        bindTurret()
//        bindCapping()
        bindTest()

//        koawa.hardware.leftRetract.startAt(0.0)
//        koawa.hardware.rightRetract.startAt(0.0)
//        koawa.hardware.perpRetract.startAt(0.8)
    }

    private fun bindDrive() {
        koawa.drive.setDefaultCommand(
            MecanumCmd(
                koawa.drive,
                driver.leftStick,
                driver.rightStick,
                1.0, 1.0, 0.85,
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
        gunner.b.onPress(ClockingCommands.ClockingDeposit(koawa.clocking)
            .alongWith(IntakeCommands.Outtake(koawa.intake))
            .alongWith(DoorOpen(koawa.door)))
    }

    private fun bindIntake() {
        driver.leftTrigger.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret,  koawa.arm, koawa.slides, koawa.clocking, koawa.door))
        driver.leftBumper.onPress(IntakeSequenceExtCommand(koawa.intake, koawa.turret,  koawa.arm, koawa.slides, koawa.clocking, koawa.door))
    }

    private fun bindTurret() {
        gunner.leftBumper.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.allianceAngleBlue, Arm.topPosition))
        gunner.rightBumper.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.sharedAngle, Arm.sharedPosition))
        gunner.leftTrigger.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.allianceHomeAngle, koawa.intake, koawa.clocking, koawa.door))
        gunner.rightTrigger.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.sharedHomeAngle, koawa.intake, koawa.clocking, koawa.door))
        gunner.a.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.allianceAngleBlue, Arm.midPosition))
    }

    private fun bindTest() {
//        gunner.x.onPress(TurretSequenceCommand(koawa.turret, koawa.arm, koawa.slides, koawa.intake, koawa.clocking, Turret.allianceAngleBlue, Arm.bottomPosition))
//        driver.y.onPress(InstantCmd({koawa.turret.motor.setPIDTarget(Turret.allianceAngleBlue)}, koawa.turret))
//        driver.x.onPress(InstantCmd({koawa.turret.motor.setPIDTarget(Turret.allianceHomeAngle)}, koawa.turret))
//        driver.b.onPress(InstantCmd({koawa.arm.motor.setPIDTarget(Arm.topPosition)}, koawa.arm))
//        driver.a.onPress(InstantCmd({koawa.arm.motor.setPIDTarget(Arm.armIntakePos)}, koawa.arm))
        driver.y.onPress(SlidesCommands.SlidesHomeCommand(koawa.slides))
        driver.x.onPress(SlidesCommands.SlidesAllianceCommand(koawa.slides))
    }

    private fun bindCapping() {
        gunner.dpadLeft.onPress(TurretCommand(-0.25, koawa.turret).cancelIf { gunner.dpadLeft.isJustReleased }.alongWith(ClockingCommands.ClockingCap(koawa.clocking)))
        gunner.dpadRight.onPress(TurretCommand(0.25, koawa.turret).cancelIf { gunner.dpadRight.isJustReleased }.alongWith(ClockingCommands.ClockingCap(koawa.clocking)))
        gunner.dpadUp.onPress(ArmCommand(0.5, koawa.arm).cancelIf { gunner.dpadUp.isJustReleased }.alongWith(ClockingCommands.ClockingCap(koawa.clocking)))
        gunner.dpadDown.onPress(ArmCommand(-0.5, koawa.arm).cancelIf { gunner.dpadDown.isJustReleased }.alongWith(ClockingCommands.ClockingCap(koawa.clocking)))
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