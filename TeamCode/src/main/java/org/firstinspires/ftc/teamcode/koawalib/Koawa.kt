package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.MotionProfileController
import com.asiankoala.koawalib.control.PIDExController
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import com.asiankoala.koawalib.subsystem.odometry.Encoder
import com.asiankoala.koawalib.subsystem.odometry.TwoWheelOdometry
import com.asiankoala.koawalib.subsystem.old.MotorControlType
import com.asiankoala.koawalib.subsystem.old.MotorSubsystemConfig
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*
import kotlin.math.max
import kotlin.math.min


class Koawa {
    private val fl = KMotor("FL").brake.reverse
    private val bl = KMotor("BL").brake.reverse
    private val fr = KMotor("FR").brake
    private val br = KMotor("BR").brake
    private val duckSpinnerMotor = KMotor("Duck").brake
    private val slidesServo = KServo("Slides").startAt(0.9)
    val loadingSensor = KDistanceSensor("dSensor")
    val intakeMotor = KMotor("Intake")
    //    private val turretLimitSwitch = KLimitSwitch(TURRET_LIMIT_SWITCH_NAME)
    val turretMotor = KMotor("turret").brake
    val armMotor = KMotor("Arm").brake

    val LeftEncoder = Encoder(bl, 1892.3724, isRevEncoder = true).reversed.zero()
    val PerpEncoder = Encoder(br, 1892.3724, isRevEncoder = true).reversed.zero()
    val armEncoder = Encoder(armMotor, 213.5/90.0,false).reversed.zero()
    val turretEncoder = Encoder(turretMotor, 0.0, isRevEncoder = false).zero()

    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)

    val drive = KMecanumOdoDrive(fl, bl, fr, br, TwoWheelOdometry(imu, LeftEncoder, PerpEncoder, 1.857, 1.0 ), true)

    val duckSpinner = DuckSpinner(duckSpinnerMotor)
    val intake = Intake(intakeMotor, loadingSensor)
    val slides = Slides(slidesServo)
    val arm = Arm(MotorSubsystemConfig(
        armMotor,
        armEncoder,
        controlType = MotorControlType.POSITION_PID,
        kP = 0.0,
        kI = 0.0,
        kD = 0.0,
        positionEpsilon = 0.0,
    ))
//    val turret = Turret(turretMotor, turretLimitSwitch, TurretEncoder)
}

    var hub = Hub.ALLIANCE_HIGH

    fun incHub() {
        hub = Hub.values()[min(hub.ordinal + 1, Hub.values().size - 1)]
    }

    fun decHub() {
        hub = Hub.values()[max(hub.ordinal - 1, 0)]
    }
