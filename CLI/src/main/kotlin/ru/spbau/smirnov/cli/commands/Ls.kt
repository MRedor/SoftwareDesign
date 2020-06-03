package ru.spbau.smirnov.cli.commands

import ru.spbau.smirnov.cli.Environment
import ru.spbau.smirnov.cli.executor.Streams

class Ls(private val environment: Environment, arguments: List<String>) : Executable(arguments) {
    override fun execute(streams: Streams): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null || other !is Ls) {
            return false
        }

        return arguments == other.arguments
    }
}
