# Firestore Collections Setup Guide

Your Firebase project is now configured: **esports-tournament-1b0eb**

## ✅ Step 1: Go to Firestore Console

1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Select your project: **esports-tournament-1b0eb**
3. Click **Firestore Database** (left sidebar)
4. Click **Create Database**
5. Select **Start in test mode** (for now - we'll secure it later)
6. Choose region: **asia-south1** (or closest to you)
7. Click **Create**

## ✅ Step 2: Enable Firebase Authentication

1. In Firebase Console, click **Authentication** (left sidebar)
2. Click **Get Started**
3. Enable **Email/Password** provider:
   - Click **Email/Password**
   - Toggle **Enable** ON
   - Click **Save**

## ✅ Step 3: Create Collections & Documents

Copy-paste the JSON data below into each collection. Follow these steps for each collection:

### Option A: Manual Creation (Recommended for understanding)

For each collection below:
1. Click **+ Start collection** in Firestore
2. Enter collection name (e.g., "teams")
3. Click **Next**
4. Click **Auto-ID** to generate document ID
5. Add fields as shown in the sample data
6. Click **Save**
7. Repeat for additional documents

### Option B: Import JSON (Faster)

Use Firebase CLI or Firestore UI to import JSON. But **Option A** is simpler for first setup.

---

## 📊 Collection 1: Users (Optional - for role management)

**Collection name:** `users`

Create 1 document with Auto-ID:

```javascript
{
  "email": "admin@esports.com",
  "username": "AYUSH1234",
  "role": "admin",
  "createdAt": "(timestamp - Nov 19, 2025)"
}
```

---

## 🏆 Collection 2: Teams

**Collection name:** `teams`

Create these 3 documents with Auto-ID:

### Document 1:
```javascript
{
  "teamName": "Phoenix Rising",
  "description": "Legendary esports team with championship wins",
  "totalPoints": 450,
  "wins": 12,
  "losses": 3,
  "createdAt": "(timestamp)"
}
```

### Document 2:
```javascript
{
  "teamName": "Dragon Force",
  "description": "Fast and aggressive gameplay style",
  "totalPoints": 380,
  "wins": 10,
  "losses": 5,
  "createdAt": "(timestamp)"
}
```

### Document 3:
```javascript
{
  "teamName": "Apex Predators",
  "description": "Strategic and tactical team",
  "totalPoints": 320,
  "wins": 8,
  "losses": 7,
  "createdAt": "(timestamp)"
}
```

---

## 👤 Collection 3: Players

**Collection name:** `players`

Create these documents with Auto-ID:

### Document 1:
```javascript
{
  "playerName": "AlexPro",
  "role": "Rifler",
  "teamId": "[COPY_TEAM1_ID_HERE]",
  "kd_ratio": 1.45,
  "skill_rating": 92,
  "createdAt": "(timestamp)"
}
```

### Document 2:
```javascript
{
  "playerName": "ShadowHunter",
  "role": "AWPer",
  "teamId": "[COPY_TEAM1_ID_HERE]",
  "kd_ratio": 2.1,
  "skill_rating": 95,
  "createdAt": "(timestamp)"
}
```

### Document 3:
```javascript
{
  "playerName": "ThunderStrike",
  "role": "Support",
  "teamId": "[COPY_TEAM2_ID_HERE]",
  "kd_ratio": 0.95,
  "skill_rating": 85,
  "createdAt": "(timestamp)"
}
```

### Document 4:
```javascript
{
  "playerName": "IceQueen",
  "role": "Entry Fragger",
  "teamId": "[COPY_TEAM2_ID_HERE]",
  "kd_ratio": 1.65,
  "skill_rating": 89,
  "createdAt": "(timestamp)"
}
```

### Document 5:
```javascript
{
  "playerName": "NovaKing",
  "role": "IGL",
  "teamId": "[COPY_TEAM3_ID_HERE]",
  "kd_ratio": 1.2,
  "skill_rating": 88,
  "createdAt": "(timestamp)"
}
```

---

## 🎮 Collection 4: Tournaments

**Collection name:** `tournaments`

Create these 2 documents with Auto-ID:

### Document 1:
```javascript
{
  "tournamentName": "Winter Championship 2025",
  "description": "Grand tournament with prize pool",
  "status": "ongoing",
  "prizePool": 50000,
  "startDate": "2025-11-20",
  "endDate": "2025-12-20",
  "createdAt": "(timestamp)"
}
```

### Document 2:
```javascript
{
  "tournamentName": "Spring Qualifiers 2026",
  "description": "Qualifying matches for spring league",
  "status": "upcoming",
  "prizePool": 25000,
  "startDate": "2026-03-01",
  "endDate": "2026-04-15",
  "createdAt": "(timestamp)"
}
```

---

## ⚽ Collection 5: Matches

**Collection name:** `matches`

Create these 2 documents with Auto-ID:

### Document 1:
```javascript
{
  "team1": "[COPY_TEAM1_ID_HERE]",
  "team2": "[COPY_TEAM2_ID_HERE]",
  "team1Score": 2,
  "team2Score": 1,
  "status": "completed",
  "tournamentId": "[COPY_TOURNAMENT1_ID_HERE]",
  "matchDate": "2025-11-18",
  "createdAt": "(timestamp)"
}
```

### Document 2:
```javascript
{
  "team1": "[COPY_TEAM2_ID_HERE]",
  "team2": "[COPY_TEAM3_ID_HERE]",
  "team1Score": 0,
  "team2Score": 0,
  "status": "upcoming",
  "tournamentId": "[COPY_TOURNAMENT1_ID_HERE]",
  "matchDate": "2025-11-22",
  "createdAt": "(timestamp)"
}
```

---

## 📝 How to Add Fields in Firestore Console

When creating a document:

1. Click **+ Add field**
2. Enter **Field name** (e.g., "teamName")
3. Select **Type** (e.g., "String")
4. Enter **Value** (e.g., "Phoenix Rising")
5. Click **Save**
6. Repeat for all fields

---

## ✅ Verify Collections Created

After creating all collections, you should have:

```
Firestore Collections:
├─ users (1 document)
├─ teams (3 documents)
├─ players (5 documents)
├─ tournaments (2 documents)
└─ matches (2 documents)
```

---

## 🔒 Security Rules (For Later)

Replace test mode rules with these in **Firestore > Rules**:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Allow public read
    match /{document=**} {
      allow read: if true;
    }
    
    // Allow authenticated users to write
    match /{document=**} {
      allow write: if request.auth != null;
    }
  }
}
```

---

## 📞 Troubleshooting

**Error: "Collection not found"**
- Make sure you created the collection with exact name spelling

**Error: "Permission denied"**
- Switch to "Test mode" in Firestore Security Rules (tab)
- Or use the security rules above

**Data not showing in app**
- Verify collection names match exactly (case-sensitive)
- Check document field names match the app code

---

## ✅ You're Ready!

Once all 5 collections are created with sample data:
1. Close this document
2. Go back to main deployment steps
3. Test the app locally
4. Deploy to GitHub Pages

Let me know when you've completed this setup! Reply with "Done with Firestore" and I'll continue with testing and deployment.
