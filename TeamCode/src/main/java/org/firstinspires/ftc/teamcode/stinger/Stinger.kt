package org.firstinspires.ftc.teamcode.stinger

import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import org.firstinspires.ftc.teamcode.stinger.subsystems.*

class Stinger {

    val hardware = HardwareMap()

    val drive = KMecanumDrive(hardware.fl, hardware.bl, hardware.fr, hardware.br)

    val duckSpinner = Duck(hardware.duckSpinnerMotor)
    val intake = Intake(hardware.intakeMotor, hardware.loadingSensor)
    val arm = Arm(hardware.armMotor)
    val outtake = Outtake(hardware.outtakeServo)
    val lights = Lights(hardware.lightsServo)

    init {
        arm.motor.encoder.zero(-55.0).update()
        arm.motor.setPIDTarget(-55.0)
    }
}