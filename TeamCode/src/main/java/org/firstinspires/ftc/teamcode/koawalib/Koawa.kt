package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.asiankoala.koawalib.control.*
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.motor.KMotorExConfig
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.roadrunner.drive.DriveConstants
import com.asiankoala.koawalib.roadrunner.drive.KMecanumDriveRR
import com.asiankoala.koawalib.roadrunner.drive.KTwoWheelOdometryRR
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import com.asiankoala.koawalib.subsystem.odometry.KEncoder
import com.asiankoala.koawalib.subsystem.odometry.KTwoWheelOdometry
import com.asiankoala.koawalib.subsystem.old.FeedforwardConstants
import com.asiankoala.koawalib.subsystem.old.MotorControlType
import com.asiankoala.koawalib.subsystem.old.MotorSubsystemConfig
import com.asiankoala.koawalib.subsystem.old.PIDConstants
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*
import kotlin.math.max
import kotlin.math.min


class Koawa {
    private val fl = KMotor("FL").brake
    private val bl = KMotor("BL").brake
    private val br = KMotor("BR").brake.reverse
    private val fr = KMotor("FR").brake.reverse
    private val duckSpinnerMotor = KMotor("Duck").brake
    private val slidesServo = KServo("Slides").startAt(Slides.zeroPosition)
    val loadingSensor = KDistanceSensor("dSensor")
    val intakeMotor = KMotor("Intake").reverse
    val clockingServo = KServo("Clocking").startAt(0.5)
//    val turretLimitSwitch = KLimitSwitch("limitSwitch")
    val turretMotor = KMotorEx(
    KMotorExConfig(
        "Turret",
        745.0/90.0,
        false,
        MotorControlType.MOTION_PROFILE,

    )
    ).reverse.brake
    val armMotor = KMotorEx(
        KMotorExConfig(
            "Arm",
            672.0/90.0,
            false,
            MotorControlType.MOTION_PROFILE,

            PIDConstants(
            kP = 0.09,
            kI = 0.0,//0.1
            kD = 0.0009 //0.00055
            ),
            FeedforwardConstants(
           kCos = 0.350
            ),
            positionEpsilon = 2.0, //0.6
        )).brake
//    val retractServo = KServo("Retract").startAt(0.7)

    val LeftEncoder = KEncoder(bl, 1892.3724, true).reversed.zero()
    val PerpEncoder = KEncoder(br, 1892.3724, true).reversed.zero()
    val armEncoder = KEncoder(armMotor, 672.0/90.0, false).zero(-52.0)
    val turretEncoder = KEncoder(turretMotor, 745.0/90.0, false).reversed.zero()

    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)
    val driveOdo = KTwoWheelOdometry(imu, LeftEncoder, PerpEncoder, 1.857, 1.0 )

    val drive = KMecanumDrive(fl, bl, fr, br)


//    val driveConstants = DriveConstants(
//        TICKS_PER_REV = 8192.0,
//        WHEEL_RADIUS = 1.37795276,
//        TRACK_WIDTH = 1.857
//    )

    val duckSpinner = DuckSpinner(duckSpinnerMotor)
    val intake = Intake(intakeMotor, loadingSensor)
    val slides = Slides(slidesServo)
    val clocking = Clocking(clockingServo)
//    val retract = Retract(retractServo)
    val arm = Arm(armMotor)
//    val arm = Arm(MotorSubsystemConfig(
//        armMotor,
//        armEncoder,
//        controlType = MotorControlType.POSITION_PID,
//        PIDConstants(
//            kP = 0.09,
//            kI = 0.0,//0.1
//            kD = 0.0009
//            //0.00055
//        ),
//       FeedforwardConstants(
//           kCos = 0.350
//       ),
//        positionEpsilon = 2.0, //0.6
//
//    ))
    val turret = Turret(
        MotorSubsystemConfig(
        turretMotor,
        turretEncoder,
            controlType = MotorControlType.POSITION_PID,
            PIDConstants(
                kP = 0.1,
                kI= 0.0,
                kD = 0.003
            ),
            FeedforwardConstants(
                kStatic = 0.003
            ),
            positionEpsilon = 2.0,
            homePositionToDisable = 2.0,
            lowerBound = -180.0,
            upperBound = 180.0
    )
    )
}