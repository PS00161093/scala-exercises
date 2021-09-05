package com.ps.commands

import com.ps.files.{DirEntry, Directory}
import com.ps.filesystem.State

case class MkDir(name: String) extends CreateEntry(name) {
    override def createSpecificEntry(state: State): DirEntry = Directory.empty(state.wd.path, name)
}

