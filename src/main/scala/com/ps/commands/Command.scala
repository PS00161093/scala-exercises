package com.ps.commands

import com.ps.filesystem.State

trait Command {
    def apply(state: State): State
}

object Command {
    val CD = "cd"
    val LS = "ls"
    val RM = "rm"
    val PWD = "pwd"
    val MKDIR = "mkdir"
    val TOUCH = "touch"


    def emptyCommand: Command = new Command {
        override def apply(state: State): State = state
    }

    def incompleteCommand(name: String): Command = new Command {
        override def apply(state: State): State = state.setMessage(name + ": Incomplete command!")
    }

    def from(input: String): Command = {
        val tokens: Array[String] = input.split(" ")
        if (input.isEmpty || tokens.isEmpty) emptyCommand
        else if (MKDIR.equals(tokens(0))) {
            if (tokens.length < 2) incompleteCommand(MKDIR)
            else new MkDir(tokens(1))
        }
        else if (LS.equals(tokens(0))) new Ls
        else if (PWD.equals(tokens(0))) new Pwd
        else if (TOUCH.equals(tokens(0))) {
            if (tokens.length < 2) incompleteCommand(TOUCH)
            else new Touch(tokens(1))
        }
        else if (CD.equals(tokens(0))) {
            if (tokens.length < 2) incompleteCommand(CD)
            else new Cd(tokens(1))
        }
        else if (RM.equals(tokens(0))) {
            if (tokens.length < 2) incompleteCommand(RM)
            else new Rm(tokens(1))
        }
        else new UnknownCommand
    }
}