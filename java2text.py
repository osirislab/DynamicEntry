# Licence: Public Domain
#
# A dumb little script to encode Java code (from a Java file) as a Java string to
# be created as a method dynamically.
#
# Usage:
# $ python java2text.py <Somefile.java>
#
import sys, string

if(len(sys.argv) != 2):
  sys.exit(1)
  
fd = open(sys.argv[1], 'r')
lines = fd.readlines();
for i in range(len(lines)):
  lines[i] = string.replace(lines[i], "\\", "\\\\");
  lines[i] = string.replace(lines[i], "\n", "\\n");
  lines[i] = string.replace(lines[i], "\"", "\\\"");
  lines[i] = string.replace(lines[i], "  ", "\\t");
  lines[i] = "\"" + lines[i] + "\" +"
  print lines[i]
