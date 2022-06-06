package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.control.FeedforwardConstants
import com.asiankoala.koawalib.control.MotorControlType
import com.asiankoala.koawalib.control.PIDConstants
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.motor.KMotorExConfig
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.odometry.KEncoder
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Door
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides

class Hardware {
    val fl = KMotor("FL").brake
    val bl = KMotor("BL").brake
    val br = KMotor("BR").brake.reverse
    val fr = KMotor("FR").brake.reverse

    val duckSpinnerMotor = KMotor("Duck").brake
    val intakeMotor = KMotor("Intake").reverse

    val slidesServo = KServo("Slides").startAt(Slides.zeroPosition)
    val clockingServo = KServo("Clocking").startAt(0.5)
    val doorServo = KServo("Door").startAt(Door.lockPosition)

    val loadingSensor = KDistanceSensor("dSensor")
    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)
    //    val turretLimitSwitch = KLimitSwitch("limitSwitch")

    val rightEncoder = KEncoder(br, 1892.3724, true).zero()
    val leftEncoder = KEncoder(fl, 1892.3724, true).reversed.zero()
    val perpEncoder = KEncoder(fr, 1892.3724, true).reversed.zero()

    val rightRetract = KServo("Right Retract")
    val leftRetract = KServo("Left Retract")
    val perpRetract = KServo("Perp Retract")

    val turretMotor = KMotorEx(
        KMotorExConfig(
            "Turret",
            745.0/90.0,
            false,
            MotorControlType.POSITION_PID,

            PIDConstants(
                kP = 0.1,//0.1
                kI= 0.0,
                kD = 0.003//0.003
            ),
            FeedforwardConstants(
                kStatic = 0.003//0.003
            ),
            positionEpsilon = 2.0,//2.0
            homePositionToDisable = 2.0,//2.0
            lowerBound = -180.0,//-180.0
            upperBound = 180.0,//180.0
//            maxVelocity = 70.0,
//            maxAcceleration = 50.0
        )
    ).reverse.brake as KMotorEx

    val armMotor = KMotorEx(
        KMotorExConfig(
            "Arm",
            672.0/90.0,
            false,
            MotorControlType.POSITION_PID,

            PIDConstants(
                kP = 0.09,//0.09
                kI = 0.0,//0.1
                kD = 0.0009 //0.00055
            ),
            FeedforwardConstants(
                kCos = 0.1
            ),
            positionEpsilon = 2.0, //0.6
//            maxVelocity = 120.0,
//            maxAcceleration = 100.0
        )
    ).brake as KMotorEx
}