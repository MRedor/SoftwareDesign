package ru.spbau.smirnov.cli.commands

import ru.spbau.smirnov.cli.Environment
import ru.spbau.smirnov.cli.executor.Streams
import java.io.DataOutputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class Ls(private val environment: Environment, arguments: List<String>) : Executable(arguments) {
    override fun execute(streams: Streams): Int {
        val output = DataOutputStream(streams.outputStream)
        if (arguments.isEmpty()) {
            print(Paths.get(environment.currentDirectory), streams)
            output.writeBytes(System.lineSeparator())
        } else {
            for (arg in arguments) {
                val path = Paths.get(environment.currentDirectory, arg)
                if (!Files.isDirectory(path)) {
                    streams.errorStream.println("Error in ls: argument is not a directory")
                    return 1
                }

                if (arguments.size > 1) {
                    output.writeBytes(arg + ": ${System.lineSeparator()}")
                }
                print(path, streams)
                output.writeBytes(System.lineSeparator())
                if (arguments.size > 1) {
                    output.writeBytes(System.lineSeparator())
                }
            }
        }
        return 0
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

    fun print(path: Path, streams: Streams) {
        val output = DataOutputStream(streams.outputStream)

        try {
            path.toFile().list().forEach {
                output.writeBytes("$it ")
            }
        } catch (e: IOException) {
            streams.errorStream.println("Error in ls.${System.lineSeparator()}${e.message}")
        }
    }
}