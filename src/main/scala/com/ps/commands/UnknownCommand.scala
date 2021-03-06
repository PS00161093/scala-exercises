package com.ps.commands

import com.ps.filesystem.State

class UnknownCommand extends Command {

    override def apply(state: State): State = state.setMessage("Command not found!")
}
