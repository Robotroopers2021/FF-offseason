package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.OdoConfig
import com.asiankoala.koawalib.subsystem.odometry.Odometry
import com.asiankoala.koawalib.subsystem.odometry.ThreeWheelOdometry
import com.asiankoala.koawalib.subsystem.odometry.TwoWheelOdometry
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder

class Koawa {
    private val fl = KMotor("FL").reverse.brake
    private val bl = KMotor("BL").reverse.brake
    private val fr = KMotor("FR").brake
    private val br = KMotor("BR").brake

    private val odoLeft = fr
    private val odoRight = bl.reverseEncoder
    private val odoAux = br
    val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)
    val drive = KMecanumOdoDrive(fl, bl, fr, br, TwoWheelOdometry(OdoConfig(
        1892.3724, 0.7, 5.90551,
        odoLeft, odoRight, odoAux
    ), imu), true)
}