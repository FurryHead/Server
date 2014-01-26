@echo off
title Hyperion Compiler
cd src
dir /s /B *.java >..\srcs.txt
cd ..
"C:\Program Files\Java\jdk1.7.0_45\bin\javac.exe" -d bin/ -cp "lib\*"; @srcs.txt
pause