# 💻 COMMAND REFERENCE - Copy & Paste Ready

All the commands you need for deployment (20 minutes)

---

## STEP 1: Firestore Collections Setup

**Location:** Firebase Console  
**Time:** 5-10 minutes  
**Guide:** FIRESTORE_SETUP.md

No commands needed - use Firebase Console UI

---

## STEP 2: GitHub Repository Creation

**Location:** GitHub.com  
**Time:** 2 minutes  
**Guide:** GITHUB_PAGES_SETUP.md

No commands needed - use GitHub web interface

**Steps:**
1. Go to: https://github.com/new
2. Name: esports-tournament-management-system
3. Visibility: PUBLIC
4. Create Repository

---

## STEP 3: Push Code to GitHub

**Location:** PowerShell  
**Time:** 1 minute  
**Command:**

```powershell
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"
git branch -M main
git push -u origin main
```

**Expected Output:**
```
Counting objects: 27, done.
...
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

---

## STEP 4: Enable GitHub Pages

**Location:** GitHub Repository Settings  
**Time:** 3 minutes  
**Guide:** GITHUB_PAGES_SETUP.md

**Steps:**
1. Go to: https://github.com/ius-sharma/esports-tournament-management-system
2. Click: Settings (top right)
3. Left menu: Pages
4. Source: Deploy from a branch
5. Branch: main
6. Folder: /docs
7. Click: Save
8. Wait 2-5 minutes for green checkmark

---

## STEP 5: Test Live Site

**Location:** Web Browser  
**Time:** 1 minute  
**URL:** https://ius-sharma.github.io/esports-tournament-management-system/

**Verification Checklist:**
- [ ] Page loads with "ODDSOCEAN ESPORTS" title
- [ ] "Get Started" button visible
- [ ] Navigation links work
- [ ] Click "Admin Login" → login page loads
- [ ] Try login with AYUSH1234 / Ayush@2908

---

## Additional Commands (Optional)

### Make Changes Later

```powershell
# Make edits to files locally, then:
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"
git add .
git commit -m "Your description of changes"
git push origin main
```

Site updates automatically (1-2 minutes)

---

## Troubleshooting Commands

### Check Git Status
```powershell
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"
git status
```

### Check Git Logs
```powershell
git log --oneline -5
```

### Check Remote URL
```powershell
git remote -v
```

### Test Local Server (Optional)
```powershell
cd docs/
python -m http.server 8000
```
Then visit: http://localhost:8000

---

## Firebase URLs

- Firebase Console: https://console.firebase.google.com/
- Project: https://console.firebase.google.com/project/esports-tournament-1b0eb/

---

## GitHub URLs

- GitHub Profile: https://github.com/ius-sharma
- Repository: https://github.com/ius-sharma/esports-tournament-management-system
- Repository Settings: https://github.com/ius-sharma/esports-tournament-management-system/settings
- Pages Settings: https://github.com/ius-sharma/esports-tournament-management-system/settings/pages

---

## Your Live Site URLs

- GitHub Pages: https://ius-sharma.github.io/esports-tournament-management-system/
- Repository: https://github.com/ius-sharma/esports-tournament-management-system
- Firebase Project: esports-tournament-1b0eb

---

## Important Files

```
docs/js/firebase-config.js          ← Your Firebase credentials (already configured)
docs/index.html                      ← Homepage
docs/login.html                      ← Login page
docs/dashboard.html                  ← Dashboard
```

---

## Quick Links

| Item | Link |
|------|------|
| Firebase Console | https://console.firebase.google.com/ |
| GitHub New Repo | https://github.com/new |
| Your Repository | https://github.com/ius-sharma/esports-tournament-management-system |
| Your Live Site (after deployment) | https://ius-sharma.github.io/esports-tournament-management-system/ |

---

## Helpful Commands Cheatsheet

```powershell
# Check current directory
pwd

# Change to project directory
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"

# Check git status
git status

# Stage all changes
git add .

# Commit changes
git commit -m "Your message here"

# Push to GitHub
git push origin main

# Pull latest from GitHub
git pull origin main

# View last 5 commits
git log --oneline -5

# Check remote URL
git remote -v
```

---

**Version:** 1.0  
**Last Updated:** November 19, 2025  
**Status:** ✅ Ready to use
