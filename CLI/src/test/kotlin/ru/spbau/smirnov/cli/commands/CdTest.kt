package ru.spbau.smirnov.cli.commands

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import ru.spbau.smirnov.cli.Environment
import ru.spbau.smirnov.cli.fillFiles
import java.nio.file.Paths

class CdTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun initFiles() {
            fillFiles()
        }
    }

    @Test
    fun `Should change directory with no argument`() {
        val env = Environment()
        CommandTestUtils.runExecutorTest(
             Cd(env, listOf()),
            "",
            "",
            ""
        )
        assertEquals(System.getProperty("user.home"), env.currentDirectory)
    }

    @Test
    fun `Should change directory`() {
        val env = Environment()
        CommandTestUtils.runExecutorTest(
            Cd(env, listOf("src")),
            "src",
            "",
            ""
        )
        assertEquals(Paths.get("src").toAbsolutePath().toString(), env.currentDirectory)
    }

    @Test
    fun `Should give an error about non-directory argument`() {
        CommandTestUtils.runExecutorTest(
            Cd(Environment(), listOf("README.md")),
            "README.md",
            "",
            "error"
        )
    }

    @Test
    fun `Should give an error about non-existing argument`() {
        CommandTestUtils.runExecutorTest(
            Cd(Environment(), listOf("X")),
            "X",
            "",
            "error"
        )
    }

}