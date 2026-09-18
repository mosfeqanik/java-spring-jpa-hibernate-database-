---
name: commit
description: Stage a specific set of files (never a blanket git add -A or git add .) and create a git commit for them, following this repo's conventions (Co-Authored-By attribution line). Use this whenever the user names particular files/folders and asks to "commit X", "add and commit these", "commit just the Y changes", or similar — i.e. any time the intent is to commit a chosen subset of the working tree rather than everything that's changed.
---

# Commit specific files

This skill commits only the files the user names — it never sweeps in unrelated
changes with `git add -A` / `git add .`. That distinction is the whole point of
using it instead of a generic commit: the user is choosing a subset on purpose,
often because other changes in the tree are unrelated, half-finished, or
private.

## Steps

1. **Resolve the file list.** Use the paths given in `$ARGUMENTS` (files or
   folders, space-separated). If none were given, ask which files to commit —
   don't guess and don't fall back to committing everything.

2. **Look before staging.** Run `git status` and `git diff -- <paths>` (plus
   `git diff --staged -- <paths>` if anything's already staged) for exactly the
   named paths. Confirm the changes look like what the user described, and
   check nothing that looks like a secret or credential is in there.

3. **Stage only those paths:** `git add -- <paths>`. Never widen this to `-A`
   or `.`, even if `git status` shows other modified files — those are
   intentionally being left out.

4. **Write the message.** If the user supplied a message, use it. Otherwise
   draft a 1-2 sentence message from the actual diff, focused on *why* the
   change was made rather than restating the diff. Match the tone of this
   repo's existing log (`git log --oneline -10`) — short, imperative,
   no fluff.

5. **Commit with attribution**, passed via heredoc so formatting survives:

   ```bash
   git commit -m "$(cat <<'EOF'
   <summary line>

   Co-Authored-By: Claude Sonnet 5 <noreply@anthropic.com>
   EOF
   )"
   ```

6. **Confirm.** Run `git status` and `git log -1 --oneline` afterward and
   report the resulting commit hash and message to the user.

7. **Don't push.** This skill stops at a local commit. If the user also wants
   it pushed, point them at the `commit-push` skill instead of pushing here —
   push is a shared, harder-to-reverse action and deserves its own explicit
   step.
