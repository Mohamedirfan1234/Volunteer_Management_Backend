#!/bin/bash

echo "🚀 Volunteer Backend Deployment Script"
echo "======================================"

# Check if git is clean
if [ -n "$(git status --porcelain)" ]; then
    echo "❌ Error: You have uncommitted changes. Please commit them first."
    echo "Run: git add . && git commit -m 'Your commit message'"
    exit 1
fi

echo "✅ Git repository is clean"

# Push to GitHub
echo "📤 Pushing to GitHub..."
git push origin main

echo "✅ Code pushed to GitHub"
echo ""
echo "🎯 Next Steps for Render Deployment:"
echo "===================================="
echo "1. Go to: https://dashboard.render.com"
echo "2. Click 'New +' → 'Web Service'"
echo "3. Connect your GitHub repository"
echo "4. Configure with these settings:"
echo "   - Name: volunteer-backend"
echo "   - Environment: Java"
echo "   - Build Command: ./mvnw clean package -DskipTests"
echo "   - Start Command: java -jar target/volunteer_backend-0.0.1-SNAPSHOT.jar"
echo "   - Health Check Path: /"
echo "5. Click 'Create Web Service'"
echo ""
echo "🔗 Your app will be available at: https://volunteer-backend.onrender.com"
echo ""
echo "📊 Monitor deployment in the Render dashboard" 