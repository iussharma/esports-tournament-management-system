#!/usr/bin/env python3
"""
Quick Firestore Data Import Script
Run this after creating collections manually or use Firebase CLI
"""

import json
from datetime import datetime

# Sample data structure for Firestore
FIRESTORE_DATA = {
    "users": [
        {
            "email": "admin@esports.com",
            "username": "AYUSH1234",
            "role": "admin",
            "createdAt": datetime.now().isoformat()
        }
    ],
    "teams": [
        {
            "teamName": "Phoenix Rising",
            "description": "Legendary esports team with championship wins",
            "totalPoints": 450,
            "wins": 12,
            "losses": 3,
            "createdAt": datetime.now().isoformat()
        },
        {
            "teamName": "Dragon Force",
            "description": "Fast and aggressive gameplay style",
            "totalPoints": 380,
            "wins": 10,
            "losses": 5,
            "createdAt": datetime.now().isoformat()
        },
        {
            "teamName": "Apex Predators",
            "description": "Strategic and tactical team",
            "totalPoints": 320,
            "wins": 8,
            "losses": 7,
            "createdAt": datetime.now().isoformat()
        }
    ],
    "players": [
        {
            "playerName": "AlexPro",
            "role": "Rifler",
            "kd_ratio": 1.45,
            "skill_rating": 92,
            "createdAt": datetime.now().isoformat()
        },
        {
            "playerName": "ShadowHunter",
            "role": "AWPer",
            "kd_ratio": 2.1,
            "skill_rating": 95,
            "createdAt": datetime.now().isoformat()
        },
        {
            "playerName": "ThunderStrike",
            "role": "Support",
            "kd_ratio": 0.95,
            "skill_rating": 85,
            "createdAt": datetime.now().isoformat()
        },
        {
            "playerName": "IceQueen",
            "role": "Entry Fragger",
            "kd_ratio": 1.65,
            "skill_rating": 89,
            "createdAt": datetime.now().isoformat()
        },
        {
            "playerName": "NovaKing",
            "role": "IGL",
            "kd_ratio": 1.2,
            "skill_rating": 88,
            "createdAt": datetime.now().isoformat()
        }
    ],
    "tournaments": [
        {
            "tournamentName": "Winter Championship 2025",
            "description": "Grand tournament with prize pool",
            "status": "ongoing",
            "prizePool": 50000,
            "startDate": "2025-11-20",
            "endDate": "2025-12-20",
            "createdAt": datetime.now().isoformat()
        },
        {
            "tournamentName": "Spring Qualifiers 2026",
            "description": "Qualifying matches for spring league",
            "status": "upcoming",
            "prizePool": 25000,
            "startDate": "2026-03-01",
            "endDate": "2026-04-15",
            "createdAt": datetime.now().isoformat()
        }
    ],
    "matches": [
        {
            "team1": "Phoenix Rising",
            "team2": "Dragon Force",
            "team1Score": 2,
            "team2Score": 1,
            "status": "completed",
            "matchDate": "2025-11-18",
            "createdAt": datetime.now().isoformat()
        },
        {
            "team1": "Dragon Force",
            "team2": "Apex Predators",
            "team1Score": 0,
            "team2Score": 0,
            "status": "upcoming",
            "matchDate": "2025-11-22",
            "createdAt": datetime.now().isoformat()
        }
    ]
}

if __name__ == "__main__":
    print("Firestore Sample Data Generated")
    print(json.dumps(FIRESTORE_DATA, indent=2))
    print("\nUse this data in Firebase Console > Firestore Database")
    print("Create each collection and add documents manually (refer to FIRESTORE_SETUP.md)")
