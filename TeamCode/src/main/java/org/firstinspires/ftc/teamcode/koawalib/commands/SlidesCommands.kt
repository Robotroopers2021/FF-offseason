package org.firstinspires.ftc.teamcode.koawalib.commands

import com.asiankoala.koawalib.command.commands.InstantCmd
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides

class SlidesCommands {
    class SlidesAllianceCommand(slides: Slides) : InstantCmd(slides::alliance, slides)
    class SlidesSharedExtCommand(slides : Slides) : InstantCmd(slides::sharedExt, slides)
    class SlidesHomeCommand(slides: Slides) : InstantCmd(slides::home, slides)
    class SlidesIntakeCommand(slides : Slides) : InstantCmd(slides::intakePos, slides)
    class SlidesSharedCommand(slides : Slides) : InstantCmd(slides::shared, slides)
}