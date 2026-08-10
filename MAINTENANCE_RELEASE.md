# Maintenance Release — Frozen HL7-PET Scala Library

This document describes how to cut a **one-off maintenance release** of the
**frozen** Scala/JVM `hl7-pet` library to Maven Central, should a critical fix
ever be required after the freeze.

This is an exceptional, out-of-band path off the frozen maintenance line. Under
normal circumstances the Scala library is **frozen** and no new versions are
published. Do not follow this procedure for routine changes.

## Current status

`maintenance/v1.3.0` is being pushed as the frozen anchor with the final `1.3.1`
version bump committed on the branch (see Step 3). **Only the branch is pushed — no
`v*.*.*` tag is created — so nothing is published to Maven Central.** The tag/publish
mechanics in Steps 6–7 are retained for reference only, in case a real publish is ever
approved later. Once pushed, the branch is left **stale** — no publish is planned.

## Publication reality (read first)

- The last **published** Maven Central artifact is `gov.cdc:hl7-pet_2.13:1.2.11`
  (released 2024-09-10). See
  <https://central.sonatype.com/artifact/gov.cdc/hl7-pet_2.13/versions>.
- No `1.3.0` was ever published to Central. The `v1.3.0` git tag is an unpublished
  source anchor; `build.sbt` still declares `1.2.11`.
- **Maven Central artifacts are immutable** — a published coordinate can never be
  overwritten or deleted. A maintenance release **must** use a version **strictly
  greater than `1.2.11`**.

## When to invoke

Invoke this procedure only when **all** of the following hold:

- A critical defect or security issue exists in the last published Scala artifact
  (`1.2.11`).
- No acceptable workaround exists for affected downstream consumers.
- A responsible maintainer has explicitly approved a one-off release.

## Prerequisites

- **JDK 17** for publishing. The frozen release workflow
  [.github/workflows/sbt-build.yml](.github/workflows/sbt-build.yml) builds and
  publishes on JDK 17, which is how `1.2.11` was produced. Local verification may
  also be run on JDK 11, but publish through the same toolchain the artifact was
  originally built with (JDK 17).
- **Scala 2.13.13**, **SBT** per [project/build.properties](project/build.properties).
- A `GITHUB_TOKEN` environment variable for dependency resolution (see
  [README.md](README.md)).
- **Sonatype (Maven Central) credentials** authorized for the `gov.cdc` namespace.
- A **PGP signing key** for artifact signing, with its passphrase available to SBT.
- Repository secrets used by the workflow: `PGP_SECRET`, `PGP_PASSPHRASE`,
  `SONATYPE_USERNAME`, `SONATYPE_PASSWORD`.

> The frozen `maintenance/v1.3.0` branch retains the Sonatype/PGP publishing
> configuration ([build.sbt](build.sbt), [project/plugins.sbt](project/plugins.sbt))
> and the release workflow. The slim/refactoring line intentionally removed them,
> so releases must be cut from `maintenance/v1.3.0`, never from the slim line.
> Note the workflow auto-triggers on pushing a `v*.*.*` tag or creating a GitHub
> Release — pushing the tag *is* the publish action.

## Procedure

### 1. Start from the frozen line

```sh
git fetch --all --tags
git switch maintenance/v1.3.0        # frozen maintenance line at commit d35be5c (== tag v1.3.0)
git switch -c hotfix/<short-description>
```

Never cut a release from the slim/refactoring branch: it has features removed and
its publishing configuration stripped.

### 2. Apply the minimal fix

- Make the smallest change that resolves the issue.
- Do not reintroduce features that were removed on the slim line and do not pull in
  slim-line refactoring.
- Add or update a test that proves the fix.

### 3. Choose and set the release version

- Use **`1.3.1`** — the planned final version. It is strictly greater than the last
  published `1.2.11`, and `1.3.0` is intentionally skipped because the `v1.3.0` git tag
  is reserved as the immutable source anchor.
- Update the version in [build.sbt](build.sbt) (currently `1.2.11`) to `1.3.1`.
- Add a matching entry to [ChangeLog.md](ChangeLog.md).

> **The published coordinate comes from the `version` in [build.sbt](build.sbt),
> not from the tag name.** The tag is only the trigger and the commit that gets
> checked out. The `v<version>` tag must match the bumped `build.sbt` version by
> convention, but tagging **without** bumping `build.sbt` will attempt to
> republish `1.2.11` and fail (immutable coordinate). Bumping `build.sbt` is the
> load-bearing step; the tag name is convention only.

### 4. Verify locally

```sh
java -version                        # JDK 17 to match the frozen release toolchain (JDK 11 acceptable for local checks only)
sbt clean compile
sbt test
sbt package
sbt makePom
```

Confirm the JAR contains the expected classes and resources, and confirm on Maven
Central that the chosen version is **unused**, before publishing.

### 5. Commit and push the branch (no tag)

```sh
git commit -am "Maintenance release 1.3.1: <short-description>"
git push origin maintenance/v1.3.0
```

**Do not create or push a `v*.*.*` tag.** The tag is the publish trigger (Step 6);
leaving the commit untagged keeps `1.3.1` on the branch only, with nothing sent to
Maven Central.

### 6. Publish (reference only — not performed here)

This step is **not** run for the current branch-only push. It is documented so a real
publish can be done later if ever approved.

If publishing: the frozen [.github/workflows/sbt-build.yml](.github/workflows/sbt-build.yml)
(named *Release*) auto-runs `sbt clean publishSigned sonatypeBundleRelease` on JDK 17
whenever a `v*.*.*` tag is pushed or a GitHub Release is created — so pushing the tag is
the publish action. It only succeeds with the required secrets present (`PGP_SECRET`,
`PGP_PASSPHRASE`, `SONATYPE_USERNAME`, `SONATYPE_PASSWORD`) and maintainer approval.

Because Central artifacts are immutable, a failed or partial publish of a taken
coordinate cannot be retried under the same version — bump again and repeat.

### 7. Announce (only if actually published)

- If Step 6 was performed, confirm the release is visible on Maven Central and notify
  downstream consumers. For a branch-only push, there is nothing to announce.

## Do NOT

- Do not overwrite or attempt to re-publish `1.2.11` or any existing coordinate.
- Do not move, delete, or recreate the `v1.3.0` git tag.
- Do not push a `v*.*.*` tag for this branch-only release — that would trigger a Maven
  Central publish, which is not intended.
- Do not release from the slim/refactoring branch.
- Do not merge slim-line changes into `maintenance/v1.3.0`.
- Do not treat this procedure as a routine release path.
