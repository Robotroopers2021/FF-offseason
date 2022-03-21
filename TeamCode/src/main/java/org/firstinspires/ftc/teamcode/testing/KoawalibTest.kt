package org.firstinspires.ftc.teamcode.testing

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.OdoConfig

class KoawalibTest {
    @Config
    companion object HardwareConstants {
        const val FL_NAME = "FL"
        const val BL_NAME = "BL"
        const val FR_NAME = "FR"
        const val BR_NAME = "BR"
        @JvmField var turn_scalar = 0.5
        @JvmField var aux_tracker = 1.0
    }
    private val flMotor = KMotor(FL_NAME).reverse.brake.zero()
    private val blMotor = KMotor(BL_NAME).reverse.brake.zero()
    private val frMotor = KMotor(FR_NAME).brake.zero()
    private val brMotor = KMotor(BR_NAME).brake.zero()

    private val odoLeftEncoder = frMotor
    private val odoRightEncoder = blMotor
    private val odoAuxEncoder = brMotor

    val drive = KMecanumOdoDrive(
            flMotor, blMotor, frMotor, brMotor,
            OdoConfig(
                    1892.3724, turn_scalar, aux_tracker,
                    odoLeftEncoder, odoRightEncoder, odoAuxEncoder
            ) ,true
    )
}