---
name: commit-push
description: Stage a specific set of files (never a blanket git add -A or git add .), commit them following this repo's conventions, and push to the current branch's remote — after explicit user confirmation of the push step. Use whenever the user asks to "commit and push X", "push these files", or names particular files/folders they want committed and shipped to the remote in one go.
---

# Commit and push specific files

Same as the `commit` skill for staging and committing, plus a push at the end.
The commit half is silent, reversible, local work; the push half is not — it
changes shared state on the remote — so it gets its own explicit confirmation
gate even when the user already asked for "commit and push" in one breath.
People say that phrase loosely; a moment to confirm the actual push costs
almost nothing and prevents shipping something half-checked.

## Steps

1. **Resolve the file list** from `$ARGUMENTS` (files/folders, space-separated).
   If none were given, ask — don't default to everything changed.

2. **Look before staging.** Run `git status` and `git diff -- <paths>` for
   exactly the named paths. Check the changes match what the user described
   and that nothing that looks like a secret is included — this matters more
   here than for a local-only commit, since it's about to leave the machine.

3. **Stage only those paths:** `git add -- <paths>`. Never `-A` or `.`.

4. **Write the message** (user-supplied, or drafted from the diff — why, not
   what — matching the tone of `git log --oneline -10`).

5. **Commit with attribution:**

   ```bash
   git commit -m "$(cat <<'EOF'
   <summary line>

   Co-Authored-By: Claude Sonnet 5 <noreply@anthropic.com>
   EOF
   )"
   ```

6. **Check the push target before asking.** Run `git status -sb` (or
   `git rev-parse --abbrev-ref --symbolic-full-name @{u}` ) to see whether the
   branch has an upstream, and `git log @{u}.. --oneline` if it does, to see
   how many commits are about to go out. If there's no upstream yet, that's
   fine — it just means the push will need `-u origin <branch>`.

7. **Confirm before pushing.** Show the user the commit(s) about to be pushed
   and which branch/remote they're going to, then ask for explicit
   confirmation. Do not push without it, even if the user's original request
   already said "push" — this is the one irreversible-ish step (rewriting
   remote history is hard to undo for anyone who's already pulled), so it
   always gets a checkpoint.

8. **On confirmation, push:**

   ```bash
   git push            # if upstream is already set
   git push -u origin <branch>   # first push of a new branch
   ```

   Never force-push (`--force`/`-f`) from this skill. If a push is rejected
   because the remote has diverged, stop and tell the user rather than
   force-pushing on their behalf.

9. **Report the result** — the commit hash, branch, and remote it now lives
   on.
