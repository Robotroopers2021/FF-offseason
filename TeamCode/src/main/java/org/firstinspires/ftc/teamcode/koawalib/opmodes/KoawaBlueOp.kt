package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.commands.GoToPointCommand
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.command.commands.MecanumDriveCommand
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.util.Logger
import com.asiankoala.koawalib.util.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.commands.*
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

@TeleOp
class KoawaBlueOp : CommandOpMode() {
    private lateinit var koawa : Koawa
    override fun mInit() {
        Logger.config = LoggerConfig(isLogging = true, isPrinting = false, isLoggingTelemetry = false, isDebugging = false, maxErrorCount = 1)
        koawa = Koawa()

        koawa.drive.setDefaultCommand(
            MecanumDriveCommand(
            koawa.drive,
            driver.leftStick,
            driver.rightStick.xInverted,
            1.0,1.0,0.65,
            xScalar = 0.75, yScalar = 0.75
        )
        )

        driver.leftTrigger.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret, Turret.allianceAngleBlue, koawa.arm, koawa.slides, Arm.topPosition, koawa.clocking))

        driver.rightBumper.onPress(SlidesCommands.SlidesAllianceCommand(koawa.slides))

        driver.rightTrigger.onPress(ClockingCommands.ClockingDeposit(koawa.clocking).alongWith(IntakeCommands.IntakeReverse(koawa.intake)))

        driver.leftBumper.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.turretHomeAngle, koawa.intake, koawa.clocking))

        driver.dpadUp.onPress(DuckSpinnerCommand (0.25, koawa.duckSpinner )
            .pauseFor(0.45)
            .andThen( DuckSpinnerCommand (0.35, koawa.duckSpinner))
            .pauseFor(0.5)
            .andThen(DuckSpinnerCommand (0.85, koawa.duckSpinner ))
            .pauseFor(0.4)
            .andThen(DuckSpinnerCommand (0.0, koawa.duckSpinner ))
        )

        driver.y.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret, Turret.sharedAngleBlue, koawa.arm, koawa.slides, Arm.sharedPosition, koawa.clocking))

        driver.x.onPress(SlidesCommands.SlidesSharedCommand(koawa.slides))

        driver.b.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.sharedHomeAngle, koawa.intake, koawa.clocking))

        gunner.leftBumper.onPress(TurretSequenceCommand(koawa.turret, Turret.allianceAngleBlue, koawa.arm, Arm.topPosition, koawa.clocking))

        gunner.b.onPress(TurretSequenceCommand(koawa.turret, Turret.allianceAngleBlue, koawa.arm, Arm.midPosition, koawa.clocking))

        gunner.x.onPress(TurretSequenceCommand(koawa.turret, Turret.allianceAngleBlue, koawa.arm, Arm.bottomPosition, koawa.clocking))

        gunner.rightBumper.onPress(TurretSequenceCommand(koawa.turret, Turret.sharedAngleBlue, koawa.arm, Arm.sharedPosition, koawa.clocking))

//        driver.y.onPress(InstantCommand({koawa.turret.setPIDTarget(Turret.allianceAngleBlue)}, koawa.turret))
//
//        driver.x.onPress(InstantCommand({koawa.turret.setPIDTarget(Turret.turretHomeAngle)}, koawa.turret))
//
//        driver.b.onPress(InstantCommand({koawa.arm.setPIDTarget(Arm.topPosition)}, koawa.arm))
//
//        driver.a.onPress(InstantCommand({koawa.arm.setPIDTarget(Arm.armIntakePos)}, koawa.arm))
    }


    override fun mStart() {
//        koawa.turret.disabled = false
//        koawa.arm.disabled = false
        koawa.slides.unregister()
        koawa.turret.unregister()
        koawa.arm.unregister()

//        koawa.turret.setPIDTarget(0.0)
//        koawa.arm.setPIDTarget(30.0)
    }

    override fun mLoop() {
        koawa.imu.periodic()
        Logger.addTelemetryData("power", koawa.drive.powers)
        Logger.addTelemetryData("position", koawa.drive.position)
        Logger.addTelemetryData("turret angle", koawa.turretEncoder.position)
        Logger.addTelemetryData("arm angle", koawa.armEncoder.position)
        Logger.addTelemetryData("dSensor", koawa.loadingSensor.invokeDouble())
    }
}
