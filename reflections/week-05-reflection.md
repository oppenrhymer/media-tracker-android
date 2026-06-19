# Week 2 Reflection

**Name:** Ryan Burke
**Date:** 06/18/2026

---

## Commits This Week


**Link:** https://github.com/oppenrhymer/media-tracker-android/commit/7e4344281421aa08f72cf8845caad8366fac175d

https://github.com/oppenrhymer/media-tracker-android/commit/f1ba6faea816a602e5ed21c498d53514e89caf6e

https://github.com/oppenrhymer/media-tracker-android/commit/f4ab582fe371d043eda16695ddb10d5aab313e43


---

## Code Review


**Reviewed:** Mai Moua
**Link to my review:** https://github.com/mmoua02/media-tracker-android/pull/5#pullrequestreview-4529745647

### What I Looked At

I examined Mai's PR, specifically her UserRepository as I was curious about how she implemented things. 
I also looked at her TokenResponse class and her gradle build config.



### What I Noticed

Her implementation of the UserRepository was much cleaner looking than mine. She used interfaces
as was demonstrated in class.
I noticed her TokenResponse data class was incomplete; it was missing the accessToken and refreshToken.
I noticed a strange dependency in her gradle build config. I couldn't find reference to it in the rest of the code.


 

### Comments I Left
I complimented her on how professional her UserRepository implementation was. I also reminded her to add the other
fields to her TokenResponse, to avoid any deserialization issues. I also pointed out the strange dependency, just in case it
isn't necessary and slipped through the cracks.

---

## One Thing I Understood More Deeply

When I first started trying to hit the /users endpoint, I would get exceptions thrown for there being
null values in the response, e.g. bio, avatar. After looking at the documentation, it appeared that 
you can set the json to ignore those fields; but I couldn't get it to work right away. In my impatience,
I instead learned about making fields nullable via ? = null, in the data class.


---

## One Thing I'm Still Confused About

How integrated Kotlin is with Java, i.e. how much can you mix the two/why might someone mix them? 
I was intrigued by this after noticing _val userApiService: UserApiService = retrofit.create(UserApiService::class.java)_,
in Benjamin's RetrofitInstance.kt.

---

## Anything Else *(optional)*




---

## Rubric

*You don't need to self-assess — this is here so you know what I'm looking at.*

| Section | Points | Full Credit | Half Credit | No Credit |
|:---|:---:|:---|:---|:---|
| **Reflection** | 10 | Specific, honest responses to "More Deeply" and "Still Confused" sections. Shows genuine thinking — not just "I learned X." | Responses are present but vague or generic ("I got better at Compose"). | Missing or one-word answers. |
| **Code Review** | 10 | Specific observation about the code with explanation of why it matters (or a substantive positive comment). Link to review present and verified. | A question or comment that shows you read the code, but lacks explanation. | "Looks good!" or equivalent. Missing link. Review not found on GitHub. |
| **Total** | **20** | | | |

**A note on the code review score:** I check that the review actually exists on GitHub before grading. The written summary here and the GitHub comment should match. If the review isn't there, the written summary can't earn credit.
