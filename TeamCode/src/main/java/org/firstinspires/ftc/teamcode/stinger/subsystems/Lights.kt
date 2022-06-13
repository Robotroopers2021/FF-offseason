package org.firstinspires.ftc.teamcode.stinger.subsystems

import android.provider.CalendarContract
import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import com.qualcomm.hardware.rev.RevBlinkinLedDriver

class Lights(private val servo : KServo) : DeviceSubsystem() {

    companion object LightConstants {
        const val redPosition = 0.61
        const val greenPosition = 0.77
    }
    fun red() {
        servo.position = redPosition
    }

    fun green() {
        servo.position = greenPosition
    }
}

class RedCmd(lights : Lights) : InstantCmd(lights::red, lights)
class GreenCmd(lights : Lights) : InstantCmd(lights::green, lights)