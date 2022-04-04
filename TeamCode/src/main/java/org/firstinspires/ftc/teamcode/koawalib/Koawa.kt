package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.asiankoala.koawalib.control.MotionProfileController
import com.asiankoala.koawalib.control.PIDExController
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.roadrunner.drive.DriveConstants
import com.asiankoala.koawalib.roadrunner.drive.KMecanumDriveRR
import com.asiankoala.koawalib.roadrunner.drive.TwoWheelOdometryRR
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import com.asiankoala.koawalib.subsystem.odometry.Encoder
import com.asiankoala.koawalib.subsystem.odometry.TwoWheelOdometry
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
    private val br = KMotor("BR").reverse.brake
    private val fr = KMotor("FR").reverse.brake
    private val duckSpinnerMotor = KMotor("Duck").brake
    private val slidesServo = KServo("Slides").startAt(Slides.zeroPosition)
    val loadingSensor = KDistanceSensor("dSensor")
    val intakeMotor = KMotor("Intake").reverse
    val clockingServo = KServo("Clocking").startAt(0.5)
//    val turretLimitSwitch = KLimitSwitch("limitSwitch")
    val turretMotor = KMotor("Turret").reverse.float
    val armMotor = KMotor("Arm").brake

    val LeftEncoder = Encoder(bl, 1892.3724, true).reversed.zero()
    val PerpEncoder = Encoder(br, 1892.3724, true).reversed.zero()
    val armEncoder = Encoder(armMotor, 592.0/90.0, false).reversed.zero()
    val turretEncoder = Encoder(turretMotor, 745.0/90.0, false).reversed.zero()

    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)
    val odo = TwoWheelOdometryRR(imu, LeftEncoder, PerpEncoder, 1.857, 1.0 )

//    val drive = KMecanumOdoDrive(fl, bl, fr, br, odo, true)

    val driveConstants = DriveConstants(
        TICKS_PER_REV = 8192.0,
        WHEEL_RADIUS = 1.37795276,
        TRACK_WIDTH = 1.857
    )

    val rrDrive = KMecanumDriveRR(driveConstants, fr, bl, fr, br, odo, PIDCoefficients(0.0, 0.0, 0.0), PIDCoefficients(0.0, 0.0, 0.0))

    val duckSpinner = DuckSpinner(duckSpinnerMotor)
    val intake = Intake(intakeMotor, loadingSensor)
    val slides = Slides(slidesServo)
    val clocking = Clocking(clockingServo)
    val arm = Arm(MotorSubsystemConfig(
        armMotor,
        armEncoder,
        controlType = MotorControlType.POSITION_PID,
        PIDConstants(
            kP = 0.1,   //0.18
            kI= 0.006,   //0.001
            kD = 0.00055 //0.0004
        ),
       FeedforwardConstants(
           kCos = 0.275
       ),
        positionEpsilon = 2.0, //0.6
    ))
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
            homePositionToDisable = 2.0
    )
    )
}