package org.firstinspires.ftc.teamcode.stinger.subsystems

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.hardware.servo.KCRServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Cap(private val servo : KCRServo) : DeviceSubsystem(){
    fun forward() {
        servo.speed = 1.0
    }

    fun backward() {
        servo.speed = -1.0
    }

    fun noSpeed() {
        servo.speed = 0.0
}

}
class ForwardCap(private val cap: Cap) : SequentialGroup(
    InstantCmd(cap::forward, cap)
){
    init {
        addRequirements(cap)
    }
    override fun end() {
        NoCap(cap)
    }
}
class ReverseCap(private val cap: Cap) : SequentialGroup(
    InstantCmd(cap::backward, cap)
){
    init {
        addRequirements(cap)
    }
    override fun end() {
        NoCap(cap)
    }
}
class NoCap(cap : Cap) : InstantCmd(cap::noSpeed, cap)
