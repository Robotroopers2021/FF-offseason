package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.subsystem.intake.KIntake

class Intake(motor: KMotor, private val sensor: KDistanceSensor) : KIntake(motor, 0.75) {
    companion object IntakeConstants {
        const val SENSOR_THRESHOLD = 57.0
    }
    private var isReadingSensor = false
    private var lastRead = Double.POSITIVE_INFINITY

    fun startReading() {
        isReadingSensor = true
        lastRead = Double.POSITIVE_INFINITY
    }

    fun stopReading() {
        isReadingSensor = false
        lastRead = Double.POSITIVE_INFINITY
    }

    fun slowIntake() {
        setIntakeSpeed(1.0)
    }

    fun outtake() {
        setIntakeSpeed(-0.40)
    }

    val hasMineral get() = sensor.lastRead < SENSOR_THRESHOLD

    override fun periodic() {
        if(isReadingSensor) {
            lastRead = sensor.lastRead
        }
    }
}
