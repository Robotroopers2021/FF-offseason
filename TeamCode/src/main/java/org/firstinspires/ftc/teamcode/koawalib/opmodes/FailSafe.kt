package org.firstinspires.ftc.teamcode.koawalib.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.koawalib.ScuffedKoawa

@TeleOp
class FailSafe : KOpMode() {
    private lateinit var koawa : ScuffedKoawa
    override fun mInit() {
        Logger.config = LoggerConfig(isLogging = true, isPrinting = false, isLoggingTelemetry = false, isDebugging = false, maxErrorCount = 1)
        koawa = ScuffedKoawa()

        koawa.drive.setDefaultCommand(
            MecanumCmd(
                koawa.drive,
                driver.leftStick,
                driver.rightStick,
                1.0,1.0,0.65,
                xScalar = 0.75, yScalar = 0.75
            )
        )

        gunner.dpadUp.onPress(InstantCmd({koawa.turret.setPower(0.25)}, koawa.turret))
        gunner.dpadDown.onPress(InstantCmd({koawa.turret.setPower(-0.25)}, koawa.turret))
        gunner.a.onPress(InstantCmd({koawa.turret.setPower(0.0)}, koawa.turret))

        gunner.leftBumper.onPress(InstantCmd({koawa.arm.setPower(-0.25)}, koawa.arm))
        gunner.rightBumper.onPress(InstantCmd({koawa.arm.setPower(0.0)}, koawa.arm))
    }


    override fun mStart() {
    }

    override fun mLoop() {
    }
}
