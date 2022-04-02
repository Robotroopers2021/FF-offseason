package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.command.commands.InfiniteCommand
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import com.asiankoala.koawalib.util.Logger
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.commands.IntakeCommands
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand

@TeleOp
class KoawaRedOp : CommandOpMode() {
    private lateinit var robot: Koawa
    override fun mInit() {
        robot = Koawa()
        bindDrive()
        bindIntake()
        bindSpinnerRed()
//        bindDeposit()
    }

    override fun mLoop() {
        Logger.addTelemetryData("dSensor", robot.loadingSensor.invokeDouble())
    }

    fun bindDrive() {
        robot.drive.setDefaultCommand(MecanumDriveCommand(robot.drive, driver.leftStick.yInverted.xInverted, driver.rightStick.xInverted))
    }

    fun bindIntake() {
//        driver.leftTrigger.whenPressed(IntakeCommands.IntakeSequenceCommand(robot.intake))
        driver.leftTrigger.whenPressed(IntakeCommands.IntakeOn(robot.intake))
        driver.rightTrigger.whenPressed(IntakeCommands.IntakeReverse(robot.intake))
        InfiniteCommand({ robot.intake.turnOff() }, robot.intake)
//        driver.leftTrigger.whenPressed(IntakeCommands.setIntakeSpeed(robot.intake))
    }


    fun bindSpinnerRed() {
        driver.dpadUp.onPress(
            DuckSpinnerCommand (-0.25, robot.duckSpinner )
                .pauseFor(0.45)
                .andThen( DuckSpinnerCommand (-0.35, robot.duckSpinner))
                .pauseFor(0.5)
                .andThen(DuckSpinnerCommand (-0.85, robot.duckSpinner ))
                .pauseFor(0.4)
                .andThen(DuckSpinnerCommand (0.0, robot.duckSpinner ))
        )
    }


//    private fun bindDeposit() {
//        CommandScheduler.scheduleWatchdog({
//            driver.leftTrigger.isJustPressed && robot.hub == Hub.ALLIANCE_HIGH && robot.intake.isMineralIn
//             }
//    }
}
