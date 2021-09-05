package com.ps.commands
import com.ps.files.{DirEntry, File}
import com.ps.filesystem.State

class Touch(name: String) extends CreateEntry(name) {
    override def createSpecificEntry(state: State): DirEntry = File.empty(state.wd.path, name)
}
