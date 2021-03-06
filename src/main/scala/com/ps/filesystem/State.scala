package com.ps.filesystem

import com.ps.files.Directory
import com.ps.filesystem.State.SHELL_TOKEN

class State(val root: Directory, val wd: Directory, val output: String) {

    def show(): Unit = {
        println(output)
        print(SHELL_TOKEN)
    }

    def setMessage(message: String): State = State(root, wd, message)

}

object State {

    val SHELL_TOKEN = "$ "

    def apply(root: Directory, wd: Directory, output: String = ""): State = new State(root, wd, output)
}
