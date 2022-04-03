package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.commands.GoToPointCommand
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import com.asiankoala.koawalib.util.Logger
import com.asiankoala.koawalib.util.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.commands.IntakeCommands
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.commands.IntakeSequenceCommand
import org.firstinspires.ftc.teamcode.koawalib.commands.ResetAfterDepositCommand
import org.firstinspires.ftc.teamcode.koawalib.commands.SlidesCommands
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Arm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

@TeleOp
class KoawaBlueOp : CommandOpMode() {
    private lateinit var koawa : Koawa
    override fun mInit() {
        Logger.config = LoggerConfig(isLogging = true, isPrinting = false, isLoggingTelemetry = false, isDebugging = false, maxErrorCount = 1)
        koawa = Koawa()

        koawa.drive.setDefaultCommand(MecanumDriveCommand(
            koawa.drive,
            driver.leftStick.yInverted.xInverted,
            driver.rightStick.xInverted,
            0.85,0.85,0.65,
            xScalar = 0.75, yScalar = 0.75
        ))

        driver.leftTrigger.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret, Turret.allianceAngleBlue, koawa.arm, koawa.slides, Arm.topPosition))

        driver.rightBumper.onPress(SlidesCommands.SlidesAllianceCommand(koawa.slides))

        driver.rightTrigger.onPress(IntakeCommands.IntakeReverse(koawa.intake))

        driver.leftBumper.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.turretHomeAngle))

        driver.dpadUp.onPress(DuckSpinnerCommand (0.25, koawa.duckSpinner )
            .pauseFor(0.45)
            .andThen( DuckSpinnerCommand (0.35, koawa.duckSpinner))
            .pauseFor(0.5)
            .andThen(DuckSpinnerCommand (0.85, koawa.duckSpinner ))
            .pauseFor(0.4)
            .andThen(DuckSpinnerCommand (0.0, koawa.duckSpinner ))
        )

//        driver.y.onPress(IntakeSequenceCommand(koawa.intake, koawa.turret, Turret.sharedAngleBlue, koawa.arm, koawa.slides, Arm.sharedPosition))
//
//        driver.x.onPress(SlidesCommands.SlidesSharedCommand(koawa.slides))
//
//        driver.b.onPress(ResetAfterDepositCommand(koawa.turret, koawa.arm, koawa.slides, Turret.sharedHomeAngle))

        driver.y.onPress(InstantCommand({koawa.turret.setPIDTarget(Turret.allianceAngleBlue)}, koawa.turret))

        driver.x.onPress(InstantCommand({koawa.turret.setPIDTarget(Turret.turretHomeAngle)}, koawa.turret))

        driver.b.onPress(InstantCommand({koawa.arm.setPIDTarget(Arm.topPosition)}, koawa.arm))

        driver.a.onPress(InstantCommand({koawa.arm.setPIDTarget(Arm.startPosition)}, koawa.arm))
    }


    override fun mStart() {
        koawa.turret.disabled = false
        koawa.arm.disabled = false
        koawa.turret.setPIDTarget(0.0)
        koawa.arm.setPIDTarget(0.0)
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
