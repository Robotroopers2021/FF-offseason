package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCommand
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides

class SlidesCommands {
    class SlidesAllianceCommand(slides: Slides) : InstantCommand(slides::alliance, slides)
    class SlidesSharedCommand(slides : Slides) : InstantCommand(slides::shared, slides)
    class SlidesHomeCommand(slides: Slides) : InstantCommand(slides::home, slides)
    class SlidesIntakeCommand(slides : Slides) : InstantCommand(slides::intakePos, slides)
}