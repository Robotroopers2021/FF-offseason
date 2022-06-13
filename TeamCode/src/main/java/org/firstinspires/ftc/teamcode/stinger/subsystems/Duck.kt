package org.firstinspires.ftc.teamcode.stinger.subsystems

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Duck (private val motor: KMotor) : DeviceSubsystem(){
    fun setPower(power : Double) {
        motor.power = power
    }
}

class DuckSpinnerCommand(private val power: Double, private val spinner: Duck) : InstantCmd({ spinner.setPower(power) }, spinner) {
}