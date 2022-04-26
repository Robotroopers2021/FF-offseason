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
        Logger.config = LoggerConfig(isLogging = true, isPrinting = false, isLoggingTelemetry = true, isDebugging = false, maxErrorCount = 1)
        koawa = Koawa()
        koawa.drive.setDefaultCommand(
            MecanumCmd(
            koawa.drive,
            driver.leftStick,
            driver.rightStick,
            1.0,1.0,0.65,
            xScalar = 0.75, yScalar = 0.75
        )
        )

        driver.leftTrigger.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret,  koawa.arm, koawa.slides, koawa.clocking))

        driver.rightBumper.onPress(SlidesCommands.SlidesAllianceCommand(koawa.slides))

        driver.rightTrigger.onPress(ClockingCommands.ClockingDeposit(koawa.clocking).alongWith(IntakeCommands.Outtake(koawa.intake)))

        driver.leftBumper.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.turretHomeAngle, koawa.intake, koawa.clocking))

        driver.dpadUp.onPress(DuckSpinnerCommand (0.25, koawa.duckSpinner )
            .pauseFor(0.45)
            .andThen( DuckSpinnerCommand (0.35, koawa.duckSpinner))
            .pauseFor(0.5)
            .andThen(DuckSpinnerCommand (0.85, koawa.duckSpinner ))
            .pauseFor(0.4)
            .andThen(DuckSpinnerCommand (0.0, koawa.duckSpinner ))
            .cancelIf { driver.dpadUp.isJustReleased }
        )

//        driver.x.onPress(SlidesCommands.SlidesSharedCommand(koawa.slides))

//        driver.y.onPress(SlidesCommands.SlidesSharedExtCommand(koawa.slides))

        gunner.leftBumper.onPress(TurretSequenceCommand(koawa.turret, Turret.allianceAngleBlue, koawa.arm, Arm.topPosition, koawa.clocking))

        gunner.b.onPress(TurretSequenceCommand(koawa.turret, Turret.allianceAngleBlue, koawa.arm, Arm.midPosition, koawa.clocking))

        gunner.x.onPress(TurretSequenceCommand(koawa.turret, Turret.allianceAngleBlue, koawa.arm, Arm.bottomPosition, koawa.clocking))

        gunner.rightBumper.onPress(TurretSequenceCommand(koawa.turret, Turret.sharedAngleBlue, koawa.arm, Arm.sharedPosition, koawa.clocking))

        driver.y.onPress(InstantCmd({koawa.turret.motor.followMotionProfile(Turret.allianceAngleBlue)}, koawa.turret)) //for testing

        driver.x.onPress(InstantCmd({koawa.turret.motor.followMotionProfile(Turret .turretHomeAngle)}, koawa.turret)) //for testing

        driver.b.onPress(InstantCmd({koawa.arm.motor.setPIDTarget(Arm.topPosition)}, koawa.arm)) //for testing

        driver.a.onPress(InstantCmd({koawa.arm.motor.setPIDTarget(Arm.armIntakePos)}, koawa.arm)) //for testing

//        koawa.turret.motor.followMotionProfile(0.0)
//        koawa.arm.motor.followMotionProfile(0.0)
    }


    override fun mStart() {
    }

    override fun mLoop() {
//        Logger.addTelemetryData("power", koawa.drive.powers)
//        Logger.addTelemetryData("position", koawa.drive.pose)
//        Logger.addTelemetryData("turret angle", koawa.turret.motor.encoder.position)
        Logger.addTelemetryData("arm angle", koawa.arm.motor.encoder.position)
        Logger.addTelemetryData("dSensor", koawa.hardware.loadingSensor.lastRead)
    }
}
