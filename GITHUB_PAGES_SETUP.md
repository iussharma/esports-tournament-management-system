# GitHub Repository Creation & GitHub Pages Setup

## ✅ Step 1: Create GitHub Repository

1. **Go to GitHub.com and log in**
   - Visit: https://github.com/new

2. **Create a new repository with these details:**
   - Repository name: `esports-tournament-management-system`
   - Description: "Firebase-powered eSports Tournament Management System with GitHub Pages"
   - Visibility: **Public** (required for GitHub Pages)
   - Initialize repository: **Skip** (we already have local repo)
   - Click **Create Repository**

3. **After creation, GitHub will show you push commands**
   - Copy them or follow the next step

## ✅ Step 2: Push Code to GitHub

After creating the repository on GitHub, run these commands in your terminal:

```powershell
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"
git branch -M main
git push -u origin main
```

Wait for the push to complete. You'll see something like:
```
Counting objects: XX, done.
Compressing objects: XX%, done.
Writing objects: XX%, done.
...
To https://github.com/ius-sharma/esports-tournament-management-system.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

## ✅ Step 3: Enable GitHub Pages

1. **Go to your GitHub repository**
   - URL: https://github.com/ius-sharma/esports-tournament-management-system

2. **Go to Settings**
   - Click **Settings** tab (top-right)

3. **Enable GitHub Pages**
   - Left sidebar → scroll to **Pages**
   - Under "Build and deployment":
     - Source: **Deploy from a branch**
     - Branch: **main** / **(root)** folder → Change to **main** / **docs** folder
     - Click **Save**

4. **Wait for deployment** (2-5 minutes)
   - Green checkmark will appear
   - Your site URL will be shown: `https://ius-sharma.github.io/esports-tournament-management-system/`

## ✅ Verification

After enabling GitHub Pages:

1. Wait 2-5 minutes for GitHub to build and deploy
2. Visit your new site: 
   ```
   https://ius-sharma.github.io/esports-tournament-management-system/
   ```

3. You should see:
   - ODDSOCEAN homepage
   - "Get Started" button
   - Features section

## 🐛 Troubleshooting GitHub Pages

**Error: "Your site is ready to be published"**
- Check that you selected the correct branch (`main`) and folder (`docs`)
- Wait 5 minutes and refresh

**Error: "404 Not Found"**
- Make sure `docs/index.html` exists and is pushed to GitHub
- Verify your GitHub Pages settings

**Error: "Cannot find module"**
- Check that all script paths in HTML use relative paths (not `/docs/...`)

## ⏭️ Next Steps

1. **Create the GitHub repository** (Step 1)
2. **Push code to GitHub** (Step 2) 
3. **Enable GitHub Pages** (Step 3)
4. **Test your live site** (Verification)
5. **Share the public URL** with your team

Once you've completed these steps, reply with:
**"GitHub Pages is live"**

And I'll verify everything and give you the final working URL!
