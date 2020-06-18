package ru.spbau.smirnov.cli.commands

import ru.spbau.smirnov.cli.Environment
import ru.spbau.smirnov.cli.executor.Streams

import java.nio.file.Paths
import java.nio.file.Files

/**
 * Cd command.
 *
 * Changes current directory to the given one.
 * If `arguments` is empty changes current directory to the home directory.
 */
class Cd (private val environment: Environment, arguments: List<String>) : Executable(arguments) {
    override fun execute(streams: Streams): Int {
        if (arguments.isEmpty()) {
            environment.currentDirectory = System.getProperty("user.home")
            return 0
        }

        if (arguments.size > 1) {
             streams.errorStream.println("Error in cd: too many arguments")
            return 1
        }

        val path = Paths.get(environment.currentDirectory, arguments[0])
        if (!Files.isDirectory(path)) {
            streams.errorStream.println("Error in cd: argument is not a directory")
            return 1
        }
        environment.currentDirectory = path.toAbsolutePath().toString()

        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other == null || other !is Cd) {
            return false
        }

        return environment === other.environment && arguments == other.arguments
    }
}
