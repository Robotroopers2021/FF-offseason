package org.firstinspires.ftc.teamcode.koawalib

import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import org.firstinspires.ftc.teamcode.koawalib.subsystem.ScuffedArm
import org.firstinspires.ftc.teamcode.koawalib.subsystem.ScuffedTurret

class ScuffedKoawa {
    private val fl = KMotor("FL").brake
    private val bl = KMotor("BL").brake
    private val br = KMotor("BR").brake.reverse
    private val fr = KMotor("FR").brake.reverse
    val turretMotor = KMotor("Turret").reverse.brake
    val turret = ScuffedTurret(turretMotor)
    val armMotor = KMotor("Arm").brake
    val arm = ScuffedArm(armMotor)

    val drive = KMecanumDrive(fl, bl, fr, br)
}