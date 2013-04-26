
# Usage:
# python setup.py build
# This will call this build script and use it to set all appropriate 
# variables and compile it.  The build script will pull all 
# needed files and libraries from their locations and store them
# in one folder under build/.  For some reason, cx_Freeze ignores
# Tkinter's Tix library, so that has to be copied manually.
# Once it is copied manually, it works fine.  
# Make sure questions.txt is included or this build will fail.

from cx_Freeze import setup, Executable
import sys

base = None

# Make it target GUI.
if sys.platform == "win32":
	base = "WIN32GUI"

setup(
	name = "Binary Track Configuration Editor",
	version = "1.0",
	description = "Configuration editor for the GLEE Binary Racetrack",
	executables = [Executable(
        "glee_config.pyw", 
        base=base,
        compress=True,
        copyDependentFiles = True
        
    )],
    options = {
        "build_exe":{
            "includes" : [
                "tkinter.tix",
                "tkinter",
                "tkinter.ttk"
                #"tkinter.tix"
                ],
            "packages" : [
                "tkinter.tix"
                ],
            # Include questions.txt.  If questions.txt
            # is not in the same folder as setup.py, 
            # the b uild will fail.  Ensure it is in 
            # the folder before attempting to build.
            "include_files" : [
                "questions.txt"
            ]
        }
    }
)
