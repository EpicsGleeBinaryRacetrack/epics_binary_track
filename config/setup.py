
from cx_Freeze import setup, Executable

setup(
	name = "Binary Track Configuration Editor",
	version = "1.0",
	description = "Configuration editor for the GLEE Binary Racetrack",
	executables = [Executable("glee_config.pyw", base="WIN32GUI")]
	)
