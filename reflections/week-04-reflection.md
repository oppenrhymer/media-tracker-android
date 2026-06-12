# Week 2 Reflection

**Name:** Ryan Burke
**Date:** 06/11/2026

---

## Commits This Week


**Link:** https://github.com/oppenrhymer/media-tracker-android/commit/2ec3d0c91a1cbbea6ce6cf5107b50b79e3bfd2d0

---

## Code Review


**Reviewed:** Diego Godinez
**Link to my review:** https://github.com/dgodinez227/media-tracker-android/pull/4#discussion_r3400277617

### What I Looked At

I examined Diego's PR, the purpose of which was to add his implementation of the Registration Screen (RegisterScreen.kt) and the accompanying ViewModel (RegisterViewModel.kt).



### What I Noticed

I noticed that he implemented the snackbar, and attempted to follow the logic there. I kind of grasp it
but am still confused. It uses a bunch of built-in methods for which I don't have time to go down the rabbit hole.
I also noticed that he hardcoded a few strings in the input text field labels.
In RegisterViewModel, he also had a really neat-looking way of using _when_ to do form validation, and return
specific error messages for each type of validation error.

 

### Comments I Left


I suggested that he move some hardcoded string values (the labels in the input text fields) into strings.xml, to follow best practices. I also
complimented him on using the _when_ keyword in his form validation, because it looks way nicer than mine (I used a helper method that
returns a boolean if it fails).


---

## One Thing I Understood More Deeply



Using the MutableStateFlow variables make a little more sense now. When dealing with trying to get
appropriate error messages to display on the RegisterScreen, I initially had if (viewmodel.hasErrors()),
which returned true if there was an errorMessage (filled during form validation, when the Sign Up button was clicked).
However, it had weird behavior. Even though in the ViewModel, errorMessage had changed, the if statement
would not trigger, unless I did something else in the form. I reasoned that whatever triggers the 
screen to redraw, wasn't happening.
After changing to if (errorMessage == ""), referencing the MutableStateFlow variable directly, the
error message is now displaying responsively.

---

## One Thing I'm Still Confused About

Kotlin syntax. Random question marks and exclamation points showing up in places, and I still don't
know how to parse them.

---

## Anything Else *(optional)*

I implemented the "Already have an account? Log in" correctly; that is, only "Log in" is a button to
navigate back to the logon screen. To make the layout look correct, I needed to add a nested Row, so that
the Text and TextButton objects could sit next to each other, as well as arrange and align them accorndingly.



---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
