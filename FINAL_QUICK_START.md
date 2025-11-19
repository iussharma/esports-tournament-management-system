# 🚀 QUICK START GUIDE - GET YOUR SITE LIVE IN 20 MINUTES

**Your Project:** esports-tournament-management-system  
**Your GitHub Username:** ius-sharma  
**Firebase Project:** esports-tournament-1b0eb

---

## 🎯 FINAL CHECKLIST

Do these 5 things in order. Takes ~20 minutes total.

---

### ✅ ACTION 1: Create Firestore Collections (5-10 min)

**Why:** Your app needs data to display

**Go to:** https://console.firebase.google.com/
- Select: **esports-tournament-1b0eb** project
- Click: **Firestore Database** (left menu)
- Click: **Create Database**
  - Mode: **Start in test mode**
  - Region: **asia-south1**
  - Click: **Create**

**Create 5 Collections:**

#### Collection 1: users
- Click: **+ Start collection**
- Name: `users`
- Click: **Auto-ID**
- Add fields:
  - `email` (string): `admin@esports.com`
  - `username` (string): `AYUSH1234`
  - `role` (string): `admin`
- Click: **Save**

#### Collection 2: teams
- Click: **+ Start collection**
- Name: `teams`
- Click: **Auto-ID** (create 3 documents)

**Document 1:**
```
teamName (string): Phoenix Rising
description (string): Legendary esports team
totalPoints (number): 450
wins (number): 12
losses (number): 3
```

**Document 2:**
```
teamName: Dragon Force
description: Fast and aggressive
totalPoints: 380
wins: 10
losses: 5
```

**Document 3:**
```
teamName: Apex Predators
description: Strategic team
totalPoints: 320
wins: 8
losses: 7
```

#### Collection 3: players
- Name: `players`
- Create 5 documents with **Auto-ID**

**Player 1:**
```
playerName: AlexPro
role: Rifler
kd_ratio: 1.45
skill_rating: 92
```

**Player 2:**
```
playerName: ShadowHunter
role: AWPer
kd_ratio: 2.1
skill_rating: 95
```

**Player 3:**
```
playerName: ThunderStrike
role: Support
kd_ratio: 0.95
skill_rating: 85
```

**Player 4:**
```
playerName: IceQueen
role: Entry Fragger
kd_ratio: 1.65
skill_rating: 89
```

**Player 5:**
```
playerName: NovaKing
role: IGL
kd_ratio: 1.2
skill_rating: 88
```

#### Collection 4: tournaments
- Name: `tournaments`
- Create 2 documents

**Tournament 1:**
```
tournamentName: Winter Championship 2025
description: Grand tournament with prize pool
status: ongoing
prizePool: 50000
startDate: 2025-11-20
endDate: 2025-12-20
```

**Tournament 2:**
```
tournamentName: Spring Qualifiers 2026
description: Qualifying matches
status: upcoming
prizePool: 25000
startDate: 2026-03-01
endDate: 2026-04-15
```

#### Collection 5: matches
- Name: `matches`
- Create 2 documents

**Match 1:**
```
team1: Phoenix Rising
team2: Dragon Force
team1Score: 2
team2Score: 1
status: completed
matchDate: 2025-11-18
```

**Match 2:**
```
team1: Dragon Force
team2: Apex Predators
team1Score: 0
team2Score: 0
status: upcoming
matchDate: 2025-11-22
```

✅ **DONE WITH STEP 1**

---

### ✅ ACTION 2: Create GitHub Repository (2 min)

**Go to:** https://github.com/new

**Fill in:**
- Repository name: `esports-tournament-management-system`
- Visibility: **Public** ← Important!
- Description: Firebase-powered eSports Tournament Management System with GitHub Pages
- Skip "Initialize this repository"
- Click: **Create repository**

**You'll see a setup page. IGNORE IT (we already have code locally)**

✅ **DONE WITH STEP 2**

---

### ✅ ACTION 3: Push Your Code to GitHub (1 min)

**Open PowerShell and run:**

```powershell
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"
git branch -M main
git push -u origin main
```

**Wait for it to finish. You'll see:**
```
Counting objects: 27, done.
...
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

✅ **DONE WITH STEP 3**

---

### ✅ ACTION 4: Enable GitHub Pages (5 min)

**Go to:** https://github.com/ius-sharma/esports-tournament-management-system

**Click:** Settings (top right)

**Left menu → Pages**

**Fill in:**
- Source: Deploy from a branch
- Branch: **main**
- Folder: **/docs** ← This is important!
- Click: **Save**

**Wait 2-5 minutes. You'll see a green checkmark when done.**

Your site URL will be:
```
https://ius-sharma.github.io/esports-tournament-management-system/
```

✅ **DONE WITH STEP 4**

---

### ✅ ACTION 5: Test Your Live Site (1 min)

**Wait 5 minutes**, then visit:
```
https://ius-sharma.github.io/esports-tournament-management-system/
```

**Verify:**
- ✅ Title says "ODDSOCEAN ESPORTS"
- ✅ "Get Started" button visible
- ✅ Navigation links work
- ✅ Can click "Admin Login"

**If it shows 404:**
- Wait another 2 minutes (GitHub is still building)
- Clear your browser cache (Ctrl+Shift+Delete)
- Refresh the page

✅ **DONE WITH STEP 5**

---

## 🎉 YOU'RE DONE!

Your site is now **LIVE and ACCESSIBLE** from anywhere!

**Share this URL with your team:**
```
https://ius-sharma.github.io/esports-tournament-management-system/
```

---

## 🔐 Default Admin Login

**Username:** `AYUSH1234`  
**Password:** `Ayush@2908`

---

## 📱 Access from Other Devices

Your site is now public! Access it from:
- ✅ Mobile phones (iOS/Android)
- ✅ Tablets
- ✅ Laptops
- ✅ Desktops
- ✅ Any device with internet

Just visit: `https://ius-sharma.github.io/esports-tournament-management-system/`

---

## 🔧 Make Changes Later

When you make changes to your code:

```powershell
cd "c:\Users\sharm\OneDrive\Desktop\SEM - 3\AJT\Project\esports-tournament-management-system"
git add .
git commit -m "Description of changes"
git push origin main
```

Your site updates automatically!

---

## 📊 Final URL Information

**Live Site:** `https://ius-sharma.github.io/esports-tournament-management-system/`

This URL will:
- ✅ Work on any device with internet
- ✅ Update automatically when you push to GitHub
- ✅ Be accessible 24/7
- ✅ Load fast (GitHub CDN)
- ✅ Show real data from Firestore

---

## ❓ Need Help?

**"Site shows 404"**
- Wait 5 minutes and refresh
- Check GitHub Pages is enabled (Settings > Pages)
- Verify folder is `/docs`

**"Login doesn't work"**
- Check Firestore collections are created
- Check Firebase credentials in `docs/js/firebase-config.js`
- Open browser console (F12) and check for errors

**"Data not showing"**
- Make sure you created all 5 Firestore collections
- Check collection names match exactly
- Wait 30 seconds for data to load

---

## ✅ COMPLETE!

**FINAL LINK FOR YOUR TEAM:**
```
https://ius-sharma.github.io/esports-tournament-management-system/
```

You can now access your eSports Tournament Management System from any device anywhere in the world! 🚀

---

**Status:** ✅ LIVE AND ACCESSIBLE  
**Date Deployed:** November 19, 2025  
**Backend:** Firebase (Firestore + Auth)  
**Hosting:** GitHub Pages
