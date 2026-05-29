# Week 2 Reflection

**Name:**
Ryan Burke
**Date:**
05/28/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:**
https://github.com/oppenrhymer/media-tracker-android/commit/0ef43a42a86e19877b6da6e2a4a9ebde25ce49fc
---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Diego
**Link to my review:** https://github.com/dgodinez227/media-tracker-android/commit/a98e4393aaa827884d45b001267e745313a678e3#commitcomment-186869592

### What I Looked At

<!-- Walk through the code you reviewed. What was the PR trying to do? Which files or
     functions did you focus on? -->
(Note, there was an issue with getting his code into git and there didn't end up being a PR for me to 
review. I still reviewed the code and made the comment linked above, however)
I looked at LibraryViewModel.kt, specifically the loadLibrary function. The PR was to fix an issue
with there being a delay when looking at the library tab.

### What I Noticed

<!-- Be specific. Did you spot a potential bug? A pattern that could cause problems? Something
     done well that you want to call out? "I looked at the ViewModel and everything seemed fine"
     is not specific enough. Name the thing you noticed and explain why it matters. -->

Diego figured this one out during our pod time. He found the Thread.sleep() call and realized that was
what was delaying the tab from opening. Was awesome.

### Comments I Left

<!-- Briefly summarize the comments you left on the PR. If you left a positive comment,
     say what it was. If you left a suggestion, say what you suggested and why. -->

I praised him for finding the Thread.sleep(). It really was an awesome catch.

---

## One Thing I Understood More Deeply

<!-- Be specific. Don't write "I learned about ViewModels." Write what specifically clicked —
     what was confusing before, what made it make sense, and how you'd explain it to someone else.
     There are no wrong answers here. -->

One thing I learned during this week's session was about GlobalScope; 
specifically, that it is not life-cycle aware and that you should be wary of using it.
---

## One Thing I'm Still Confused About

<!-- Be honest. This is the most useful part of the reflection for me — it tells me where to
     spend more time in class. You will not lose points for being confused. -->

Still confused about coroutines. I've heard about them before and how they are a huge
benefit to Kotlin, but still are kind of in the dark.

---

## Anything Else *(optional)*

<!-- Did you help a pod mate work through something? Did you discover something cool or frustrating?
     Did something from a previous week finally click? This is a good place to put it. -->

I found an alternative a fix for the filters resetting issue (#16) after reading the compose docs. 
By using rememberSaveable, the filters persisted through rotations. Not ultimately the right answer,
but was cool.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
