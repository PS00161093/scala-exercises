package com.ps.commands

import com.ps.filesystem.State

class Pwd extends Command {

    override def apply(state: State): State = state.setMessage(state.wd.path)
}
