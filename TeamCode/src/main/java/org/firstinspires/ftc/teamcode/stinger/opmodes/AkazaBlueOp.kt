package org.firstinspires.ftc.teamcode.stinger.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.stinger.Stinger
import org.firstinspires.ftc.teamcode.stinger.commands.DuckSequenceCommand
import org.firstinspires.ftc.teamcode.stinger.commands.IntakeCommands
import org.firstinspires.ftc.teamcode.stinger.commands.IntakeSequenceCommand
import org.firstinspires.ftc.teamcode.stinger.commands.ResetCmd
import org.firstinspires.ftc.teamcode.stinger.subsystems.*

@TeleOp
class AkazaBlueOp : KOpMode() {
    private lateinit var stinger: Stinger

    override fun mInit() {
        Logger.config = LoggerConfig(
            isLogging = true,
            isPrinting = false,
            isLoggingTelemetry = true,
            isDebugging = true,
            maxErrorCount = 1
        )

        stinger = Stinger()
        bindDrive()
        bindDuck()
        bindArm()
        bindOuttake()
        bindIntake()
    }

    private fun bindDrive() {
        stinger.drive.setDefaultCommand(
            MecanumCmd(
                stinger.drive,
                driver.leftStick.yInverted,
                driver.rightStick,
                0.85, 0.85, 0.65,
                xScalar = 0.75, yScalar = 0.75
            )
        )
    }

    private fun bindDuck() {
        driver.dpadUp.onPress(DuckSequenceCommand(stinger.duckSpinner).cancelIf (driver.dpadUp::isJustReleased))
    }

    private fun bindArm() {
        driver.leftBumper.onPress(InstantCmd({stinger.arm.motor.setPIDTarget(Arm.depositAngle)}, stinger.arm).alongWith(LockCmd(stinger.outtake)).alongWith(GreenCmd(stinger.lights)))
        driver.a.onPress(InstantCmd({stinger.arm.motor.setPIDTarget(Arm.sharedAngle)}, stinger.arm).alongWith(LockCmd(stinger.outtake)).alongWith(GreenCmd(stinger.lights)))
        driver.x.onPress(InstantCmd({stinger.arm.motor.setPIDTarget(Arm.sharedAngleAlliance)}, stinger.arm).alongWith(LockCmd(stinger.outtake)).alongWith(GreenCmd(stinger.lights)))
        driver.b.onPress(InstantCmd({stinger.arm.motor.setPIDTarget(Arm.sharedAngleEnemy)}, stinger.arm).alongWith(LockCmd(stinger.outtake)).alongWith(GreenCmd(stinger.lights)))
        driver.y.onPress(InstantCmd({stinger.arm.motor.setPIDTarget(Arm.middlePos)}, stinger.arm).alongWith(LockCmd(stinger.outtake)).alongWith(GreenCmd(stinger.lights)))
        driver.rightBumper.onPress(ResetCmd(stinger.arm, stinger.intake, stinger.outtake, stinger.lights))
        gunner.leftTrigger.onPress(InstantCmd({stinger.arm.motor.setPIDTarget(Arm.failPos)}, stinger.arm))
    }

    private fun bindOuttake() {
        gunner.a.onPress(OpenCmd(stinger.outtake).alongWith(RedCmd(stinger.lights)))
        gunner.b.onPress(OuttakeCmd(stinger.outtake).alongWith(RedCmd(stinger.lights)))
        gunner.x.onPress(LockCmd(stinger.outtake).alongWith(GreenCmd(stinger.lights)))
    }

    private fun bindIntake() {
        driver.leftTrigger.onPress(IntakeSequenceCommand(stinger.intake, stinger.outtake, stinger.lights))
        driver.rightTrigger.onPress(IntakeCommands.IntakeReverseSequence(stinger.intake).cancelIf { driver.rightTrigger.isJustReleased })
    }

    override fun mLoop() {
        Logger.addTelemetryData("dSensor", stinger.hardware.loadingSensor.lastRead)
    }
}