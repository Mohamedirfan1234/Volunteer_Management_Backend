@REM @echo off
@REM echo 🚀 Volunteer Backend Deployment Script
@REM echo ======================================
@REM
@REM REM Check if git is clean
@REM git diff --quiet
@REM if %errorlevel% neq 0 (
@REM     echo ❌ Error: You have uncommitted changes. Please commit them first.
@REM     echo Run: git add . ^&^& git commit -m "Your commit message"
@REM     pause
@REM     exit /b 1
@REM )
@REM
@REM echo ✅ Git repository is clean
@REM
@REM REM Push to GitHub
@REM echo 📤 Pushing to GitHub...
@REM git push origin main
@REM
@REM echo ✅ Code pushed to GitHub
@REM echo.
@REM echo 🎯 Next Steps for Render Deployment:
@REM echo ====================================
@REM echo 1. Go to: https://dashboard.render.com
@REM echo 2. Click 'New +' → 'Web Service'
@REM echo 3. Connect your GitHub repository
@REM echo 4. Configure with these settings:
@REM echo    - Name: volunteer-backend
@REM echo    - Environment: Java
@REM echo    - Build Command: ./mvnw clean package -DskipTests
@REM echo    - Start Command: java -jar target/volunteer_backend-0.0.1-SNAPSHOT.jar
@REM echo    - Health Check Path: /
@REM echo 5. Click 'Create Web Service'
@REM echo.
@REM echo 🔗 Your app will be available at: https://volunteer-backend.onrender.com
@REM echo.
@REM echo 📊 Monitor deployment in the Render dashboard
@REM pause