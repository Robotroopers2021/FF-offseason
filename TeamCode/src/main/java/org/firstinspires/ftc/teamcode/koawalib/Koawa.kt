package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.odometry.KTwoWheelOdometry
import org.firstinspires.ftc.teamcode.koawalib.subsystem.*

class Koawa {

    val hardware = Hardware()

    val driveOdo = KTwoWheelOdometry(hardware.imu, hardware.leftEncoder, hardware.perpEncoder, 1.857, 1.0 )

    val drive = KMecanumDrive(hardware.fl, hardware.bl, hardware.fr, hardware.br)

//    val driveConstants = DriveConstants(
//        TICKS_PER_REV = 8192.0,
//        WHEEL_RADIUS = 1.37795276,
//        TRACK_WIDTH = 1.857
//    )

    val duckSpinner = DuckSpinner(hardware.duckSpinnerMotor)
    val intake = Intake(hardware.intakeMotor, hardware.loadingSensor)
    val slides = Slides(hardware.slidesServo)
    val clocking = Clocking(hardware.clockingServo)
//    val retract = Retract(retractServo)
    val arm = Arm(hardware.armMotor)
    val turret = Turret(hardware.turretMotor)

    init {
        arm.motor.encoder.zero(-55.0).update()
        turret.motor.encoder.zero(0.0).update()
    }
}