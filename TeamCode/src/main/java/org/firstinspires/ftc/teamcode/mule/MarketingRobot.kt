package org.firstinspires.ftc.teamcode.mule

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.ElapsedTime

@TeleOp
class MarketingRobot : OpMode() {

    lateinit var fl: DcMotor
    lateinit var fr: DcMotor
    lateinit var bl: DcMotor
    lateinit var br: DcMotor

    lateinit var arm: DcMotor

    private var motionTimer = ElapsedTime()

    var drive = 0.0
    var strafe = 0.0
    var rotate = 0.0

    fun driveForward() {
        fl.power = 0.5
        fr.power = 0.5
        bl.power = 0.5
        br.power = 0.5
    }

    fun driveBackwards() {
        fl.power = -0.5
        fr.power = -0.5
        bl.power = -0.5
        br.power = -0.5
    }

    fun removeError() {
        fl.power = -0.005
        fr.power = 0.005
        bl.power = -0.005
        br.power = 0.005
    }


    override fun init() {
        fl = hardwareMap.get(DcMotor::class.java, "FL")
        fr = hardwareMap.get(DcMotor::class.java, "FR")
        bl = hardwareMap.get(DcMotor::class.java, "BL")
        br = hardwareMap.get(DcMotor::class.java, "BR")
        arm = hardwareMap.dcMotor["Arm"]

        fl.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        fr.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        bl.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        br.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        arm.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        fl.direction = DcMotorSimple.Direction.REVERSE
        bl.direction = DcMotorSimple.Direction.REVERSE
    }

    override fun loop() {
        if (motionTimer.seconds() < 0.1) {
            removeError()
        }
        if (motionTimer.seconds() < 2.9 && motionTimer.seconds() > 0.1) {
            driveForward()
        }
        if (motionTimer.seconds() > 3.0 && motionTimer.seconds() < 3.1 ) {
            removeError()
        }
        if (motionTimer.seconds() > 3.1) {
            driveBackwards()
        }
        if (motionTimer.seconds() > 6.0) {
            motionTimer.reset()
        }
    }
}