# Branching and merging #

The instructions belpw were adapted from [here](https://code.google.com/p/thrust/wiki/BranchingAndMerging) and [here](http://blog.red-bean.com/sussman/?p=92). For detailed info refer to [SVN book Red-Bean.com](http://svnbook.red-bean.com/en/1.4/svn.branchmerge.copychanges.html).

## Step 1 ##

Make a branch for your experimental work:

```
$ svn copy https://mathdoku.googlecode.com/svn/trunk/  https://mathdoku.googlecode.com/svn/branches/YOUR_BRANCH_NAME
$ svn switch https://mathdoku.googlecode.com/svn/branches/YOUR_BRANCH_NAME
```

## Step 2 ##

Work on the branch for a while:

```
# ...edit files
$ svn commit
# ...edit files
$ svn commit
```

## Step 3 ##

Sync your branch with the trunk, so your branch doesn’t fall behind:

```
$ svn merge https://mathdoku.googlecode.com/svn/trunk/
--- Merging r3452 through r3580 into '.':
U button.c
U integer.c
...
$ svn commit
```

## Step 4 ##

Repeat the prior two steps until you’re done coding.

## Step 5 ##

Merge your branch back into the trunk:

```
$ svn switch https://mathdoku.googlecode.com/svn/trunk/
$ svn merge --reintegrate https://mathdoku.googlecode.com/svn/branches/YOUR_BRANCH_NAME
--- Merging differences between repository URLs into '.':
U button.c
U integer.c
...

$ svn commit
```