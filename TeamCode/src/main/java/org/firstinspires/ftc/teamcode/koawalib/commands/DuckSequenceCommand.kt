package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinner
import org.firstinspires.ftc.teamcode.koawalib.subsystem.DuckSpinnerCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Turret

class DuckSequenceCommand (val duckSpinner: DuckSpinner) : SequentialGroup(
    DuckSpinnerCommand(0.25, duckSpinner ),
    WaitCmd(0.45),
    DuckSpinnerCommand (0.35, duckSpinner ),
    WaitCmd(0.5),
    DuckSpinnerCommand (0.85, duckSpinner ),
    WaitCmd(0.4),
    DuckSpinnerCommand (0.0, duckSpinner )
) {
    init {
        addRequirements(duckSpinner)
    }

    override fun end() {
        duckSpinner.setPower(0.0)
    }
}