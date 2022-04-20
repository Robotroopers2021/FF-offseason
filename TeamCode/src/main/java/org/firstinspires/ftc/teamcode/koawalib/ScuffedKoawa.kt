package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.hardware.motor.KMotor
import org.firstinspires.ftc.teamcode.koawalib.subsystem.ScuffedTurret

class ScuffedKoawa {
    val turretMotor = KMotor("Turret").reverse.brake
    val turret = ScuffedTurret(turretMotor)
}