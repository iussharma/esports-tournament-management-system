# Deployment Guide - GitHub Pages & Firebase Hosting

## 🚀 Quick Start (Choose One)

### Option 1: Firebase Hosting (Recommended)

**Pros:**
- Free tier (generous)
- Custom domain support
- Automatic HTTPS
- Firebase suite integration
- CDN global distribution

**Steps:**

```bash
# 1. Install Firebase CLI
npm install -g firebase-tools

# 2. Login to Firebase
firebase login

# 3. Initialize Firebase in your project root
firebase init hosting

# When prompted:
# "What do you want to use as your public directory?" → Enter: docs
# "Configure as a single-page app (rewrite all urls to index.html)?" → Yes
# "Set up automatic builds and deploys with GitHub?" → Yes (optional)

# 4. Deploy
firebase deploy

# 5. View your site at:
# https://your-project-id.web.app
```

### Option 2: GitHub Pages

**Pros:**
- Integrated with GitHub
- No additional accounts needed
- Tight GitHub integration

**Steps:**

```bash
# 1. Ensure docs/ folder exists in repository root

# 2. In GitHub:
#    Settings > Pages
#    Source: Branch "main", Folder "docs"
#    Save

# 3. Your site is at:
# https://your-username.github.io/esports-tournament-management-system/
```

---

## 📋 Pre-Deployment Checklist

### Step 1: Verify Project Structure

```bash
cd esports-tournament-management-system/
ls -la docs/

# Should see:
# - index.html
# - login.html
# - dashboard.html
# - team-list.html, team-form.html, team-view.html
# - player-list.html, player-form.html
# - tournament-list.html, tournament-form.html
# - match-list.html, match-form.html
# - css/gaming.css
# - js/firebase-config.js, auth.js, api.js, render.js
```

### Step 2: Configure Firebase

1. **Open:** `docs/js/firebase-config.js`

2. **Replace credentials:**
```javascript
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "YOUR_PROJECT.firebaseapp.com",
  projectId: "YOUR_PROJECT_ID",
  storageBucket: "YOUR_PROJECT.appspot.com",
  messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
  appId: "YOUR_APP_ID",
  measurementId: "YOUR_MEASUREMENT_ID"
};
```

3. **Get values:**
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Select your project
   - Settings (gear icon) > Project Settings
   - Scroll to "Your apps"
   - Copy values from Web app config

### Step 3: Verify Firestore Setup

1. **Create Firestore Database**
   - Firebase Console > Firestore Database
   - Click "Create Database"
   - Start in production mode
   - Choose region (closest to you)

2. **Create Collections:**
   - `teams`
   - `players`
   - `tournaments`
   - `matches`
   - `users` (optional, for admin roles)

3. **Add Sample Data** (or use demo mode)
   - See `FIRESTORE_MAPPING.md` for schema
   - Use Firebase Console to manually add docs
   - Or import JSON via Firebase CLI

### Step 4: Test Locally

```bash
# Start a local web server
cd docs/
python -m http.server 8000
# OR
npx http-server

# Visit http://localhost:8000
# Test:
# 1. Home page loads
# 2. Login works (demo: AYUSH1234 / Ayush@2908)
# 3. Dashboard displays
# 4. Create/edit team works
# 5. No console errors (F12 > Console)
```

---

## 🔧 Firebase Configuration (firebase.json)

Create file: `firebase.json` in project root

```json
{
  "hosting": {
    "public": "docs",
    "ignore": [
      "firebase.json",
      "**/.*",
      "**/node_modules/**"
    ],
    "rewrites": [
      {
        "source": "**",
        "destination": "/index.html"
      }
    ],
    "headers": [
      {
        "source": "**/*.@(js|css)",
        "headers": [
          {
            "key": "Cache-Control",
            "value": "public, max-age=3600"
          }
        ]
      },
      {
        "source": "**/*.html",
        "headers": [
          {
            "key": "Cache-Control",
            "value": "public, max-age=300"
          }
        ]
      }
    ]
  }
}
```

## 🔐 Firebase Project Config (.firebaserc)

Create file: `.firebaserc` in project root

```json
{
  "projects": {
    "default": "your-project-id"
  },
  "targets": {},
  "etags": {}
}
```

Replace `your-project-id` with your actual Firebase project ID.

---

## 📦 Deploy Commands

### Firebase Hosting

```bash
# Deploy only hosting
firebase deploy --only hosting

# Deploy with custom message
firebase deploy --message "Initial deploy with all pages"

# View deployment history
firebase open hosting:site
```

### GitHub Pages

```bash
# Push to main branch
git add .
git commit -m "Deploy static site to GitHub Pages"
git push origin main

# GitHub automatically deploys to:
# https://your-username.github.io/esports-tournament-management-system/
```

---

## 🌐 Custom Domain Setup

### Firebase Hosting + Custom Domain

1. **In Firebase Console:**
   - Hosting > Custom Domain
   - Enter your domain (e.g., `esports.example.com`)
   - Add DNS records (Firebase provides instructions)
   - Verify ownership

2. **After verification:**
   - SSL certificate auto-provisioned
   - Site available at custom domain

### GitHub Pages + Custom Domain

1. **In GitHub:**
   - Settings > Pages
   - Custom domain: Enter your domain
   - GitHub creates `CNAME` file in `docs/`

2. **In DNS Provider:**
   - Add CNAME record:
     ```
     esports.example.com CNAME your-username.github.io
     ```

3. **Enforce HTTPS:**
   - Check "Enforce HTTPS" in GitHub Pages settings

---

## ✨ Post-Deployment

### 1. Verify Deployment

```bash
# Firebase Hosting
firebase open hosting:site

# GitHub Pages
open https://your-username.github.io/esports-tournament-management-system/
```

### 2. Test Core Features

- [ ] Home page loads (`/`)
- [ ] Login page loads (`/login.html`)
- [ ] Demo login works
- [ ] Dashboard displays stats
- [ ] Create new team works
- [ ] Edit team works
- [ ] List pages load
- [ ] Mobile responsive (test on phone)
- [ ] No console errors (DevTools > Console)

### 3. Monitor Performance

**Firebase Console:**
- Firestore > Usage tab
- Hosting > Analytics tab
- Monitor read/write counts

**GitHub Pages:**
- No built-in analytics
- Use Google Analytics (optional)

### 4 . Set Up Alerts (Optional)

**Firebase Console:**
- Firestore > Alarms
- Set budget alerts
- Get notified of quota limits

---

## 🐛 Debugging Deployment Issues

| Error | Solution |
|-------|----------|
| **"Cannot find module"** | Check all script tags in HTML files point to correct paths |
| **Firebase config not loading** | Check `firebase-config.js` is in `docs/js/` |
| **"Firestore not initialized"** | Verify Firebase credentials in `firebase-config.js` |
| **404 on GitHub Pages** | Check paths use correct base URL if deploying to subdirectory |
| **Styles not applying** | Verify `css/gaming.css` path in `<link>` tags |
| **Login fails** | Check Firestore `users` collection exists; verify auth enabled |
| **Data not saving** | Check Firestore permissions in Firebase Console > Rules |
| **Blank page** | Check browser console (F12) for JavaScript errors |

---

## 📊 Performance Tips

1. **Minimize data reads:**
   ```javascript
   // Instead of loading all teams:
   API.getTeams()
   
   // Load only top 10:
   db.collection('teams').orderBy('totalPoints', 'desc').limit(10)
   ```

2. **Enable browser caching:**
   - Configured in `firebase.json`
   - Static assets cached for 1 hour
   - HTML cached for 5 minutes

3. **Use CDN (Firebase Hosting):**
   - Automatic global distribution
   - Files served from nearest edge location

4. **Monitor bundle size:**
   - Keep JavaScript modular
   - Lazy load if needed

---

## 🔒 Security Checklist

- [ ] Firestore rules prevent unauthorized writes
- [ ] Firebase Auth enabled
- [ ] Password reset configured
- [ ] HTTPS enforced
- [ ] API keys restricted (Firebase Console > APIs)
- [ ] No sensitive data in client-side code
- [ ] Demo credentials documented

---

## 📈 Scaling Considerations

**Free Tier Limits:**
- 50,000 reads/day
- 20,000 writes/day
- 20,000 deletes/day

**If you exceed:**
1. Firebase will notify
2. Upgrade to paid plan
3. Or optimize queries to reduce reads

**Ways to optimize:**
- Cache query results
- Batch operations
- Use pagination
- Denormalize frequently-read data

---

## 🎯 Next Steps

1. ✅ Complete all pre-deployment checks
2. ✅ Deploy using Firebase Hosting or GitHub Pages
3. ✅ Verify all pages load
4. ✅ Test login and CRUD operations
5. ✅ Share public URL with team
6. ✅ Monitor Firestore usage
7. ✅ Set up custom domain (optional)

---

## 📚 Quick Links

- [Firebase Console](https://console.firebase.google.com/)
- [Firebase CLI Documentation](https://firebase.google.com/docs/cli)
- [Firebase Hosting Documentation](https://firebase.google.com/docs/hosting)
- [Firestore Documentation](https://firebase.google.com/docs/firestore)
- [GitHub Pages Documentation](https://pages.github.com/)

---

## 💬 Need Help?

1. Check **MIGRATION_GUIDE.md** for setup instructions
2. Check **FIRESTORE_MAPPING.md** for database schema
3. Review browser console errors (F12)
4. Check Firebase Console for permission errors
5. Visit [Firebase Support](https://firebase.google.com/support)

---

## 🎉 Success!

Once deployed, you have a **fully functional esports management system** running on:
- **Firebase Cloud Infrastructure**
- **GitHub Pages or Firebase Hosting**
- **Accessible from anywhere**
- **No server maintenance required**

Share your live URL: `https://your-project.web.app` 🚀
