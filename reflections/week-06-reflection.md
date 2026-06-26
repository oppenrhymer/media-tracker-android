# Week 2 Reflection

**Name:** Ryan Burke
**Date:** 06/25/2026

---

## Commits This Week


**Link:** https://github.com/oppenrhymer/media-tracker-android/pull/6/changes/44640a8e785d4c44a7a5d3815510c23cbaea92d4

---

## Code Review


**Reviewed:** Diego Godinez
**Link to my review:**
https://github.com/dgodinez227/media-tracker-android/pull/6#pullrequestreview-4576323833


### What I Looked At
I looked at Diego's SearchResultsViewModel.kt class to see his progress on reaching the media API.
I also looked at his SearchScreen.kt, to see how he implemented the UI.




### What I Noticed
SearchResultsViewModel looked like it was fleshed out, but was not connected to
the API yet. So, the search functionality was not yet implemented.
I noticed in SearchScreen that he was using the wrong string from the strings.xml; specifically, "app_name"
instead of something that says "Search".


 

### Comments I Left
For SearchResultsViewModel, I hinted that he will need to find a way to get the auth token in that class (for the API call),
and to do so he can reference Benjamin's DefaultSessionRepository.

For SearchScreen, I reminded him not to forget to change the header string to "Search", so as to fit
the spec.

---

## One Thing I Understood More Deeply

I feel like I grasp scoping/context a bit better. I was trying to find a way to persist the auth token. Benjamin
had a DefaultSessionRepository which implemented the android data store. In order to use it, I had to add
a reference to the Application in the ViewModels parameter list, where I wanted to access that same store. 
This allowed, I think, for me to access a single data score at the application scope, so I could share
state between classes.


---

## One Thing I'm Still Confused About

I was able to get the API to hit, but am not sure how to add the parameters listed on the api spec, e.g.
query, type. I think I'm currently doing an empty GET request, which just returns the same results every time.
Are the query parameters to be added as headers? I don't have time tonight to test, but will try that next.

---

## Anything Else *(optional)*

Nothing this week.


---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
