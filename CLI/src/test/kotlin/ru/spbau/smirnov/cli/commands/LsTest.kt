package ru.spbau.smirnov.cli.commands

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import ru.spbau.smirnov.cli.Environment
import ru.spbau.smirnov.cli.fillFiles
import java.nio.file.Paths

class LsTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun initFiles() {
            fillFiles()
        }
    }

    @Test
    fun `Should print current directory (no argument passed)`() {
        var env = Environment()
        env.currentDirectory = Paths.get("src").toAbsolutePath().toString()
        CommandTestUtils.runExecutorTest(
            Ls(env, listOf()),
            "",
            "main test ${System.lineSeparator()}",
            ""
        )
    }

    @Test
    fun `Should print a directory` () {
        CommandTestUtils.runExecutorTest(
            Ls(Environment(), listOf("src")),
            "src",
            "main test ${System.lineSeparator()}",
            ""
        )
    }
}