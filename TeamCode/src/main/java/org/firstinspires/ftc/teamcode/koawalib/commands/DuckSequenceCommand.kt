package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.LoopCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinner
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import kotlin.math.pow



class DuckSequenceCommand (private val duckSpinner: DuckSpinner) : SequentialGroup(
    InstantCmd(motionTimer::reset),
    WaitCmd(3.0).
        deadlineWith(
            LoopCmd( {duckSpinner.setPower(setDuckSpeed(motionTimer.seconds()))},  duckSpinner)),
    DuckSpinnerCommand(0.0, duckSpinner)
) {

    init {
        addRequirements(duckSpinner)
    }

    override fun end() {
        duckSpinner.setPower(0.0)
    }
}

private var motionTimer = ElapsedTime()

fun setDuckSpeed(time : Double) : Double{
    var result = 0.0
    if(time > 0.0 && time <= 0.9) {
        result = ((time.pow(4.0)) / (4.0)) + 0.325
    } else if (time > 0.9 && time < 1.4  ) {
        result = 0.85
    }
    return result
}
