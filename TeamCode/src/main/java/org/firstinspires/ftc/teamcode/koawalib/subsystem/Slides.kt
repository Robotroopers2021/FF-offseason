package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides.SlideConstants.alliancePosition

class Slides (private val servo: KServo) : DeviceSubsystem() {

    companion object SlideConstants {
        const val alliancePosition = 0.9
        const val sharedPosition = 0.0
        const val zeroPosition = 0.0
    }
    fun setSlidePosition (position: Double) {
        servo.position = position
    }
}

class SlidesCommand(private val position: Double, private val slides: Slides) : InstantCommand( {slides.setSlidePosition(position)}, slides)
