package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.OpenLoopController
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.intake.IntakeConfig
import com.asiankoala.koawalib.subsystem.intake.KDistanceSensorIntake
import com.asiankoala.koawalib.subsystem.odometry.OdoConfig
import com.asiankoala.koawalib.subsystem.odometry.ThreeWheelOdometry
import kotlin.math.max
import kotlin.math.min


class Koawa {
    @Config
    companion object HardwareConstants {
        const val INTAKE_NAME = "Intake"
        const val LOADING_SENSOR_NAME = "dSensor"
    }
    private val fl = KMotor("FL").reverse.brake
    private val bl = KMotor("BL").reverse.brake
    private val fr = KMotor("FR").brake
    private val br = KMotor("BR").brake
//    private val intakeMotor = KMotorEx(INTAKE_NAME, OpenLoopController())
    val loadingSensor = KDistanceSensor(LOADING_SENSOR_NAME)
    private val intakeMotor = KMotor(INTAKE_NAME)

    private val odoLeft = fr
    private val odoRight = bl.reverseEncoder
    private val odoAux = br

    val drive = KMecanumOdoDrive(fl, bl, fr, br, ThreeWheelOdometry(OdoConfig(
        1892.3724, 0.7, 5.90551,
        odoLeft, odoRight, odoAux
    )), true)

    val intake = Intake(intakeMotor, loadingSensor, IntakeConfig(0.75), 25.0)

    var hub = Hub.ALLIANCE_HIGH

    fun incHub() {
        hub = Hub.values()[min(hub.ordinal + 1, Hub.values().size - 1)]
    }

    fun decHub() {
        hub = Hub.values()[max(hub.ordinal - 1, 0)]
    }
}