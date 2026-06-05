# Week 2 Reflection

**Name:**
Ryan Burke
**Date:**
06/04/2026

---

## Commits This Week

<!-- Paste a link to your commits for this week. The easiest way: go to your repo on GitHub,
     click "commits", and copy the URL after filtering by your name or branch. -->

**Link:**
https://github.com/oppenrhymer/media-tracker-android/commit/1e5a21b5e447c7f9c7044c01f9ef84e0f58195fd
---

## Code Review

<!-- Every week you leave a review on a pod mate's pull request. Fill in both parts below.
     Part 1 is the link — I will verify the review exists on GitHub.
     Part 2 is your written assessment — what you actually looked at and what you found. -->

**Reviewed:** Mai
**Link to my review:** https://github.com/mmoua02/media-tracker-android/pull/3#discussion_r3359969546

### What I Looked At

<!-- Walk through the code you reviewed. What was the PR trying to do? Which files or
     functions did you focus on? -->

I looked at a few of her classes, including the RegisterScreen implementation and her RegisterScreen ViewModel class.
### What I Noticed

<!-- Be specific. Did you spot a potential bug? A pattern that could cause problems? Something
     done well that you want to call out? "I looked at the ViewModel and everything seemed fine"
     is not specific enough. Name the thing you noticed and explain why it matters. -->

There is a block (sealed class) in the AuthViewModel, which we used as a guide for our RegisterScreen ViewModel.
I saw that block, couldn't understand it right away (and was kind of intimidated by it). But Mai seems to have
integrated it, and is even using it for errorState(??). 

### Comments I Left

<!-- Briefly summarize the comments you left on the PR. If you left a positive comment,
     say what it was. If you left a suggestion, say what you suggested and why. -->

I praised her for figuring out that scary sealed class block in the RegisterScreen ViewModel. I didn't even try to
integrate it myself, and skipped over it; so, I thought it was cool that she appears to have gotten it working.

---

## One Thing I Understood More Deeply

<!-- Be specific. Don't write "I learned about ViewModels." Write what specifically clicked —
     what was confusing before, what made it make sense, and how you'd explain it to someone else.
     There are no wrong answers here. -->

I feel like I'm getting better with the kotlin syntax. Doesn't feel as bizarre and strange as it did,
even a week ago. 

---

## One Thing I'm Still Confused About

<!-- Be honest. This is the most useful part of the reflection for me — it tells me where to
     spend more time in class. You will not lose points for being confused. -->

Still coroutines. We keep approaching it in the material but haven't really jumped into the 
subject yet. 

---

## Anything Else *(optional)*

<!-- Did you help a pod mate work through something? Did you discover something cool or frustrating?
     Did something from a previous week finally click? This is a good place to put it. -->

I was able to help my podmates get started on how to begin, i.e. showed them the LoginScreen elements
that could be taken and repurposed/learned from in building the Create Account screen.

---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
