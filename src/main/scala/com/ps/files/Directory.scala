package com.ps.files

import com.ps.filesystem.FilesystemException

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

    override def asDirectory: Directory = this

    def getAllFoldersInPath: List[String] = path.substring(1).split(Directory.SEPARATOR).toList.filter(x => x.nonEmpty)

    def addEntry(newEntry: DirEntry): Directory = new Directory(parentPath, name, contents :+ newEntry)

    def hasEntry(name: String): Boolean = findEntry(name) != null

    def findEntry(entryName: String): DirEntry = {
        @tailrec
        def fileEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
            if (contentList.isEmpty) null
            else if (contentList.head.name.equals(name)) contentList.head
            else fileEntryHelper(name, contentList.tail)
        }

        fileEntryHelper(entryName, contents)
    }

    def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
        new Directory(parentPath, name, contents.filter(e => !e.name.equals(entryName)) :+ newEntry)

    def isRoot: Boolean = parentPath.isEmpty

    def findDescendant(path: List[String]): Directory = {
        if (path.isEmpty) this
        else findEntry(path.head).asDirectory.findDescendant(path.tail)
    }

    def findDescendant(relativePath: String): Directory =
        if (relativePath.isEmpty) this else findDescendant(relativePath.split(Directory.SEPARATOR).toList)

    def removeEntry(entryName: String): Directory =
        if (!hasEntry(entryName)) this else new Directory(parentPath, name, contents.filter(x => !x.name.equals(entryName)))

    override def asFile: File = throw new FilesystemException("A directory can't be converted to a file!")

    override def isDirectory: Boolean = true

    override def isFile: Boolean = false
}

object Directory {

    val SEPARATOR = "/"

    val ROOT_PATH = "/"

    def ROOT: Directory = Directory.empty("", "")

    def empty(parentPath: String, name: String): Directory = new Directory(parentPath, name, List())
}