package org.firstinspires.ftc.teamcode.perseus

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.stateMachine.StateMachineBuilder
import org.firstinspires.ftc.teamcode.util.GamepadUtil.dpad_up_pressed
import org.firstinspires.ftc.teamcode.util.GamepadUtil.left_trigger_pressed
import org.firstinspires.ftc.teamcode.util.GamepadUtil.right_trigger_pressed
import org.firstinspires.ftc.teamcode.util.math.MathUtil


@Config
@TeleOp
class PerseusOp : OpMode(){
    //drive train motors
    lateinit var fl : DcMotor
    lateinit var fr : DcMotor
    lateinit var bl : DcMotor
    lateinit var br : DcMotor
    //intake-outtake system/duck spinner motors
    lateinit var intake : DcMotor
    lateinit var turret : DcMotor
    lateinit var arm : DcMotor
    lateinit var duck : DcMotor
    lateinit var slides :Servo
    lateinit var clocking: Servo
    //mecanum drivetrain variables
    var drive = 0.0
    var strafe = 0.0
    var rotate = 0.0

    private var motionTimer = ElapsedTime()

    fun driveControl() {
        drive = MathUtil.cubicScaling(0.75, -gamepad1.left_stick_y.toDouble()) * 0.85
        strafe = MathUtil.cubicScaling(0.75, gamepad1.left_stick_x.toDouble()) * 0.85
        rotate = -gamepad1.right_stick_x.toDouble() * 0.65
        fl.power = drive + strafe + rotate
        fr.power = drive - strafe - rotate
        bl.power = drive - strafe + rotate
        br.power = drive + strafe - rotate
    }

    fun intakeControl() {
        if(gamepad1.left_trigger_pressed) {
            intake.power = -0.75
        } else if(gamepad1.right_trigger_pressed) {
            intake.power = 0.25
        } else if(!gamepad1.right_trigger_pressed && !gamepad1.left_trigger_pressed) {
            intake.power = 0.0
        }
    }

    fun armControl() {
        if(gamepad1.left_bumper) {
            arm.power = 0.50
        }
        if (gamepad1.right_bumper) {
            arm.power = -0.30
        }
        if (!gamepad1.left_bumper && !gamepad1.right_bumper) {
            arm.power = 0.0
        }
    }

    fun turretControl() {
        if (gamepad1.y) {
            turret.power = 0.5
        }
        if (gamepad1.a) {
            turret.power = -0.5
        }
        if (!gamepad1.y && !gamepad1.a) {
            turret.power = 0.0
        }
    }

    fun slidesControl() {
        if (gamepad1.x) {
            slides.position =0.0
        }
        if (gamepad1.b) {
            slides.position = 0.9
        }
    }


    private enum class DuckSpinnerStates {
        RUN_SLOW,
        RUN_FAST,
        YEET,
        STOP
    }

    private val duckSpinnerSequence = StateMachineBuilder<DuckSpinnerStates>()
        .state(DuckSpinnerStates.RUN_SLOW)
        .onEnter {
            duck.power = 0.25
        }
        .transitionTimed(0.5)
        .state(DuckSpinnerStates.RUN_FAST)
        .onEnter {
            duck.power = 0.35
        }
        .transitionTimed(0.5)
        .state(DuckSpinnerStates.YEET)
        .onEnter{
            duck.power = 0.85
        }
        .transitionTimed(0.4)
        .state(DuckSpinnerStates.STOP)
        .onEnter {
            duck.power = 0.0
        }

        .build()

    private fun duckSpinnerSequenceStart() {
        if (gamepad1.dpad_up_pressed && !duckSpinnerSequence.running) {
            duckSpinnerSequence.start()
        }
        if (!gamepad1.dpad_up_pressed && duckSpinnerSequence.running) {
            duckSpinnerSequence.stop()
            duckSpinnerSequence.reset()
            motionTimer.reset()
        }
        if (!gamepad1.dpad_up_pressed) {
            duck.power = 0.0
        }
        if (duckSpinnerSequence.running && gamepad1.dpad_up_pressed) {
            duckSpinnerSequence.update()
        }

    }
    override fun init() {
        fl = hardwareMap.get(DcMotor::class.java, "FL")
        fr = hardwareMap.get(DcMotor::class.java, "FR")
        bl = hardwareMap.get(DcMotor::class.java, "BL")
        br = hardwareMap.get(DcMotor::class.java, "BR")

        arm = hardwareMap.get(DcMotor::class.java, "Arm")
        turret = hardwareMap.get(DcMotor::class.java, "Turret")
        duck = hardwareMap.get(DcMotor::class.java, "Duck")
        intake = hardwareMap.get(DcMotor::class.java, "Intake")

        slides = hardwareMap.get(Servo::class.java, "Slides")
        clocking = hardwareMap.get(Servo::class.java, "Clocking")

        fl.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        fr.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        bl.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        br.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        arm.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        turret.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        duck.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        intake.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        fl.direction = DcMotorSimple.Direction.REVERSE
        bl.direction = DcMotorSimple.Direction.REVERSE

        slides.direction = Servo.Direction.REVERSE

        slides.position = 0.0

        telemetry.addData("STATUS", "Initialized")
        telemetry.speak("System dot Print el en Odometry")
        telemetry.update()
    }

    override fun loop() {
        driveControl()
        intakeControl()
        armControl()
        turretControl()
        duckSpinnerSequenceStart()
        slidesControl()
    }
}