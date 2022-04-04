
package robotuprising.ftc2021.auto.drive.opmode

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry
import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.kinematics.Kinematics.calculateMotorFeedforward
import com.acmerobotics.roadrunner.profile.MotionProfile
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator.generateSimpleMotionProfile
import com.acmerobotics.roadrunner.profile.MotionState
import com.acmerobotics.roadrunner.util.NanoClock
import com.asiankoala.koawalib.command.CommandScheduler
import com.asiankoala.koawalib.hardware.KDevice
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.hardware.sensor.AxesSigns
import com.asiankoala.koawalib.hardware.sensor.KIMU
import com.asiankoala.koawalib.roadrunner.drive.DriveConstants
import com.asiankoala.koawalib.roadrunner.drive.KMecanumDriveRR
import com.asiankoala.koawalib.roadrunner.drive.TwoWheelOdometryRR
import com.asiankoala.koawalib.subsystem.odometry.KEncoder
import com.asiankoala.koawalib.util.Logger
import com.asiankoala.koawalib.util.LoggerConfig
import com.qualcomm.hardware.lynx.LynxModule
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.RobotLog
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder
import org.firstinspires.ftc.teamcode.koawalib.Koawa
import org.firstinspires.ftc.teamcode.util.Extensions.d
import java.util.*

 /*
 * This routine is designed to tune the open-loop feedforward coefficients. Although it may seem unnecessary,
 * tuning these coefficients is just as important as the positional parameters. Like the other
 * manual tuning routines, this op mode relies heavily upon the dashboard. To access the dashboard,
 * connect your computer to the RC's WiFi network. In your browser, navigate to
 * https://192.168.49.1:8080/dash if you're using the RC phone or https://192.168.43.1:8080/dash if
 * you are using the Control Hub. Once you've successfully connected, start the program, and your
 * robot will begin moving forward and backward according to a motion profile. Your job is to graph
 * the velocity errors over time and adjust the feedforward coefficients. Once you've found a
 * satisfactory set of gains, add them to the appropriate fields in the DriveConstants.java file.
 *
 * Pressing Y/Δ (Xbox/PS4) will pause the tuning process and enter driver override, allowing the
 * user to reset the position of the bot in the event that it drifts off the path.
 * Pressing B/O (Xbox/PS4) will cede control back to the tuning process.
 */
// @Config
@Disabled
@Autonomous(group = "drive")
class ManualFeedforwardTuner : LinearOpMode() {
    private val dashboard: FtcDashboard = FtcDashboard.getInstance()
    private lateinit var koawa : Koawa


    internal enum class Mode {
        DRIVER_MODE, TUNING_MODE
    }

    private var mode: Mode? = null
    override fun runOpMode() {
        Logger.config = LoggerConfig(
            false,
            false,
            false,
            false,
            1
        )

        KDevice.hardwareMap = this.hardwareMap
        val hubs = hardwareMap.getAll(LynxModule::class.java)
        hubs.forEach { it.bulkCachingMode = LynxModule.BulkCachingMode.MANUAL }

        telemetry = MultipleTelemetry(telemetry, dashboard.getTelemetry())

        val fl = KMotor("FL").brake
        val bl = KMotor("BL").brake
        val br = KMotor("BR").reverse.brake
        val fr = KMotor("FR").reverse.brake
        val imu = KIMU("imu", AxesOrder.XYZ, AxesSigns.NPN)
        val LeftEncoder = KEncoder(bl, 1892.3724, true).reversed.zero()
        val PerpEncoder = KEncoder(br, 1892.3724, true).reversed.zero()
        val odo = TwoWheelOdometryRR(imu, LeftEncoder, PerpEncoder, 1.857, 1.0 )

        val drive = KMecanumDriveRR(driveConstants, fr, bl, fr, br, odo, PIDCoefficients(0.0, 0.0, 0.0), PIDCoefficients(0.0, 0.0, 0.0))

//        val drive = KMecanumDriveRR()
//        drive = SampleMecanumDrive(hardwareMap)

        mode = Mode.DRIVER_MODE

        val clock: NanoClock = NanoClock.system()

        telemetry.addLine("Ready!")
        telemetry.update()
        telemetry.clearAll()

        waitForStart()

        if (isStopRequested()) return

        var movingForwards = true
        var activeProfile: MotionProfile = generateProfile(true)
        var profileStart: Double = clock.seconds()

        while (!isStopRequested()) {
            telemetry.addData("mode", mode)
            when (mode) {
                Mode.TUNING_MODE -> {
                    if (gamepad1.y) {
                        mode = Mode.DRIVER_MODE
                    }

                    // calculate and set the motor power
                    val profileTime: Double = clock.seconds() - profileStart

                    if (profileTime > activeProfile.duration()) {
                        // generate a new profile
                        movingForwards = !movingForwards
                        activeProfile = generateProfile(movingForwards)
                        profileStart = clock.seconds()
                    }

                    val motionState: MotionState = activeProfile.get(profileTime)
                    val targetPower: Double = calculateMotorFeedforward(motionState.v, motionState.a,
                        driveConstants.kV, driveConstants.ka, driveConstants.kStatic)

                    drive.setDrivePower(Pose2d(targetPower, 0.0, 0.0))
                    drive.updatePoseEstimate()

                    val poseVelo = drive.poseVelocity!!
//                    val poseVelo: Pose2d = Objects.requireNonNull(drive!!.poseVelocity, "poseVelocity() must not be null. Ensure that the getWheelVelocities() method has been overridden in your localizer.")!!
                    val currentVelo: Double = poseVelo.x

                    // update telemetry
                    telemetry.addData("targetVelocity", motionState.v)
                    telemetry.addData("measuredVelocity", currentVelo)
                    telemetry.addData("error", motionState.v - currentVelo)
                }

                Mode.DRIVER_MODE -> {
                    if (gamepad1.b) {
                        mode = Mode.TUNING_MODE
                        movingForwards = true
                        activeProfile = generateProfile(movingForwards)
                        profileStart = clock.seconds()
                    }

                    drive.setDrivePower(
                        Pose2d(
                            -gamepad1.left_stick_y.d,
                            -gamepad1.left_stick_x.d,
                            -gamepad1.right_stick_x.d
                        )
                    )
                }
            }

            telemetry.update()
        }
    }

    companion object {
        var DISTANCE = 72.0 // in
        val driveConstants = DriveConstants(
            TICKS_PER_REV = 8192.0,
            WHEEL_RADIUS = 1.37795276,
            TRACK_WIDTH = 1.857
        )


        private fun generateProfile(movingForward: Boolean): MotionProfile {
            val start = MotionState(if (movingForward) 0.0 else DISTANCE, 0.0, 0.0, 0.0)
            val goal = MotionState(if (movingForward) DISTANCE else 0.0, 0.0, 0.0, 0.0)

            return generateSimpleMotionProfile(start, goal, driveConstants.MAX_VEL, driveConstants.MAX_ACCEL)
        }
    }
}
