package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinner
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import kotlin.math.pow



class DuckSequenceCommand (private val duckSpinner: DuckSpinner) : SequentialGroup(
//    DuckSpinnerCommand(0.25, duckSpinner ),
//    WaitCmd(0.45),
//    DuckSpinnerCommand (0.35, duckSpinner ),
//    WaitCmd(0.5),
//    DuckSpinnerCommand (0.85, duckSpinner ),
//    WaitCmd(0.4),
//    DuckSpinnerCommand (0.0, duckSpinner )
) {
    private var motionTimer = ElapsedTime()

    init {
        addRequirements(duckSpinner)
        addCommands(
            InstantCmd(motionTimer::reset),
            DuckSpinnerCommand(setDuckSpeed(motionTimer.milliseconds()), duckSpinner),
            WaitCmd(3.0),
            DuckSpinnerCommand(0.0, duckSpinner)
        )
    }
    override fun end() {
        duckSpinner.setPower(0.0)
    }
}


fun setDuckSpeed(time : Double) : Double{
    var result = 0.0
    if(time > 0 && time<=1) {
        result = (2/3) * (1.9).pow(time) - (2/4)
    } else if (time > 1.0 && time < 1.6 ) {
        result = 0.76
    }
    return result
}
