package org.firstinspires.ftc.teamcode.stinger.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.sensor.KDistanceSensor
import com.asiankoala.koawalib.subsystem.intake.KIntake

class Intake(motor: KMotor, private val sensor: KDistanceSensor) : KIntake(motor, 1.0) {
    companion object IntakeConstants {
        const val SENSOR_THRESHOLD = 30.0
    }

    private var isReadingSensor = false
    private var lastRead = Double.POSITIVE_INFINITY

    fun startReading() {
        isReadingSensor = true
        lastRead = sensor.lastRead
    }

    fun stopReading() {
        isReadingSensor = false
        lastRead = sensor.lastRead
    }


    val hasMineral get() = sensor.lastRead < SENSOR_THRESHOLD

    override fun periodic() {
        if(isReadingSensor){
            sensor.periodic()
        }
    }
}