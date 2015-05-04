# Version 2.1 #

  * Layout improvements across all devices.
  * Swipe circle is now optional.
  * Random difficulty option.
  * Added buttons for maybe, undo, clear.

# Version 2.0.0 #

Version 2 of MathDoku is only available for Android 3+.

## Major importance ##

  * Added statistics for all games played.
  * Added an archive with all games played.
  * Added different complexity levels for the puzzles. Upon starting a game, a complexity level has to be chosen.
  * Puzzles can be shared via email with other users.
  * Digit buttons have been removed. Digits are now selected by swiping. The selected cell will be surround with two circles containing selectable digits. The outer circle can be disabled in the settings.
  * Added an option to use the same color for maybe and normal digits.
  * Upgraded style of app to the lastest guidelines.
  * Puzzles can be replayed.
  * The current state of the input mode is displayed via a orange/blue dot above the grid. The input mode switches on double tapping a cell within a short time frame. Also the input mode dot above the grid can be touched to switch input mode.

## Minor importance ##

  * Allow cancellation of the grid generating parameters dialog.
  * The tip dialogs are shown at a more intuitive pace.
  * Removed the "Cheat" message which was shown each time a cheat is used.
  * Using cheats is discouraged by adding a penalty time to the total time played.
  * Removed options, menu items and the carved theme which were used very little.
  * Improved the dark theme.
  * Added additional tips.
  * Added function to send feedback (including a screen print) to the developers.
  * Added option to play in full screen mode.
  * Show a confirmation dialog before revealing the solution of the puzzle.
  * Moved items from the context menu (long press on the grid) to the normal menu. Items in the menu are only displayed in case they can be used as that moment.


## Bug fixes ##
  * Size of text on tablets enhanced to normal sizes.
  * When undo or clear is used the duplicate warning and bad cage math are checked and removed if necessary.
  * A cage consisting of a single cell is no longer marked with the invalid cage math border in case an incorrect value is entered.
  * The clear button now also clears the maybe values from a cell.

# Version 1.96 #

## Enhancements ##

  * Screens for all devices have been rewritten to display buttons as big as possible ([issue 44](https://code.google.com/p/mathdoku/issues/detail?id=44)). Tablets are now better supported ([issue 47](https://code.google.com/p/mathdoku/issues/detail?id=47), [issue 63](https://code.google.com/p/mathdoku/issues/detail?id=63)).
  * Prepared for more easy translation into other languages.
  * The maybe checkbox has been removed. Toggling the input mode (entering a definitive versus a maybe value) is now done by tapping the text "Mode: definitive" or "Mode: maybe" or by tapping the selected cell once more. ([issue 42](https://code.google.com/p/mathdoku/issues/detail?id=42))
  * Improved algortihm for determining the cage operator ([issue 29](https://code.google.com/p/mathdoku/issues/detail?id=29), [issue 62](https://code.google.com/p/mathdoku/issues/detail?id=62)).
  * Improved scrolling for stored games.
  * Added undo functionality ([issue 22](https://code.google.com/p/mathdoku/issues/detail?id=22)).
  * Improved puzzle generation:
    * Added new shapes for cages
    * Added an option to allow cages with 5 or 6 cells.
  * Changed order of item in the context menu (long click on a cell in the puzzle). Only items that are applicable are shown.
  * In case a game with hidden operators is played, the cage operator can be revealed via the context menu.
  * Hide menu items when not applicable.
  * Cheat information is now stored and made visible again when the game is loaded.
  * The timer is not visible anymore in case the option "Show solution" has been used.
  * Added new option to clear redundant maybe values automatically.
  * Timer does not display hours as long as less than 1 hour is played.
  * Display a message in case a digit is tapped but no cell was selected yet.
  * Changelog refers to this page.
  * Added logging functionality. Users are requested to help developers of MathDoku after completing a number of games. No personal data is collected. Neither will data be transferred without consent of the user.
  * Added German translation.
  * Added tips.
  * Changed colors in the newspaper theme.
  * Text "Press menu ..." is replaced with button "New game".

## Bugfixes ##
  * Maybe values will always be displayed inside cell ([issue 106](https://code.google.com/p/mathdoku/issues/detail?id=106))
  * Remaining english text in dutch translation have been translated.
  * In case a puzzle is solved, no cell or cage will be displayed as selected ([issue 75](https://code.google.com/p/mathdoku/issues/detail?id=75)).
  * Borders of cells and the selected cage are shown correctly and consistently ([issue 48](https://code.google.com/p/mathdoku/issues/detail?id=48), [issue 55](https://code.google.com/p/mathdoku/issues/detail?id=55), [issue 58](https://code.google.com/p/mathdoku/issues/detail?id=58)).
  * The solved message will only be shown after a game has been solved.
  * The timer is visible after a puzzle is solved ([issue 46](https://code.google.com/p/mathdoku/issues/detail?id=46)).

# Version 1.95 (including subversions) #

## Enhancements ##
  * Theme and appearance improvements (version 1.95)
  * Simple timer (version 1.95)
  * Option to disable time (version 1.95a)

## Bug fixes ##
  * Small screen device layout fixes (version 1.95d)
  * Fix a startup crash (version 1.95c)
  * Fix a startup crash (version 1.95b)
  * Timer bug fixes (version 1.95a)

# Version 1.9 #

## Bug fixes ##
  * Undocumented

# Version 1.8 #

## Enhancements ##
  * Added 9x9 grid ([Issue 7](https://code.google.com/p/mathdoku/issues/detail?id=7))
  * Added option to hide operations signs ([Issue 13](https://code.google.com/p/mathdoku/issues/detail?id=13))
  * Added option to control positioning of maybe numbers ([Issue 26](https://code.google.com/p/mathdoku/issues/detail?id=26))
  * Dutch translation ([Issue 24](https://code.google.com/p/mathdoku/issues/detail?id=24))


# Version 1.7 #

## Enhancements ##
  * Added Check Progress feature ([Issue 9](https://code.google.com/p/mathdoku/issues/detail?id=9))
  * Maybe numbers shown in a 3x3 square ([Issue 6](https://code.google.com/p/mathdoku/issues/detail?id=6))
  * Added explicit support for large (Tablet) and small screen devices

## Bug fixes ##
  * Puzzle doesn't fill screen on tablet ([Issue 16](https://code.google.com/p/mathdoku/issues/detail?id=16))
  * 'Maybe' and 'clear' buttons disappear on >4x4 grid because of overflow on small screen ([Issue 14](https://code.google.com/p/mathdoku/issues/detail?id=14))

# Version 1.6 #

## Enhancements ##
  * All puzzles now have a unique solution ([Issue 2](https://code.google.com/p/mathdoku/issues/detail?id=2))
  * Added support for Install to SD Card

## Bug fixes ##
  * Ocassionally (very rarely) fails to create a complete puzzle ([issue 3](https://code.google.com/p/mathdoku/issues/detail?id=3))
  * Rare Force Close after installing new version ([Issue 4](https://code.google.com/p/mathdoku/issues/detail?id=4))
  * First time use of Alternate Theme option has no effect ([Issue 5](https://code.google.com/p/mathdoku/issues/detail?id=5))
  * Long press when no board is showing results in Force Close ([Issue 8](https://code.google.com/p/mathdoku/issues/detail?id=8))
  * L shaped pieces now used in 8x8 grids ([Issue 11](https://code.google.com/p/mathdoku/issues/detail?id=11))

# Version 1.5 #

Now with context menu on game grid

# Version 1.4 #

Fixed display problems. Added 'clear all'

# Version 1.3 #

Crash fix. 'Click' sound effects

# Version 1.2 #

Bug fixes, default theme change

# Version 1.1 #

Gameplay and appearance improvements

# Version 1.0 #

Initial version