package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.OpenLoopController
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import com.asiankoala.koawalib.subsystem.intake.KDistanceSensorIntake
import com.asiankoala.koawalib.subsystem.odometry.OdoConfig
import com.asiankoala.koawalib.subsystem.odometry.ThreeWheelOdometry
import com.asiankoala.koawalib.subsystem.odometry.TwoWheelOdometry
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import kotlin.math.max
import kotlin.math.min


class Koawa {
    @Config
    companion object HardwareConstants {
        const val INTAKE_NAME = "Intake"
        const val LOADING_SENSOR_NAME = "dSensor"
    }
    private val fl = KMotor("FL").brake.reverse
    private val bl = KMotor("BL").brake.reverse
    private val fr = KMotor("FR").brake
    private val br = KMotor("BR").brake

    private val duckSpinnerMotor = KMotor("Duck").brake
    private val slidesServo = KServo("Slides")
    val loadingSensor = KDistanceSensor(LOADING_SENSOR_NAME)
    val intakeMotor = KMotor(INTAKE_NAME)

    private val odoLeft = bl.zero().reverseEncoder
    private val odoAux = br.zero().reverseEncoder
    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)

    val drive = KMecanumOdoDrive(fl, bl, fr, br, TwoWheelOdometry(OdoConfig(
        1892.3724, 1.857, 1.0, odoLeft, odoLeft, odoAux
    ), imu), true)

    val duckSpinner = DuckSpinner(duckSpinnerMotor)
    val intake = Intake(intakeMotor, loadingSensor, IntakeConfig(0.75), 25.0)
    val slides = Slides(slidesServo)
}

    var hub = Hub.ALLIANCE_HIGH

    fun incHub() {
        hub = Hub.values()[min(hub.ordinal + 1, Hub.values().size - 1)]
    }

    fun decHub() {
        hub = Hub.values()[max(hub.ordinal - 1, 0)]
    }
