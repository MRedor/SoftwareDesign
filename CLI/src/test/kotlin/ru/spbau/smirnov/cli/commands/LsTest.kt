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
        CommandTestUtils.runExecutorTestWithList(
            Ls(Environment(), listOf("src")),
            "src",
            listOf(
            "main test ${System.lineSeparator()}",
                "test main ${System.lineSeparator()}"
            ),
            ""
        )
    }


    @Test
    fun `Should print many directories` () {
        val first = "src: ${System.lineSeparator()}main test ${System.lineSeparator()}${System.lineSeparator()}"
        val firstReversed = "src: ${System.lineSeparator()}test main ${System.lineSeparator()}${System.lineSeparator()}"
        val second = "src/test/resources/testLs: ${System.lineSeparator()}${System.lineSeparator()}${System.lineSeparator()}"

        CommandTestUtils.runExecutorTestWithList(
            Ls(Environment(), listOf("src", "src/test/resources/testLs")),
            "src src/test/resources/testLs",
            listOf(
                first + second,
                firstReversed + second
            ),
            ""
        )
    }

    @Test
    fun `Should give an error about non-directory argument`() {
        CommandTestUtils.runExecutorTest(
            Ls(Environment(), listOf("README.md")),
            "README.md",
            "",
            "error"
        )
    }

    @Test
    fun `Should give an error about non-existing argument`() {
        CommandTestUtils.runExecutorTest(
            Ls(Environment(), listOf("X")),
            "X",
            "",
            "error"
        )
    }
}