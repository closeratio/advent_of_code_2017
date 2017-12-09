package com.aoc2017.day8

class BadInstructionFormatException(line: String) : Exception("Bad instruction: \"$line\"")