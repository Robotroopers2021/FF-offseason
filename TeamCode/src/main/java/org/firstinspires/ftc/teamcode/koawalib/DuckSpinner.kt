package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class DuckSpinner (private val motor: KMotor ) : DeviceSubsystem(){
     fun setPower(power : Double) {
         motor.setSpeed(power)
     }
}

class DuckSpinnerCommand(private val power: Double, private val spinner: DuckSpinner) : InstantCommand({ spinner.setPower(power) }, spinner)