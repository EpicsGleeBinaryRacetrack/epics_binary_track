
from cx_Freeze import setup, Executable
import sys

base = None

if sys.platform == "win32":
	base = "WIN32GUI"

setup(
	name = "Binary Track Configuration Editor",
	version = "1.0",
	description = "Configuration editor for the GLEE Binary Racetrack",
	executables = [Executable(
        "glee_config.pyw", 
        base=base,
        #base="WIN32GUI",
        compress=True,
        copyDependentFiles = True
        #appendScriptToExe = True
        
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
            "include_files" : [
                "questions.txt"
            ]
        }
    }
)
