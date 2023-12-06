@echo
set "currentFolder=%cd%"
echo Current folder: %currentFolder%
echo Running TestNG tests...
java -cp "lib\*;bin" org.testng.TestNG testng.xml  // Replace with your test suite XML file path
pause