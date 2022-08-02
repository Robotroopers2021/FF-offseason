package org.firstinspires.ftc.teamcode.perseus

import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry
import org.firstinspires.ftc.teamcode.perseus.subsystem.*

class Koawa {

    val hardware = Hardware()

    val driveOdo = KThreeWheelOdometry(hardware.leftEncoder, hardware.rightEncoder, hardware.perpEncoder, 10.0, 53.0)

    val drive = KMecanumOdoDrive(hardware.fl, hardware.bl, hardware.fr, hardware.br, driveOdo, true)

//    val driveConstants = DriveConstants(
//        TICKS_PER_REV = 8192.0,
//        WHEEL_RADIUS = 1.37795276,
//        TRACK_WIDTH = 1.857
//    )

    val duckSpinner = DuckSpinner(hardware.duckSpinnerMotor)
    val intake = Intake(hardware.intakeMotor, hardware.loadingSensor)
    val slides = Slides(hardware.slidesServo)
    val clocking = Clocking(hardware.clockingServo)
    val arm = Arm(hardware.armMotor)
    val turret = Turret(hardware.turretMotor)
    val door = Door(hardware.doorServo)

    init {
        arm.motor.encoder.zero(-55.0).update()
        arm.motor.setPIDTarget(-55.0)
        turret.motor.encoder.zero(0.0).reversed.update()
        turret.motor.setPIDTarget(0.0)
    }
}