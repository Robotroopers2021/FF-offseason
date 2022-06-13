package org.firstinspires.ftc.teamcode.stinger

import com.asiankoala.koawalib.control.FeedforwardConstants
import com.asiankoala.koawalib.control.MotorControlType
import com.asiankoala.koawalib.control.PIDConstants
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.motor.KMotorExConfig
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.servo.KCRServo
import com.asiankoala.koawalib.hardware.servo.KServo
import org.firstinspires.ftc.teamcode.stinger.subsystems.Lights
import org.firstinspires.ftc.teamcode.stinger.subsystems.Outtake

class HardwareMap {
    val fl = KMotor("FL").brake.reverse
    val bl = KMotor("BL").brake.reverse
    val br = KMotor("BR").brake
    val fr = KMotor("FR").brake

    val duckSpinnerMotor = KMotor("DuckL").brake
    val intakeMotor = KMotor("Intake").reverse

    val loadingSensor = KDistanceSensor("distanceSensor")

    val outtakeServo = KServo("Outtake").startAt(Outtake.openPosition)
    val lightsServo = KServo("blinkin").startAt(Lights.redPosition)
    val capServo = KCRServo("Cap")

    val armMotor = KMotorEx(
        KMotorExConfig(
            "Arm",
            184.0 / 90,
            false,
            MotorControlType.POSITION_PID,

            PIDConstants(
                kP = 0.015,
                kI = 0.0,
                kD = 0.00075
            ),
            FeedforwardConstants(
                kCos = 0.275
            ),
            0.0
        )
    ).brake as KMotorEx
}