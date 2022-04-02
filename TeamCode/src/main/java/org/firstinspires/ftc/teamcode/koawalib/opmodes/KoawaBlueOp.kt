package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.commands.GoToPointCommand
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import com.asiankoala.koawalib.util.Logger
import com.asiankoala.koawalib.util.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.commands.ArmCommands
import org.firstinspires.ftc.teamcode.koawalib.commands.IntakeCommands
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand

@TeleOp
class KoawaBlueOp : CommandOpMode() {
    private lateinit var robot: Koawa
    override fun mInit() {
        LoggerConfig(isLogging = true,  isDebugging = true)
        robot = Koawa()
        robot.drive.setStartPose(Pose(0.0, 0.0, 90.0.radians))
        bindIntake()
        bindSpinnerBlue()
        bindDrive()
        bindArm()
        goToPoint()
//        bindDeposit()
    }

    override fun mLoop() {
        robot.imu.periodic()
        robot.armEncoder.update()
        robot.turretEncoder.update()
        Logger.addTelemetryData("dSensor", robot.loadingSensor.invokeDouble())
        Logger.addTelemetryData("driver powers", robot.drive.powers)
        Logger.addTelemetryData("position", robot.drive.position)
    }

    fun bindDrive() {
        robot.drive.setDefaultCommand(MecanumDriveCommand(
            robot.drive,
            driver.leftStick,
            driver.rightStick.xInverted.yInverted,
            1.0, 1.0, 1.0,
            xScalar = 0.7, yScalar = 0.7, rScalar = 0.7,
        ))
    }

    fun bindSlides() {

    }

    fun bindIntake() {
//        driver.leftTrigger.whenPressed(IntakeCommands.IntakeSequenceCommand(robot.intake))
        driver.leftTrigger.whenPressed(IntakeCommands.IntakeOn(robot.intake))
        driver.rightTrigger.whenPressed(IntakeCommands.IntakeReverse(robot.intake))
        InfiniteCommand({ robot.intake.turnOff() }, robot.intake)
//        driver.leftTrigger.whenPressed(IntakeCommands.setIntakeSpeed(robot.intake))
    }

    fun goToPoint() {
//        driver.rightTrigger.onPress(
//            GoToPointCommand(robot.drive,
//                Pose(36.0, 24.0),
//                2.0, 2.0.radians,
//                maxMoveSpeed = 0.8,
//                maxTurnSpeed = 0.8,
//            )
//                .pauseFor(2.0)
//                .andThen(GoToPointCommand(robot.drive,
//                    Pose(24.0, 36.0),
//                    2.0,
//                    2.0.radians,
//                    stop = true,
//                    maxMoveSpeed = 0.8,
//                    maxTurnSpeed = 0.8,
//                    isHeadingLocked = true,
//                    headingLockAngle = 90.0.radians
//                ))
//                .pauseFor(2.0)
//                .andThen(
//                    GoToPointCommand(robot.drive,
//                    Pose(),
//                    2.0,
//                    2.0.radians,
//                    stop = true,
//                    maxMoveSpeed = 0.8,
//                    maxTurnSpeed = 0.8,
//                    isHeadingLocked = true,
//                    headingLockAngle = 0.0
//                )
//                )
//        )
        driver.dpadDown.onPress(GoToPointCommand(robot.drive, Pose(3.0, 30.0),2.0,
                    2.0.radians,
                    stop = true,
                    maxMoveSpeed = 0.8,
                    maxTurnSpeed = 0.8,
                    isHeadingLocked = true,
                    headingLockAngle = 90.0.radians
                ))
    }

    fun bindSpinnerBlue() {
        driver.dpadUp.onPress(
            DuckSpinnerCommand (0.25, robot.duckSpinner )
                .pauseFor(0.45)
                .andThen( DuckSpinnerCommand (0.35, robot.duckSpinner))
                .pauseFor(0.5)
                .andThen(DuckSpinnerCommand (0.85, robot.duckSpinner ))
                .pauseFor(0.4)
                .andThen(DuckSpinnerCommand (0.0, robot.duckSpinner ))
        )
    }

    fun bindArm() {
        driver.a.onPress(ArmCommands.TopPos(robot.arm))
    }




//    private fun bindDeposit() {
//        CommandScheduler.scheduleWatchdog({
//            driver.leftTrigger.isJustPressed && robot.hub == Hub.ALLIANCE_HIGH && robot.intake.isMineralIn
//             }
//    }
}
